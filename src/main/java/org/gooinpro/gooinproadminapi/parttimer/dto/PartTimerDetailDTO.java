package org.gooinpro.gooinproadminapi.parttimer.dto;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class PartTimerDetailDTO {

    private Long pno; // pk

    private String pemail; // email

    private String ppw; // password

    private String pname; // 근로자 이름

    private String pdifilename; // 근로자 서류 사진

    private String pifilename; // 근로자 프로필 사진

    private Timestamp pbirth; // 생년월일

    private boolean pgender; // 성별 true = 남 , false = 여

    private boolean pdelete; // 삭제여부

    private Timestamp pregdate; // 가입 날짜(시간)

    private String proadAddress; // 도로명 주소

    private String pdetailAddress; // 상세 주소
}
