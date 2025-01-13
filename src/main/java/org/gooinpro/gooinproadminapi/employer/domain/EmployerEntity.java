package org.gooinpro.gooinproadminapi.employer.domain;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Date;
import java.sql.Timestamp;


@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "tbl_employer")
public class EmployerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long eno;   //PK

    private String eemail;   //email

    private String epw; //password

    private String ename;   //name

    private Date ebirth;    //birth

    private boolean egender;    //gender(true = male, false = female)

    @Column(columnDefinition = "boolean default false")
    private boolean edelete = false;    //delflag(false = deleted)

    @Column(columnDefinition = "boolean default false")
    private boolean eapproved = false;  //승인 여부(false = not Approved)

    @Column(columnDefinition = "timestamp default now()")
    private Timestamp eregdate; //등록 시간
}
