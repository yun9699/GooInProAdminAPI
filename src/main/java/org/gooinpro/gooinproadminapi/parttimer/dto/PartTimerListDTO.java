package org.gooinpro.gooinproadminapi.parttimer.dto;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class PartTimerListDTO {

    private Long pno; // pk

    private String pemail; // email

    private String pname; // 근로자 이름

    private Timestamp pbirth; // 생년월일

    private boolean pgender; // 성별

    private Timestamp pregdate; // 가입 날짜(시간)

    private String proadAddress; // 도로명 주소

}
