package br.com.cooperative.vote.entity.dto;

import br.com.cooperative.vote.entity.enums.VotingSessionStatus;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VotesCountDTO {

    private Integer votesYes;

    private Integer votesNot;

    private Integer idVotingAgenda;

    private String description;

    private VotingSessionStatus sessionStatus;

}
