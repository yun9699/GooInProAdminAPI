package org.gooinpro.gooinproadminapi.customersupport.repository.faq.search;

import org.gooinpro.gooinproadminapi.common.dto.PageRequestDTO;
import org.gooinpro.gooinproadminapi.common.dto.PageResponseDTO;
import org.gooinpro.gooinproadminapi.customersupport.dto.faq.FAQDetailDTO;
import org.gooinpro.gooinproadminapi.customersupport.dto.faq.FAQListDTO;

public interface FAQSearch {

    PageResponseDTO<FAQListDTO> faqList(PageRequestDTO pageRequestDTO);

    FAQDetailDTO faqDetail(Long fno);

}
