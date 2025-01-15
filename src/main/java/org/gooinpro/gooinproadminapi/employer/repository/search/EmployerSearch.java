package org.gooinpro.gooinproadminapi.employer.repository.search;

import org.gooinpro.gooinproadminapi.common.dto.PageRequestDTO;
import org.gooinpro.gooinproadminapi.common.dto.PageResponseDTO;
import org.gooinpro.gooinproadminapi.employer.dto.EmployerListDTO;

public interface EmployerSearch {

    //고용인 리스트
    PageResponseDTO<EmployerListDTO> employerList(PageRequestDTO pageRequestDTO);

}
