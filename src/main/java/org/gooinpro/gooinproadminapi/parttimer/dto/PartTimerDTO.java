package org.gooinpro.gooinproadminapi.parttimer.dto;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class PartTimerDTO {

    private Long pno;

    private String pemail;

    private String ppw;

    private String pname;

    private Timestamp pbirth;

    private boolean pgender;

    private boolean pdelete;

    private Timestamp pregdate;

    private String proadAddress;

    private String pdtailAddress;
}
