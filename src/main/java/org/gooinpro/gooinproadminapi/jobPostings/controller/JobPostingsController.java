package org.gooinpro.gooinproadminapi.jobPostings.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.gooinpro.gooinproadminapi.jobPostings.service.JobPostingsService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin/api/v1/jobPostings")
@Log4j2
@RequiredArgsConstructor
public class JobPostingsController {

    private final JobPostingsService jobPostingsService;
}
