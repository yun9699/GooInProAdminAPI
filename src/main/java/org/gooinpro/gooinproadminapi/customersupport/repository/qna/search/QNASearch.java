package org.gooinpro.gooinproadminapi.customersupport.repository.qna.search;

import org.gooinpro.gooinproadminapi.common.dto.PageRequestDTO;
import org.gooinpro.gooinproadminapi.common.dto.PageResponseDTO;
import org.gooinpro.gooinproadminapi.customersupport.dto.qna.QNADetailDTO;
import org.gooinpro.gooinproadminapi.customersupport.dto.qna.QNAListDTO;

public interface QNASearch {

    PageResponseDTO<QNAListDTO> qnaList(PageRequestDTO pageRequestDTO);

    QNADetailDTO qnaDetail(Long qno);

    PageResponseDTO<QNAListDTO> qnaStatusList(Boolean status, PageRequestDTO pageRequestDTO);
}
