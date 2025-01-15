package org.gooinpro.gooinproadminapi.complaints.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "tbl_complaintsImage")
public class ComplaintsImageEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long cino;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cno")
    private ComplaintsEntity cno;

    @Column(nullable = false, columnDefinition = "VARCHAR(100)")
    private String cifilename;
}
