package org.gooinpro.gooinproadminapi.jobPostings.dto;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class JobPostingsListDTO {

    private Long jpno;  //PK

    private String ename;   //등록한 고용인 이름

    private String jpname;  //공고 이름

    private Timestamp jpregdate;    //등록 시간

    private int jphourlyRate;   //시급
}
