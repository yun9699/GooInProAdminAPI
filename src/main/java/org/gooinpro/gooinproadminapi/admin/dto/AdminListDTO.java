package org.gooinpro.gooinproadminapi.admin.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import java.sql.Timestamp;

@Getter
@Builder
@AllArgsConstructor
public class AdminListDTO {

    private Long admno;
    private String admid;
    private String admname;
    private String admrole;
    private boolean admdelete;
    private Timestamp admregdate;
}
