package org.gooinpro.gooinproadminapi.customersupport.dto.qna;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class QNADetailDTO {

    private Long qno; // pk

    private String qtitle; // 제목

    private String qcontent; // QNA 문의 내용

    private String qanswer; // QNA 답변 내용

    private String pname; // 근로자 이름

    private String admname; // 서비스 관리자 이름

    private Timestamp qregdate; // 등록 날짜(시간)

    private Timestamp qmoddate; // 수정 날짜(시간)

    private Timestamp qanswerTime; // 답변 시간

    private boolean qdelete; // 삭제여부

}
