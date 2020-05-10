package br.com.cooperative.vote.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Builder
@Table(name = "voting_session")
public class VotingSession {

    @Id
    @Column(name = "id_voting_session")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "session_start", nullable = true)
    private LocalDateTime sessionStart;

    @Column(name = "end_of_session", nullable = true)
    private LocalDateTime endOfSession;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_voting_agenda")
    private VotingAgenda votingAgenda;

}
