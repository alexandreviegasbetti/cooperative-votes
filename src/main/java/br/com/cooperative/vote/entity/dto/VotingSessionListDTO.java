package br.com.cooperative.vote.entity.dto;

import br.com.cooperative.vote.entity.enums.VotingSessionStatus;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VotingSessionListDTO {

    private Integer id;

    private String start;

    private String end;

    private VotingSessionStatus status;

    private Integer idVotingAgenda;
}
