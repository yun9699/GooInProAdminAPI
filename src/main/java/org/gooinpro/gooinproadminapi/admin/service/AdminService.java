package org.gooinpro.gooinproadminapi.admin.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.gooinpro.gooinproadminapi.admin.domain.AdminEntity;
import org.gooinpro.gooinproadminapi.admin.dto.AdminEditDTO;
import org.gooinpro.gooinproadminapi.admin.dto.AdminListDTO;
import org.gooinpro.gooinproadminapi.admin.dto.AdminRegisterDTO;
import org.gooinpro.gooinproadminapi.admin.exception.AdminExceptions;
import org.gooinpro.gooinproadminapi.admin.repository.AdminRepository;
import org.gooinpro.gooinproadminapi.common.dto.PageRequestDTO;
import org.gooinpro.gooinproadminapi.common.dto.PageResponseDTO;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Log4j2
public class AdminService {

    private final AdminRepository adminRepository;
    private final PasswordEncoder passwordEncoder;

    // 관리자 등록
    public Long register(AdminRegisterDTO dto) {
        // 중복 ID 체크
        if(adminRepository.findByAdmid(dto.getAdmid()).isPresent()) {
            throw AdminExceptions.DUPLICATE_ADMIN_ID.get();
        }

        AdminEntity admin = AdminEntity.builder()
                .admid(dto.getAdmid())
                .admpw(passwordEncoder.encode(dto.getAdmpw()))
                .admname(dto.getAdmname())
                .admrole(dto.getAdmrole())
                .build();

        AdminEntity savedAdmin = adminRepository.save(admin);
        return savedAdmin.getAdmno();
    }

    // 관리자 수정
    public void edit(AdminEditDTO dto) {
        AdminEntity admin = adminRepository.findById(dto.getAdmno())
                .orElseThrow(() -> AdminExceptions.ADMIN_NOT_FOUND.get());

        admin.setAdmpw(passwordEncoder.encode(dto.getAdmpw()));
        admin.setAdmname(dto.getAdmname());
        admin.setAdmrole(dto.getAdmrole());

        adminRepository.save(admin);
    }

    // 관리자 삭제
    public void delete(Long admno) {
        AdminEntity admin = adminRepository.findById(admno)
                .orElseThrow(() -> AdminExceptions.ADMIN_NOT_FOUND.get());

        admin.setAdmdelete(true);
        adminRepository.save(admin);
    }

    // 관리자 목록 조회
    public PageResponseDTO<AdminListDTO> list(PageRequestDTO pageRequestDTO, String[] types, String keyword) {
        return adminRepository.searchAdmin(pageRequestDTO, types, keyword);
    }

    // 관리자 상세 조회
    public AdminListDTO get(Long admno) {
        AdminEntity admin = adminRepository.findById(admno)
                .orElseThrow(() -> AdminExceptions.ADMIN_NOT_FOUND.get());

        return AdminListDTO.builder()
                .admno(admin.getAdmno())
                .admid(admin.getAdmid())
                .admname(admin.getAdmname())
                .admrole(admin.getAdmrole())
                .admdelete(admin.isAdmdelete())
                .admregdate(admin.getAdmregdate())
                .build();
    }
}
