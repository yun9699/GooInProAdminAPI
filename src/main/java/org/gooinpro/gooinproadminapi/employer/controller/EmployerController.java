package org.gooinpro.gooinproadminapi.employer.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.gooinpro.gooinproadminapi.common.dto.PageRequestDTO;
import org.gooinpro.gooinproadminapi.common.dto.PageResponseDTO;
import org.gooinpro.gooinproadminapi.employer.dto.EmployerListDTO;
import org.gooinpro.gooinproadminapi.employer.service.Employerservice;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin/api/v1/employer")
@Log4j2
@RequiredArgsConstructor
public class EmployerController {

    private final Employerservice employerservice;

    //고용인 리스트 get
    @GetMapping("list")
    public ResponseEntity<PageResponseDTO<EmployerListDTO>> listController(
            PageRequestDTO pageRequestDTO) {

        return ResponseEntity.ok(employerservice.employerListService(pageRequestDTO));
    }
}
