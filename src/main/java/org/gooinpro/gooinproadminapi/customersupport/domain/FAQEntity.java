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
@Table(name = "tbl_faq")
public class FAQEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long fno;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "admno")
    private AdminEntity admno;

    @Column(nullable = false, columnDefinition = "VARCHAR(100)")
    private String ftitle;

    @Column(nullable = false, columnDefinition = "VARCHAR(500)")
    private String fcontent;

    @Column(nullable = false, columnDefinition = "VARCHAR(50)")
    private String fcategory;

    @Column(columnDefinition = "BOOLEAN DEFAULT FALSE")
    private boolean fdelete;

    @Column(nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Timestamp fregdate;

    private Timestamp fmoddate;

}
