package org.gooinpro.gooinproadminapi.parttimer.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.gooinpro.gooinproadminapi.common.dto.PageRequestDTO;
import org.gooinpro.gooinproadminapi.common.dto.PageResponseDTO;
import org.gooinpro.gooinproadminapi.parttimer.dto.PartTimerDTO;
import org.gooinpro.gooinproadminapi.parttimer.repository.PartTimerRepository;
import org.springframework.stereotype.Service;

@Service
@Transactional
@Log4j2
@RequiredArgsConstructor
public class PartTimerService {

    private final PartTimerRepository partTimerRepository;

    public PageResponseDTO<PartTimerDTO> partTimerList(PageRequestDTO pageRequestDTO) {

        return partTimerRepository.getPartTimerList(pageRequestDTO);
    }
}
