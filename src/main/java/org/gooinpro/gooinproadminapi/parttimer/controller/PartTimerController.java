package org.gooinpro.gooinproadminapi.parttimer.controller;


import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.gooinpro.gooinproadminapi.common.dto.PageRequestDTO;
import org.gooinpro.gooinproadminapi.common.dto.PageResponseDTO;
import org.gooinpro.gooinproadminapi.parttimer.dto.PartTimerDetailDTO;
import org.gooinpro.gooinproadminapi.parttimer.dto.PartTimerListDTO;
import org.gooinpro.gooinproadminapi.parttimer.service.PartTimerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/api/v1/partTimer")
@Log4j2
@RequiredArgsConstructor
public class PartTimerController{

    private final PartTimerService partTimerService;

    @GetMapping("list")
    public ResponseEntity<PageResponseDTO<PartTimerListDTO>> partTimerList(PageRequestDTO pageRequestDTO){
        return ResponseEntity.ok(partTimerService.partTimerList(pageRequestDTO));
    }

    @GetMapping("detail/{pno}")
    public ResponseEntity<PartTimerDetailDTO> partTimerDetail(@PathVariable Long pno){
        return ResponseEntity.ok(partTimerService.partTimerDetail(pno));
    }

    @PutMapping("delete/{pno}")
    public ResponseEntity<String> partTimerDelete(@PathVariable Long pno){
        return ResponseEntity.ok(partTimerService.deletePartTimer(pno));
    }

}
