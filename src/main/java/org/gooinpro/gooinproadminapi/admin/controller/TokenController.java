package org.gooinpro.gooinproadminapi.admin.controller;

import io.jsonwebtoken.ExpiredJwtException;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.gooinpro.gooinproadminapi.admin.domain.AdminEntity;
import org.gooinpro.gooinproadminapi.admin.dto.token.TokenRequestDTO;
import org.gooinpro.gooinproadminapi.admin.dto.token.TokenResponseDTO;
import org.gooinpro.gooinproadminapi.admin.exception.AdminExceptions;
import org.gooinpro.gooinproadminapi.admin.repository.AdminRepository;
import org.gooinpro.gooinproadminapi.admin.service.AdminService;
import org.gooinpro.gooinproadminapi.security.util.JWTUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@RequestMapping("/admin/api/v1/jwt")
@RequiredArgsConstructor
@Log4j2
public class TokenController {

    private final AdminService adminService;
    private final JWTUtil jwtUtil;
    private final AdminRepository adminRepository;
    private final PasswordEncoder passwordEncoder;

    @Value("${org.gooinpro.accessTime}")
    private int accessTime;

    @Value("${org.gooinpro.refreshTime}")
    private int refreshTime;

    @Value("${org.gooinpro.alwaysNew}")
    private boolean alwaysNew;

    @PostMapping("makeToken")
    public ResponseEntity<TokenResponseDTO> makeToken(
            @RequestBody @Validated TokenRequestDTO tokenRequestDTO) {

        log.info("===============================");
        log.info("Make Token");

        // 관리자 인증
        AdminEntity admin = adminRepository.findByAdmidAndAdmdelete(tokenRequestDTO.getAdmid(), false)
                .orElseThrow(() -> AdminExceptions.ADMIN_NOT_FOUND.get());

        if(!passwordEncoder.matches(tokenRequestDTO.getAdmpw(), admin.getAdmpw())) {
            throw AdminExceptions.BAD_AUTH.get();
        }

        Map<String, Object> claimMap =
                Map.of("admno", admin.getAdmno(), "admrole", admin.getAdmrole());

        String accessToken = jwtUtil.createToken(claimMap, accessTime);
        String refreshToken = jwtUtil.createToken(claimMap, refreshTime);

        TokenResponseDTO tokenResponseDTO = new TokenResponseDTO();
        tokenResponseDTO.setAccessToken(accessToken);
        tokenResponseDTO.setRefreshToken(refreshToken);
        tokenResponseDTO.setAdmno(admin.getAdmno());
        tokenResponseDTO.setAdmid(admin.getAdmid());
        tokenResponseDTO.setAdmname(admin.getAdmname());
        tokenResponseDTO.setAdmrole(admin.getAdmrole());

        return ResponseEntity.ok(tokenResponseDTO);
    }

    @PostMapping(value ="refreshToken",
            consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TokenResponseDTO> refreshToken(
            @RequestHeader("Authorization") String accessToken,
            String refreshToken) {

        if(accessToken == null || refreshToken == null) {
            throw AdminExceptions.TOKEN_NOT_ENOUGH.get();
        }

        if(!accessToken.startsWith("Bearer ")) {
            throw AdminExceptions.ACCESSTOKEN_TOO_SHORT.get();
        }

        String accessTokenStr = accessToken.substring("Bearer ".length());

        try {
            Map<String, Object> payload = jwtUtil.validateToken(accessTokenStr);

            Long admno = ((Number) payload.get("admno")).longValue();

            TokenResponseDTO tokenResponseDTO = new TokenResponseDTO();
            tokenResponseDTO.setAccessToken(accessTokenStr);
            tokenResponseDTO.setAdmno(admno);
            tokenResponseDTO.setRefreshToken(refreshToken);

            AdminEntity admin = adminRepository.findById(admno)
                    .orElseThrow(() -> AdminExceptions.ADMIN_NOT_FOUND.get());
            tokenResponseDTO.setAdmid(admin.getAdmid());
            tokenResponseDTO.setAdmname(admin.getAdmname());
            tokenResponseDTO.setAdmrole(admin.getAdmrole());

            return ResponseEntity.ok(tokenResponseDTO);

        } catch(ExpiredJwtException ex) {
            try {
                Map<String, Object> payload = jwtUtil.validateToken(refreshToken);

                Long admno = ((Number) payload.get("admno")).longValue();
                String admrole = payload.get("admrole").toString();
                String newAccessToken = null;
                String newRefreshToken = null;

                if(alwaysNew) {
                    Map<String, Object> claimMap = Map.of("admno", admno, "admrole", admrole);
                    newAccessToken = jwtUtil.createToken(claimMap, accessTime);
                    newRefreshToken = jwtUtil.createToken(claimMap, refreshTime);
                }

                TokenResponseDTO tokenResponseDTO = new TokenResponseDTO();
                tokenResponseDTO.setAccessToken(newAccessToken);
                tokenResponseDTO.setRefreshToken(newRefreshToken);
                tokenResponseDTO.setAdmno(admno);

                AdminEntity admin = adminRepository.findById(admno)
                        .orElseThrow(() -> AdminExceptions.ADMIN_NOT_FOUND.get());
                tokenResponseDTO.setAdmid(admin.getAdmid());
                tokenResponseDTO.setAdmname(admin.getAdmname());
                tokenResponseDTO.setAdmrole(admin.getAdmrole());

                return ResponseEntity.ok(tokenResponseDTO);

            } catch(ExpiredJwtException ex2) {
                throw AdminExceptions.REQUIRE_SIGN_IN.get();
            }
        }
    }

    @PostMapping("deleteToken")
    public ResponseEntity<TokenResponseDTO> deleteToken(@RequestParam Long admno) {
        Map<String, Object> claimMap = Map.of("admno", admno);

        String accessToken = jwtUtil.createToken(claimMap, 0);
        String refreshToken = jwtUtil.createToken(claimMap, 0);

        AdminEntity admin = adminRepository.findById(admno)
                .orElseThrow(() -> AdminExceptions.ADMIN_NOT_FOUND.get());

        TokenResponseDTO tokenResponseDTO = new TokenResponseDTO();
        tokenResponseDTO.setAccessToken(accessToken);
        tokenResponseDTO.setRefreshToken(refreshToken);
        tokenResponseDTO.setAdmno(admno);
        tokenResponseDTO.setAdmid(admin.getAdmid());
        tokenResponseDTO.setAdmname(admin.getAdmname());
        tokenResponseDTO.setAdmrole(admin.getAdmrole());

        return ResponseEntity.ok(tokenResponseDTO);
    }
}
