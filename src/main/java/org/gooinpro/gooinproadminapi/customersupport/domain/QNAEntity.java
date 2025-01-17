package org.gooinpro.gooinproadminapi.customersupport.domain;

import jakarta.persistence.*;
import lombok.*;
import org.gooinpro.gooinproadminapi.admin.domain.AdminEntity;
import org.gooinpro.gooinproadminapi.employer.domain.EmployerEntity;
import org.gooinpro.gooinproadminapi.parttimer.domain.PartTimerEntity;

import java.sql.Timestamp;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "tbl_qna")
public class QNAEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long qno; // pk

    @Column(nullable = false, columnDefinition = "VARCHAR(100)")
    private String qtitle; // 제목

    @Column(nullable = false, columnDefinition = "VARCHAR(500)")
    private String qcontent; // 문의 내용

    @Column(columnDefinition = "VARCHAR(500)")
    private String qanswer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "eno")
    private EmployerEntity eno; // fk 고용인

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pno")
    private PartTimerEntity pno; // fk 근로자

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "admno")
    private AdminEntity admno; // fk 서비스 관리자

    @Column(nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Timestamp qregdate; // 등록 날짜(시간)

    private Timestamp qmoddate; // 수정 날짜(시간)

    private Timestamp qanswerTime; // 답변 시간

    @Column(columnDefinition = "BOOLEAN DEFAULT FALSE")
    private boolean qdelete; // 삭제 여부

    @Column(columnDefinition = "BOOLEAN DEFAULT FALSE")
    private boolean qstatus; // 답변 여부
}
