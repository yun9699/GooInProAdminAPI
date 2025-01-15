package org.gooinpro.gooinproadminapi.jobPostings.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.gooinpro.gooinproadminapi.common.dto.PageRequestDTO;
import org.gooinpro.gooinproadminapi.common.dto.PageResponseDTO;
import org.gooinpro.gooinproadminapi.jobPostings.domain.JobPostingsEntity;
import org.gooinpro.gooinproadminapi.jobPostings.dto.JobPostingsListDTO;
import org.gooinpro.gooinproadminapi.jobPostings.dto.JobPostingsReadDTO;
import org.gooinpro.gooinproadminapi.jobPostings.repository.JobPostingsRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
@Log4j2
public class JobPostingsService {

    private final JobPostingsRepository jobPostingsRepository;

    //전체 구인 공고 리스트 get
    public PageResponseDTO<JobPostingsListDTO> jobPostingsAllListService(PageRequestDTO pageRequestDTO) {

        log.info("JobPostingsService: getAllList");
        return jobPostingsRepository.jobPostingsList(pageRequestDTO);
    }

    //구인 공고 삭제
    public String deleteJobPosting(Long jpno) {

        JobPostingsEntity jobPosting = jobPostingsRepository.findById(jpno).orElseThrow(
                () -> new RuntimeException("Can't fond JobPostings with jpno" + jpno)
        );

        jobPosting.setJpdelete(true);

        jobPostingsRepository.save(jobPosting);

        log.info("JobPostingsService: delete");
        return "Successfully_deleted_JobPosting " + jpno;
    }

    //구인 공고 상세 보기
    public JobPostingsReadDTO jobPostingsReadService(Long jpno) {

        JobPostingsEntity jobPostings = jobPostingsRepository.findById(jpno).orElseThrow(
                () -> new RuntimeException("Can't find jobPostings with jpno" + jpno)
        );

        log.info("JobPostingsService: read");
        return JobPostingsReadDTO.builder()
                .jpno(jobPostings.getJpno())
                .eno(jobPostings.getEmployer().getEno())
                .ename(jobPostings.getEmployer().getEname())
                .jpname(jobPostings.getJpname())
                .jpcontent(jobPostings.getJpcontent())
                .jpregdate(jobPostings.getJpregdate())
                .jpenddate(jobPostings.getJpenddate())
                .jpvacancies(jobPostings.getJpvacancies())
                .jphourlyRate(jobPostings.getJphourlyRate())
                .jpworkDays(jobPostings.getJpworkDays())
                .jpminDuration(jobPostings.getJpminDuration())
                .jpmaxDuration(jobPostings.getJpmaxDuration())
                .jpworkStartTime(jobPostings.getJpworkStartTime())
                .jpworkEndTime(jobPostings.getJpworkEndTime())
                .build();
    }

    //구인 공고 수 확인
    public Integer jobPostingsCountService() {

        return jobPostingsRepository.countByJpdelete(false);
    }
}
