package org.gooinpro.gooinproadminapi.complaints.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.gooinpro.gooinproadminapi.common.dto.PageRequestDTO;
import org.gooinpro.gooinproadminapi.common.dto.PageResponseDTO;
import org.gooinpro.gooinproadminapi.complaints.dto.ComplaintsDetailDTO;
import org.gooinpro.gooinproadminapi.complaints.dto.ComplaintsListDTO;
import org.gooinpro.gooinproadminapi.complaints.service.ComplaintsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Log4j2
@RequestMapping("/admin/api/v1/complaints")
@RequiredArgsConstructor
public class ComplaintsController {

    private final ComplaintsService complaintsService;

    @GetMapping("list")
    public ResponseEntity<PageResponseDTO<ComplaintsListDTO>> complaintsList(PageRequestDTO pageRequestDTO) {

        return ResponseEntity.ok(complaintsService.getComplaintsList(pageRequestDTO));
    }

    @GetMapping("detail/{cno}")
    public ResponseEntity<ComplaintsDetailDTO> complaintsDetail(@PathVariable("cno") Long cno) {

        return ResponseEntity.ok(complaintsService.complaintsDetail(cno));
    }

    @GetMapping("statuslist/{cstatus}")
    public ResponseEntity<PageResponseDTO<ComplaintsListDTO>> complaintsStatusList(@PathVariable Boolean cstatus, PageRequestDTO pageRequestDTO) {
        return ResponseEntity.ok(complaintsService.getComplaintsTFList(cstatus, pageRequestDTO));
    }

    @PutMapping("answer/{cno}")
    public ResponseEntity<String> answerComplaint(
            @PathVariable Long cno, @RequestBody String canswer) {

        complaintsService.answerComplaint(cno, canswer);

        return ResponseEntity.ok("답변이 성공적으로 등록 되었습니다.");

    }

    @PutMapping("delete/{cno}")
    public ResponseEntity<String> deleteComplaint(@PathVariable Long cno) {
        complaintsService.deleteComplaint(cno);
        return ResponseEntity.ok("삭제 완료");
    }

}
