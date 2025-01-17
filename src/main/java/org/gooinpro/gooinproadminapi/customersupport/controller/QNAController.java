package org.gooinpro.gooinproadminapi.customersupport.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.gooinpro.gooinproadminapi.common.dto.PageRequestDTO;
import org.gooinpro.gooinproadminapi.common.dto.PageResponseDTO;
import org.gooinpro.gooinproadminapi.customersupport.dto.qna.QNAAnswerDTO;
import org.gooinpro.gooinproadminapi.customersupport.dto.qna.QNADetailDTO;
import org.gooinpro.gooinproadminapi.customersupport.dto.qna.QNAListDTO;
import org.gooinpro.gooinproadminapi.customersupport.service.QNAService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Log4j2
@RequestMapping("/admin/api/v1/qna")
@RequiredArgsConstructor
public class QNAController {

    private final QNAService qnaService;

    @GetMapping("list")
    public ResponseEntity<PageResponseDTO<QNAListDTO>> getQNAList(PageRequestDTO pageRequestDTO) {
        return ResponseEntity.ok(qnaService.QNAList(pageRequestDTO));
    }

    @GetMapping("detail/{qno}")
    public ResponseEntity<QNADetailDTO> getQNADetail(@PathVariable("qno") Long qno) {
        return ResponseEntity.ok(qnaService.getQnaDetail(qno));
    }

    @PutMapping("answer/{qno}")
    public ResponseEntity<String> answerQNA(@PathVariable("qno") Long qno, @RequestBody QNAAnswerDTO qnaAnswerDTO) {
        if (qno == null || qnaAnswerDTO == null || qnaAnswerDTO.getAdmno() == null) {
            throw new IllegalArgumentException("qno and admno must not be null");
        }
        return ResponseEntity.ok(qnaService.qnaAnswer(qno, qnaAnswerDTO));
    }


    @PutMapping("delete/{qno}")
    public ResponseEntity<String> deleteQNA(@PathVariable("qno") Long qno) {
        return ResponseEntity.ok(qnaService.qnaDelete(qno));
    }

    @GetMapping("statuslist/{qstatus}")
    public ResponseEntity<PageResponseDTO<QNAListDTO>> getQNAStatusList(
            @PathVariable("qstatus") boolean qstatus, PageRequestDTO pageRequestDTO) {
        return ResponseEntity.ok(qnaService.qnaStatusList(qstatus, pageRequestDTO));
    }
}
