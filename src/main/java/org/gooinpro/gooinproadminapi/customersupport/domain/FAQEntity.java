package org.gooinpro.gooinproadminapi.customersupport.domain;

import jakarta.persistence.*;
import lombok.*;
import org.gooinpro.gooinproadminapi.admin.domain.AdminEntity;

import java.sql.Timestamp;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Table(name = "tbl_faq")
public class FAQEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long fno; // pk

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "admno")
    private AdminEntity admno; // fk 서비스 관리자

    @Column(nullable = false, columnDefinition = "VARCHAR(100)")
    private String ftitle; // 제목

    @Column(nullable = false, columnDefinition = "VARCHAR(500)")
    private String fcontent; // 문의 내용

    @Column(nullable = false, columnDefinition = "VARCHAR(50)")
    private String fcategory; // 카테고리

    @Column(columnDefinition = "BOOLEAN DEFAULT FALSE")
    private boolean fdelete; // 삭제 여부

    @Column(nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Timestamp fregdate; // 등록 날짜(시간)

    private Timestamp fmoddate; // 수정 날짜(시간)

}
