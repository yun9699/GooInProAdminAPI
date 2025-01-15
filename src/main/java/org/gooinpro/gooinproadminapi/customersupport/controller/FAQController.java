package org.gooinpro.gooinproadminapi.customersupport.controller;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.gooinpro.gooinproadminapi.common.dto.PageRequestDTO;
import org.gooinpro.gooinproadminapi.common.dto.PageResponseDTO;
import org.gooinpro.gooinproadminapi.customersupport.dto.faq.*;
import org.gooinpro.gooinproadminapi.customersupport.service.FAQService;
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
        return ResponseEntity.ok(faqService.addFAQ(faqAddDTO));
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
