package org.gooinpro.gooinproadminapi.jobPostings.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.gooinpro.gooinproadminapi.common.dto.PageRequestDTO;
import org.gooinpro.gooinproadminapi.common.dto.PageResponseDTO;
import org.gooinpro.gooinproadminapi.jobPostings.dto.JobPostingsListDTO;
import org.gooinpro.gooinproadminapi.jobPostings.service.JobPostingsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin/api/v1/jobPostings")
@Log4j2
@RequiredArgsConstructor
public class JobPostingsController {

    private final JobPostingsService jobPostingsService;

    //전체 구인 공고 리스트 get
    @GetMapping("allList")
    public ResponseEntity<PageResponseDTO<JobPostingsListDTO>> allListController(PageRequestDTO pageRequestDTO) {

        log.info("JobPostingsController: allList");
        return ResponseEntity.ok(jobPostingsService.jobPostingsAllListService(pageRequestDTO));
    }
}
