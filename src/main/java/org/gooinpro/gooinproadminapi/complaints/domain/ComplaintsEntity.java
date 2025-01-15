package org.gooinpro.gooinproadminapi.complaints.domain;

import jakarta.persistence.*;
import lombok.*;
import org.gooinpro.gooinproadminapi.employer.domain.EmployerEntity;
import org.gooinpro.gooinproadminapi.parttimer.domain.PartTimerEntity;

import java.sql.Timestamp;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "tbl_complaints")
public class ComplaintsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long cno;

    @Column(nullable = false, columnDefinition = "VARCHAR(100)")
    private String cname;

    @Column(nullable = false, columnDefinition = "VARCHAR(500)")
    private String ccontent;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "eno")
    private EmployerEntity eno;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pno")
    private PartTimerEntity pno;

    @Column(nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Timestamp cregdate;

    private Timestamp ccheckedTime;

    @Column(columnDefinition = "BOOLEAN DEFAULT FALSE")
    private boolean cdelete;

}
