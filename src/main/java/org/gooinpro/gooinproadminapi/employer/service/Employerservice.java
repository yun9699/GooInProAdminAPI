package org.gooinpro.gooinproadminapi.employer.service;


import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.gooinpro.gooinproadminapi.common.dto.PageRequestDTO;
import org.gooinpro.gooinproadminapi.common.dto.PageResponseDTO;
import org.gooinpro.gooinproadminapi.employer.domain.EmployerEntity;
import org.gooinpro.gooinproadminapi.employer.dto.EmployerListDTO;
import org.gooinpro.gooinproadminapi.employer.dto.EmployerReadDTO;
import org.gooinpro.gooinproadminapi.employer.resptiroty.EmployerRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
@Log4j2
public class Employerservice {

    private final EmployerRepository employerRepository;

    //고용인 리스트 get
    public PageResponseDTO<EmployerListDTO> employerListService(PageRequestDTO pageRequestDTO) {

        log.info("EmployerService: getList");
        return employerRepository.employerList(pageRequestDTO);
    }

    //고용인 강제 삭제
    public String deleteEmployerService(Long eno) {

        EmployerEntity employer = employerRepository.findById(eno).orElseThrow(
                () -> new RuntimeException("Can't find employer with eno " + eno)
        );

        employer.setEdelete(true);

        employerRepository.save(employer);

        log.info("EmployerService: delete");
        return "Successfully_deleted_employer " + eno;
    }

    //고용인 상세 보기
    public EmployerReadDTO employerReadService(Long eno) {

        EmployerEntity employer = employerRepository.findById(eno).orElseThrow(
                () -> new RuntimeException("Can't find employer with eno " + eno)
        );

        EmployerReadDTO employerReadDTO = EmployerReadDTO.builder()
                .ename(employer.getEname())
                .ebirth(employer.getEbirth())
                .egender(employer.isEgender())
                .eregdate(employer.getEregdate())
                .eno(employer.getEno())
                .eemail(employer.getEemail())
                .build();

        log.info("EmployerService: read");
        return employerReadDTO;
    }
}
