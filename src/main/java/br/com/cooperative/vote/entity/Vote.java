package br.com.cooperative.vote.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Builder
@Table(name = "vote")
public class Vote {

    @Id
    @Column(name = "id_vote")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "vote", nullable = true)
    private String Vote;

    @ManyToOne
    @JoinColumn(name = "id_voting_agenda", referencedColumnName = "id_voting_agenda")
    private VotingAgenda votingAgenda;

    @ManyToOne
    @JoinColumn(name = "id_associate", referencedColumnName = "id_associate")
    private Associate associate;
}
