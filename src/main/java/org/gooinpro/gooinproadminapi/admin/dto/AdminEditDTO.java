package org.gooinpro.gooinproadminapi.admin.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AdminEditDTO {

    private Long admno;
    private String admpw;
    private String admname;
    private String admrole;
}
