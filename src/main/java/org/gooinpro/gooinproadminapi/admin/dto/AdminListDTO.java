package org.gooinpro.gooinproadminapi.admin.dto;

import lombok.*;

import java.sql.Timestamp;

@Getter
@Builder
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AdminListDTO {

    private Long admno;
    private String admid;
    private String admname;
    private String admrole;
    private boolean admdelete;
    private Timestamp admregdate;
}
