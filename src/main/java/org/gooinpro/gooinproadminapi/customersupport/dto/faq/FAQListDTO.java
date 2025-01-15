package org.gooinpro.gooinproadminapi.customersupport.dto.faq;

import lombok.Data;
import org.gooinpro.gooinproadminapi.admin.domain.AdminEntity;

import java.sql.Timestamp;

@Data
public class FAQListDTO {

    private Long fno; // pk

    private String admname; // 서비스관리자 이름

    private String ftitle; // 제목

    private String fcategory; // 카테고리

    private boolean fdelete; // 삭제 여부


}
