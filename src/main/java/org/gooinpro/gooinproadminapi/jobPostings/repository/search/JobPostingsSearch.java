package org.gooinpro.gooinproadminapi.jobPostings.repository.search;

import org.gooinpro.gooinproadminapi.common.dto.PageRequestDTO;
import org.gooinpro.gooinproadminapi.common.dto.PageResponseDTO;
import org.gooinpro.gooinproadminapi.jobPostings.dto.JobPostingsListDTO;

public interface JobPostingsSearch {

    //전체 구인 공고 리스트 get
    PageResponseDTO<JobPostingsListDTO> jobPostingsList(PageRequestDTO pageRequestDTO);
}
