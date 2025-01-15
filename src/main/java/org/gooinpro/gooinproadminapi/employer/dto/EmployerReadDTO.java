package org.gooinpro.gooinproadminapi.employer.dto;

import lombok.Builder;
import lombok.Data;

import java.sql.Date;
import java.sql.Timestamp;

@Data
@Builder
public class EmployerReadDTO {

    private Long eno;

    private String eemail;

    private String ename;

    private Date ebirth;

    private boolean egender;    //true = male, false = female

    private Timestamp eregdate; //계정 등록 시간
}
