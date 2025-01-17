package org.gooinpro.gooinproadminapi.customersupport.dto.faq;

import lombok.Data;
import org.gooinpro.gooinproadminapi.admin.domain.AdminEntity;

import java.sql.Timestamp;

@Data
public class FAQDTO {

    private Long fno; // pk

    private AdminEntity admno; // 서비스관리자 fk

    private String ftitle; // 제목

    private String fcontent; // 문의 내용

    private String fcategory; // 카테고리

    private boolean fdelete; // 삭제 여부

    private Timestamp fregdate; // 등록 날짜(시간)

    private Timestamp fmoddate; // 수정 날짜(시간)

}
