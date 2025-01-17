package org.gooinpro.gooinproadminapi.parttimer.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.gooinpro.gooinproadminapi.common.dto.PageRequestDTO;
import org.gooinpro.gooinproadminapi.common.dto.PageResponseDTO;
import org.gooinpro.gooinproadminapi.parttimer.domain.PartTimerEntity;
import org.gooinpro.gooinproadminapi.parttimer.dto.PartTimerDetailDTO;
import org.gooinpro.gooinproadminapi.parttimer.dto.PartTimerListDTO;
import org.gooinpro.gooinproadminapi.parttimer.repository.PartTimerRepository;
import org.springframework.stereotype.Service;

@Service
@Transactional
@Log4j2
@RequiredArgsConstructor
public class PartTimerService {

    private final PartTimerRepository partTimerRepository;

    //근로자 리스트
    public PageResponseDTO<PartTimerListDTO> partTimerList(PageRequestDTO pageRequestDTO) {

        return partTimerRepository.getPartTimerList(pageRequestDTO);
    }

    // 근로자 상세보기
    public PartTimerDetailDTO partTimerDetail(Long pno) {
        return partTimerRepository.partTimerDetail(pno);
    }

    // 근로자 삭제
    public String deletePartTimer(Long pno) {
        PartTimerEntity partTimer = partTimerRepository.findById(pno).orElseThrow(
                () -> new RuntimeException("Part timer not found" + pno));

        partTimer.setPdelete(true);
        partTimerRepository.save(partTimer);

        return "Part timer deleted";
    }
}
