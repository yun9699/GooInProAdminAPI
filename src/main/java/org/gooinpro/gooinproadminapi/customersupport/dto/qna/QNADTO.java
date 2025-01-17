package org.gooinpro.gooinproadminapi.customersupport.dto.qna;


import lombok.Data;
import org.gooinpro.gooinproadminapi.admin.domain.AdminEntity;
import org.gooinpro.gooinproadminapi.employer.domain.EmployerEntity;
import org.gooinpro.gooinproadminapi.parttimer.domain.PartTimerEntity;

import java.sql.Timestamp;

@Data
public class QNADTO {

    private Long qno; // pk

    private String qtitle; // 제목

    private String qcontent; // 문의내용

    private EmployerEntity eno; // 고용인 fk

    private PartTimerEntity pno; // 근로자 fk

    private AdminEntity admno; // 서비스관리자 fk

    private Timestamp qregdate; // 등록 날짜(시간)

    private Timestamp qmoddate; // 수정 날짜(시간)

    private Timestamp qanswerTime; // 답변 시간

    private boolean qdelete; // 삭제여부
}
