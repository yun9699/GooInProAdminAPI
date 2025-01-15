package org.gooinpro.gooinproadminapi.parttimer.domain;

import jakarta.persistence.*;
import lombok.*;
import java.sql.Timestamp;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "tbl_partTimer")
public class PartTimerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long pno;

    @Column(nullable = false, columnDefinition = "VARCHAR(100)")
    private String pemail;

    @Column(nullable = false, columnDefinition = "VARCHAR(100)")
    private String ppw;

    @Column(nullable = false, columnDefinition = "VARCHAR(50)")
    private String pname;

    @Column(nullable = false)
    private Timestamp pbirth;

    @Column(nullable = false)
    private boolean pgender;

    @Column(columnDefinition = "BOOLEAN DEFAULT FALSE")
    private boolean pdelete;

    @Column(nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Timestamp pregdate;

    @Column(columnDefinition = "VARCHAR(100)")
    private String proadAddress;

    @Column(columnDefinition = "VARCHAR(100)")
    private String pdtailAddress;


}
