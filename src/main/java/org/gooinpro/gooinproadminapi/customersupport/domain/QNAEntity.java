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
    private Long qno;

    @Column(nullable = false, columnDefinition = "VARCHAR(100)")
    private String qtitle;

    @Column(nullable = false, columnDefinition = "VARCHAR(500)")
    private String qcontent;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "eno")
    private EmployerEntity eno;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pno")
    private PartTimerEntity pno;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "admno")
    private AdminEntity admno;

    @Column(nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Timestamp qregdate;

    private Timestamp qmoddate;

    private Timestamp qanswerTime;

    @Column(columnDefinition = "BOOLEAN DEFAULT FALSE")
    private boolean qdelete;
}
