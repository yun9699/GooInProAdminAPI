package org.gooinpro.gooinproadminapi.admin.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.gooinpro.gooinproadminapi.admin.dto.AdminEditDTO;
import org.gooinpro.gooinproadminapi.admin.dto.AdminListDTO;
import org.gooinpro.gooinproadminapi.admin.dto.AdminRegisterDTO;
import org.gooinpro.gooinproadminapi.admin.service.AdminService;
import org.gooinpro.gooinproadminapi.common.dto.PageRequestDTO;
import org.gooinpro.gooinproadminapi.common.dto.PageResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/api/v1/admin")
@Log4j2
@RequiredArgsConstructor
public class AdminController {

    private final AdminService adminService;

    @PostMapping("/register")
    public ResponseEntity<Long> register(@Valid @RequestBody AdminRegisterDTO adminRegisterDTO) {
        return ResponseEntity.ok(adminService.register(adminRegisterDTO));
    }

    @PutMapping("/edit")
    public ResponseEntity<Void> edit(@Valid @RequestBody AdminEditDTO adminEditDTO) {
        adminService.edit(adminEditDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/delete/{admno}")
    public ResponseEntity<Void> delete(@PathVariable Long admno) {
        adminService.delete(admno);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/list")
    public ResponseEntity<PageResponseDTO<AdminListDTO>> list(
            @Valid PageRequestDTO pageRequestDTO,
            @RequestParam(required = false) String[] types,
            @RequestParam(required = false) String keyword) {
        return ResponseEntity.ok(adminService.list(pageRequestDTO, types, keyword));
    }

    @GetMapping("/{admno}")
    public ResponseEntity<AdminListDTO> get(@PathVariable Long admno) {
        return ResponseEntity.ok(adminService.get(admno));
    }
}

