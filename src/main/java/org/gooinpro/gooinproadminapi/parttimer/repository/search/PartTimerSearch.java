package org.gooinpro.gooinproadminapi.parttimer.repository.search;

import org.gooinpro.gooinproadminapi.common.dto.PageRequestDTO;
import org.gooinpro.gooinproadminapi.common.dto.PageResponseDTO;
import org.gooinpro.gooinproadminapi.parttimer.dto.PartTimerDTO;

public interface PartTimerSearch {

    PageResponseDTO<PartTimerDTO> getPartTimerList(PageRequestDTO pageRequestDTO);

}
