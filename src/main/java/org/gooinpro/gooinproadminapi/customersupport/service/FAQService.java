package org.gooinpro.gooinproadminapi.customersupport.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.gooinpro.gooinproadminapi.admin.domain.AdminEntity;
import org.gooinpro.gooinproadminapi.admin.repository.AdminRepository;
import org.gooinpro.gooinproadminapi.common.dto.PageRequestDTO;
import org.gooinpro.gooinproadminapi.common.dto.PageResponseDTO;
import org.gooinpro.gooinproadminapi.customersupport.domain.FAQEntity;
import org.gooinpro.gooinproadminapi.customersupport.dto.faq.FAQAddDTO;
import org.gooinpro.gooinproadminapi.customersupport.dto.faq.FAQDTO;
import org.gooinpro.gooinproadminapi.customersupport.dto.faq.FAQDetailDTO;
import org.gooinpro.gooinproadminapi.customersupport.dto.faq.FAQListDTO;
import org.gooinpro.gooinproadminapi.customersupport.repository.faq.FAQRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
@Log4j2
@RequiredArgsConstructor
public class FAQService {

    private final FAQRepository faqRepository;
    private final AdminRepository adminRepository;

    public PageResponseDTO<FAQListDTO> getFAQList(PageRequestDTO pageRequestDTO) {

        return faqRepository.faqList(pageRequestDTO);
    }

    public FAQDetailDTO getFAQDetail(Long fno) {
        return faqRepository.faqDetail(fno);
    }

    public String addFAQ(FAQAddDTO faqAddDTO) {
        log.info("Received admno: " + faqAddDTO.getAdmno());

        Optional<AdminEntity> admno = adminRepository.findById(faqAddDTO.getAdmno());

        if (!admno.isPresent()) {
            log.error("해당 관리자 찾을 수 없음: " + faqAddDTO.getAdmno());
            return "해당 관리자 찾을 수 없음: " + faqAddDTO.getAdmno();
        }

        FAQEntity faqEntity = FAQEntity.builder()
                .admno(admno.get())
                .ftitle(faqAddDTO.getFtitle())
                .fcontent(faqAddDTO.getFcontent())
                .fcategory(faqAddDTO.getFcategory())
                .fdelete(faqAddDTO.isFdelete())
                .build();

        faqRepository.save(faqEntity);

        return "FAQ 등록 완료";
    }


    public String deleteFAQ(Long fno) {

        Optional<FAQEntity> faq = faqRepository.findById(fno);

        if (faq.isPresent()) {

            FAQEntity faqEntity = faq.get();

            faqEntity.setFdelete(true);

            return "faq 삭제 완료";
        }

        return "faq 삭제 오류";

    }

    public String editFAQ(FAQDTO faqDTO) {

        Optional<FAQEntity> faq = faqRepository.findById(faqDTO.getFno());
        if (faq.isPresent()) {
            FAQEntity faqEntity = faq.get();
            faqEntity.setFtitle(faqDTO.getFtitle());
            faqEntity.setFcontent(faqDTO.getFcontent());
            faqEntity.setFcategory(faqDTO.getFcategory());

            faqRepository.save(faqEntity);
        }

        return "faq 수정 완료";
    }
}
