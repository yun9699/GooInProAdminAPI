package org.gooinpro.gooinproadminapi.parttimer.repository.search;

import org.gooinpro.gooinproadminapi.common.dto.PageRequestDTO;
import org.gooinpro.gooinproadminapi.common.dto.PageResponseDTO;
import org.gooinpro.gooinproadminapi.parttimer.dto.PartTimerDetailDTO;
import org.gooinpro.gooinproadminapi.parttimer.dto.PartTimerListDTO;

public interface PartTimerSearch {

    PageResponseDTO<PartTimerListDTO> getPartTimerList(PageRequestDTO pageRequestDTO);

    PartTimerDetailDTO partTimerDetail(Long pno);

}
