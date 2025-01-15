package org.gooinpro.gooinproadminapi.employer.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.gooinpro.gooinproadminapi.common.dto.PageRequestDTO;
import org.gooinpro.gooinproadminapi.common.dto.PageResponseDTO;
import org.gooinpro.gooinproadminapi.employer.dto.EmployerListDTO;
import org.gooinpro.gooinproadminapi.employer.dto.EmployerReadDTO;
import org.gooinpro.gooinproadminapi.employer.service.EmployerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/api/v1/employer")
@Log4j2
@RequiredArgsConstructor
public class EmployerController {

    private final EmployerService employerService;

    //고용인 리스트 get
    @GetMapping("list")
    public ResponseEntity<PageResponseDTO<EmployerListDTO>> listController(
            PageRequestDTO pageRequestDTO) {

        log.info("EmployerController: getList");
        return ResponseEntity.ok(employerService.employerListService(pageRequestDTO));
    }

    //고용인 강제 삭제
    @PutMapping("delete/{eno}")
    public ResponseEntity<String> deleteController(@PathVariable Long eno) {

        log.info("EmployerController: delete");
        return ResponseEntity.ok(employerService.deleteEmployerService(eno));
    }

    //고용인 상세 보기
    @GetMapping("read/{eno}")
    public ResponseEntity<EmployerReadDTO> readController(@PathVariable Long eno) {

        log.info("EmployerController: read");
        return ResponseEntity.ok(employerService.employerReadService(eno));
    }

    //고용인 수 확인
    @GetMapping("count")
    public ResponseEntity<Integer> countController() {

        log.info("EmployerController: count");
        return ResponseEntity.ok(employerService.employerCountService());
    }
}
