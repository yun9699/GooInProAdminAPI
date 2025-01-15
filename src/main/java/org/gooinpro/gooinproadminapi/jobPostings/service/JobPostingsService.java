package org.gooinpro.gooinproadminapi.jobPostings.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.gooinpro.gooinproadminapi.common.dto.PageRequestDTO;
import org.gooinpro.gooinproadminapi.common.dto.PageResponseDTO;
import org.gooinpro.gooinproadminapi.jobPostings.dto.JobPostingsListDTO;
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
}
