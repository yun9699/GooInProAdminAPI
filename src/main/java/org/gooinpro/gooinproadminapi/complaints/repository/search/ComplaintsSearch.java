package org.gooinpro.gooinproadminapi.complaints.repository.search;

import org.gooinpro.gooinproadminapi.common.dto.PageRequestDTO;
import org.gooinpro.gooinproadminapi.common.dto.PageResponseDTO;
import org.gooinpro.gooinproadminapi.complaints.dto.ComplaintsDetailDTO;
import org.gooinpro.gooinproadminapi.complaints.dto.ComplaintsListDTO;

public interface ComplaintsSearch {

    PageResponseDTO<ComplaintsListDTO> complaintsList(PageRequestDTO pageRequestDTO);

    ComplaintsDetailDTO getComplaintsDetail(Long cno);

    PageResponseDTO<ComplaintsListDTO> complaintsTFList(Boolean cstatus , PageRequestDTO pageRequestDTO);

}
