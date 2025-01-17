package org.gooinpro.gooinproadminapi.jobPostings.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.gooinpro.gooinproadminapi.common.dto.PageRequestDTO;
import org.gooinpro.gooinproadminapi.common.dto.PageResponseDTO;
import org.gooinpro.gooinproadminapi.jobPostings.dto.JobPostingsListDTO;
import org.gooinpro.gooinproadminapi.jobPostings.dto.JobPostingsReadDTO;
import org.gooinpro.gooinproadminapi.jobPostings.service.JobPostingsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/api/v1/jobPostings")
@Log4j2
@RequiredArgsConstructor
public class JobPostingsController {

    private final JobPostingsService jobPostingsService;

    //구인 공고 리스트 get(eno = 0 이면 전체 구인 공고 리스트, jpname != null 이면 공고 이름으로 검색 결과 리스트)
    @GetMapping("list/{eno}")
    public ResponseEntity<PageResponseDTO<JobPostingsListDTO>> allListController(
            @PathVariable Long eno, @RequestParam(required = false) String jpname, PageRequestDTO pageRequestDTO) {

        log.info("JobPostingsController: allList");
        return ResponseEntity.ok(
                jobPostingsService.jobPostingsAllListService(eno, jpname, pageRequestDTO));
    }

    //구인 공고 삭제
    @PutMapping("delete/{jpno}")
    public ResponseEntity<String> deleteController(@PathVariable Long jpno) {

        log.info("JobPostingsController: delete");
        return ResponseEntity.ok(jobPostingsService.deleteJobPosting(jpno));
    }

    //구인 공고 상세 보기
    @GetMapping("read/{jpno}")
    public ResponseEntity<JobPostingsReadDTO> readController(@PathVariable Long jpno) {

        log.info("JobPostingsController: read");
        return ResponseEntity.ok(jobPostingsService.jobPostingsReadService(jpno));
    }

    //구인 공고 수 확인
    @GetMapping("count")
    public ResponseEntity<Integer> countController() {

        log.info("JobPostingsController: count");
        return ResponseEntity.ok(jobPostingsService.jobPostingsCountService());
    }
}
