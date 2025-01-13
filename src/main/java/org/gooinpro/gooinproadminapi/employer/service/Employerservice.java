package org.gooinpro.gooinproadminapi.employer.service;


import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.gooinpro.gooinproadminapi.common.dto.PageRequestDTO;
import org.gooinpro.gooinproadminapi.common.dto.PageResponseDTO;
import org.gooinpro.gooinproadminapi.employer.dto.EmployerListDTO;
import org.gooinpro.gooinproadminapi.employer.resptiroty.EmployerRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
@Log4j2
public class Employerservice {

    private final EmployerRepository employerRepository;

    //승인 된 고용인 리스트 get
    public PageResponseDTO<EmployerListDTO> approvedEmployerListService(PageRequestDTO pageRequestDTO) {

        return employerRepository.approvedEmployerList(pageRequestDTO);
    }

    //승인되지 않은 고용인 리스트 get
    public PageResponseDTO<EmployerListDTO> notApprovedEmployerListService(PageRequestDTO pageRequestDTO) {

        return employerRepository.notApprovedEmployerList(pageRequestDTO);
    }
}
