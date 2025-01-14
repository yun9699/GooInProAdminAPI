package org.gooinpro.gooinproadminapi.employer.repository.search;

import org.gooinpro.gooinproadminapi.common.dto.PageRequestDTO;
import org.gooinpro.gooinproadminapi.common.dto.PageResponseDTO;
import org.gooinpro.gooinproadminapi.employer.dto.EmployerListDTO;

public interface EmployerSearch {

    //승인 된 고용인 리스트
    PageResponseDTO<EmployerListDTO> approvedEmployerList(PageRequestDTO pageRequestDTO);

    //승인 되지 않은 고용인 리스트
    PageResponseDTO<EmployerListDTO> notApprovedEmployerList(PageRequestDTO pageRequestDTO);
}
