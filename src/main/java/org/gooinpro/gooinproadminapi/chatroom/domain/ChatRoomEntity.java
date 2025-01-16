package org.gooinpro.gooinproadminapi.chatroom.domain;

import jakarta.persistence.*;
import lombok.*;
import org.gooinpro.gooinproadminapi.employer.domain.EmployerEntity;
import org.gooinpro.gooinproadminapi.parttimer.domain.PartTimerEntity;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "tbl_chatroom")
public class ChatRoomEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long rno;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "eno")
    private EmployerEntity employer;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pno")
    private PartTimerEntity partTimer;

}
