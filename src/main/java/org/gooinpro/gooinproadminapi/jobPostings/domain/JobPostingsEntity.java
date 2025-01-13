package org.gooinpro.gooinproadminapi.jobPostings.domain;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Time;
import java.sql.Timestamp;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "tbl_jobPostings")
public class JobPostingsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long jpno;  //PK

    @Column(nullable = false)
    private String jpname;  //name

    private String jpcontent;   //내용

    @Column(columnDefinition = "timestamp default now()", nullable = false)
    private Timestamp jpregdate;    //공고 시작 시간

    private Timestamp jpenddate;    //공고 종료 시간(null 이면 고용인이 닫을 때까지)

    @Column(columnDefinition = "boolean default false", nullable = false)
    private boolean jpdelete = false;   //deflag(true = deleted)

    @Column(columnDefinition = "int default 0", nullable = false)
    private int jpvacancies = 0;    //모집 인원

    @Column(nullable = false)
    private int jphourlyRate;   //시급

    @Column(columnDefinition = "varchar(7) default '0000000'", nullable = false)
    private String jpworkDays = "0000000";  //근무 요일(월~일, 1이면 출근)

    @Column(nullable = false)
    private int jpminDuration;  //최소 근무 기간

    private int jpmaxDuration;  //최대 근무 기간(null 이면 제한 없음)

    private Time jpworkStartTime;   //근무 시작 시간

    private Time jpworkEndTime; //근무 종료 시간

}
