package org.gooinpro.gooinproadminapi.complaints.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.gooinpro.gooinproadminapi.common.dto.PageRequestDTO;
import org.gooinpro.gooinproadminapi.common.dto.PageResponseDTO;
import org.gooinpro.gooinproadminapi.complaints.domain.ComplaintsEntity;
import org.gooinpro.gooinproadminapi.complaints.dto.ComplaintsDetailDTO;
import org.gooinpro.gooinproadminapi.complaints.dto.ComplaintsListDTO;
import org.gooinpro.gooinproadminapi.complaints.repository.ComplaintsRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@Log4j2
@RequiredArgsConstructor
public class ComplaintsService {

    private final ComplaintsRepository complaintsRepository;

    public PageResponseDTO<ComplaintsListDTO> getComplaintsList(PageRequestDTO pageRequestDTO) {

        return complaintsRepository.complaintsList(pageRequestDTO);

    }

    public ComplaintsDetailDTO complaintsDetail(Long cno) {

        return complaintsRepository.getComplaintsDetail(cno);

    }

    public PageResponseDTO<ComplaintsListDTO> getComplaintsTFList(Boolean cstatus, PageRequestDTO pageRequestDTO) {

        return complaintsRepository.complaintsTFList(cstatus, pageRequestDTO);

    }

    public void answerComplaint(Long cno, String canswer) {
        ComplaintsEntity complaints = complaintsRepository.findById(cno)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 신고 입니다."));

        complaints.setCanswer(canswer);
        complaints.setCstatus(true);
        complaintsRepository.save(complaints);
    }

    public void deleteComplaint(Long cno) {
        ComplaintsEntity complaints = complaintsRepository.findById(cno)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 신고 입니다."));

        complaints.setCdelete(true);
        complaintsRepository.save(complaints);
    }


}
