package org.gooinpro.gooinproadminapi.parttimer.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "tbl_partTImerDocumentImage")
public class PartTimerDocEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pdino;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pno")
    private PartTimerEntity pno;

    private String pdifilename;

}
