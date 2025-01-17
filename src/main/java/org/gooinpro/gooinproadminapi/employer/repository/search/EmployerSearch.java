package org.gooinpro.gooinproadminapi.employer.repository.search;

import org.gooinpro.gooinproadminapi.common.dto.PageRequestDTO;
import org.gooinpro.gooinproadminapi.common.dto.PageResponseDTO;
import org.gooinpro.gooinproadminapi.employer.dto.EmployerListDTO;

public interface EmployerSearch {

    //고용인 리스트(이름 검색 포함, ename == null 이면 전체 리스트 출력)
    PageResponseDTO<EmployerListDTO> employerList(String ename, PageRequestDTO pageRequestDTO);
}
