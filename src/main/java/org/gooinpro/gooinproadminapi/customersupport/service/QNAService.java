package org.gooinpro.gooinproadminapi.customersupport.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.gooinpro.gooinproadminapi.admin.domain.AdminEntity;
import org.gooinpro.gooinproadminapi.admin.repository.AdminRepository;
import org.gooinpro.gooinproadminapi.common.dto.PageRequestDTO;
import org.gooinpro.gooinproadminapi.common.dto.PageResponseDTO;
import org.gooinpro.gooinproadminapi.customersupport.domain.QNAEntity;
import org.gooinpro.gooinproadminapi.customersupport.dto.qna.QNAAnswerDTO;
import org.gooinpro.gooinproadminapi.customersupport.dto.qna.QNADetailDTO;
import org.gooinpro.gooinproadminapi.customersupport.dto.qna.QNAListDTO;
import org.gooinpro.gooinproadminapi.customersupport.repository.qna.QNARepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.Optional;

@Service
@Transactional
@Log4j2
@RequiredArgsConstructor
public class QNAService {

    private final QNARepository qnaRepository;
    private final AdminRepository adminRepository;

    public PageResponseDTO<QNAListDTO> QNAList(PageRequestDTO pageRequestDTO) {
        return qnaRepository.qnaList(pageRequestDTO);
    }

    public String qnaAnswer(Long qno, QNAAnswerDTO qnaAnswerDTO) {
        if (qno == null) {
            throw new IllegalArgumentException("Qno must not be null");
        }
        if (qnaAnswerDTO == null || qnaAnswerDTO.getAdmno() == null) {
            throw new IllegalArgumentException("Admno must not be null");
        }

        QNAEntity qna = qnaRepository.findById(qno)
                .orElseThrow(() -> new IllegalArgumentException("QNA not found with id: " + qno));

        AdminEntity admin = adminRepository.findById(qnaAnswerDTO.getAdmno())
                .orElseThrow(() -> new IllegalArgumentException("Admin not found with id: " + qnaAnswerDTO.getAdmno()));

        qna.setAdmno(admin);
        qna.setQanswer(qnaAnswerDTO.getQanswer());
        qna.setQstatus(true);
        qna.setQmoddate(Timestamp.from(Instant.now()));

        qnaRepository.save(qna);

        return "QNA 답변 완료";
    }


    public QNADetailDTO getQnaDetail(Long qno) {
        return qnaRepository.qnaDetail(qno);
    }

    public String qnaDelete(Long qno) {
        Optional<QNAEntity> qna = qnaRepository.findById(qno);

        if(qna.isPresent()) {
            QNAEntity qnaEntity = qna.get();

            qnaEntity.setQdelete(true);

            return "QNA 삭제 완료";
        }

        return "해당 QNA 찾을 수 없음";
    }

    public PageResponseDTO<QNAListDTO> qnaStatusList(Boolean status, PageRequestDTO pageRequestDTO) {

        return qnaRepository.qnaStatusList(status, pageRequestDTO);

    }

    public Long QNAFCount() {
        return qnaRepository.countByFStatus();
    }

    public Long QNATCount() {
        return qnaRepository.countByTStatus();
    }



}
