package org.gooinpro.gooinproadminapi.jobPostings.dto;

import lombok.Builder;
import lombok.Data;
import org.gooinpro.gooinproadminapi.employer.domain.EmployerEntity;

import java.sql.Time;
import java.sql.Timestamp;

@Data
@Builder
public class JobPostingsReadDTO {

    private Long jpno;  //PK

    private Long eno;   //작성한 고용인 PK

    private String ename;   //작성한 고용인 이름

    private String jpname;  //name

    private String jpcontent;   //내용

    private Timestamp jpregdate;    //공고 시작 시간

    private Timestamp jpenddate;    //공고 종료 시간(null 이면 고용인이 닫을 때까지)

    private boolean jpdelete;   //deflag(true = deleted)

    private int jpvacancies;    //모집 인원

    private int jphourlyRate;   //시급

    private String jpworkDays;  //근무 요일(월~일, 1이면 출근)

    private int jpminDuration;  //최소 근무 기간

    private int jpmaxDuration;  //최대 근무 기간(null 이면 제한 없음)

    private Time jpworkStartTime;   //근무 시작 시간

    private Time jpworkEndTime; //근무 종료 시간
}
