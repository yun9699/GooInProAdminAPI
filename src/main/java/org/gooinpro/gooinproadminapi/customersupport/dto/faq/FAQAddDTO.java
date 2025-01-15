package org.gooinpro.gooinproadminapi.customersupport.dto.faq;

import lombok.Data;
import org.gooinpro.gooinproadminapi.admin.domain.AdminEntity;

import java.sql.Timestamp;

@Data
public class FAQAddDTO {

    private Long admno; // 서비스 관리자

    private String ftitle; // 제목

    private String fcontent; // 문의 내용

    private String fcategory; // 카테고리

    private boolean fdelete; // 삭제 여부

}
