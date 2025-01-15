package org.gooinpro.gooinproadminapi.jobPostings.repository;

import org.gooinpro.gooinproadminapi.jobPostings.domain.JobPostingsEntity;
import org.gooinpro.gooinproadminapi.jobPostings.repository.search.JobPostingsSearch;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobPostingsRepository extends JpaRepository<JobPostingsEntity, Long>, JobPostingsSearch {

    Integer countByJpdelete(boolean jpdelete);  //jjpdelete 칼럼에 따른 count
}
