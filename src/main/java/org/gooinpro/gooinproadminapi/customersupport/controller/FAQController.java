package org.gooinpro.gooinproadminapi.customersupport.controller;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.gooinpro.gooinproadminapi.common.dto.PageRequestDTO;
import org.gooinpro.gooinproadminapi.common.dto.PageResponseDTO;
import org.gooinpro.gooinproadminapi.customersupport.dto.faq.*;
import org.gooinpro.gooinproadminapi.customersupport.service.FAQService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Log4j2
@RequestMapping("/admin/api/v1/faq")
@RequiredArgsConstructor
public class FAQController {

    private final FAQService faqService;

    @GetMapping("list")
    public ResponseEntity<PageResponseDTO<FAQListDTO>> faqList(PageRequestDTO pageRequestDTO) {
        return ResponseEntity.ok(faqService.getFAQList(pageRequestDTO));
    }

    @GetMapping("detail/{fno}")
    public ResponseEntity<FAQDetailDTO> faqDetail(@PathVariable Long fno) {
        return ResponseEntity.ok(faqService.getFAQDetail(fno));
    }

    @PostMapping("register")
    public ResponseEntity<String> faqAdd(@RequestBody FAQAddDTO faqAddDTO) {
        try {
            String message = faqService.addFAQ(faqAddDTO);
            return ResponseEntity.ok(message);
        } catch (IllegalArgumentException e) {
            log.error("FAQ 등록 실패: " + e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            log.error("서버 내부 오류 발생: " + e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("FAQ 등록 중 오류가 발생했습니다.");
        }
    }


    @PutMapping("delete/{fno}")
    public ResponseEntity<String> faqDelete(@PathVariable Long fno) {
        return ResponseEntity.ok(faqService.deleteFAQ(fno));
    }

    @PutMapping("edit/{fno}")
    public ResponseEntity<String> faqEdit(@PathVariable Long fno, @RequestBody FAQEditDTO faqEditDTO) {
        FAQDTO dto = new FAQDTO();

        dto.setFno(fno);
        dto.setFtitle(faqEditDTO.getFtitle());
        dto.setFcategory(faqEditDTO.getFcategory());
        dto.setFcontent(faqEditDTO.getFcontent());

        return ResponseEntity.ok(faqService.editFAQ(dto));
    }

}
