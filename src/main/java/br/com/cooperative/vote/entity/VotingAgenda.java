package br.com.cooperative.vote.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Builder
@Table(name = "voting_agenda")
public class VotingAgenda {

    @Id
    @Column(name = "id_voting_agenda")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "description", nullable = true)
    private String description;

    @JsonIgnore
    @OneToMany(mappedBy = "votingAgenda")
    private List<Vote> votes;

    @JsonIgnore
    @OneToOne(mappedBy = "votingAgenda")
    private VotingSession votingSession;

}
