package org.gooinpro.gooinproadminapi.jobPostings.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.gooinpro.gooinproadminapi.jobPostings.repository.JobPostingsRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
@Log4j2
public class JobPostingsService {

    private final JobPostingsRepository jobPostingsRepository;
}
