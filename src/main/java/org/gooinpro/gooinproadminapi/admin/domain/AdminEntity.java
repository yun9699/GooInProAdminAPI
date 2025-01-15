package org.gooinpro.gooinproadminapi.admin.domain;

import jakarta.persistence.*;
import lombok.*;
import java.sql.Timestamp;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name = "tbl_admin")
public class AdminEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long admno;

    @Column(length= 100, nullable = false)
    private String admid;

    @Column(length= 100, nullable = false)
    private String admpw;

    @Column(length= 50, nullable = false)
    private String admname;

    @Column(length= 30, nullable = false)
    private String admrole;

    @Column(columnDefinition = "boolean default false", nullable = false)
    private boolean admdelete;

    @Column(columnDefinition = "timestamp default now()", nullable = false)
    private Timestamp admregdate;
}