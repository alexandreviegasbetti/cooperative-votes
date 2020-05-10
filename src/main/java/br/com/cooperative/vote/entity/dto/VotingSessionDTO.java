package br.com.cooperative.vote.entity.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VotingSessionDTO {

    Long minutes;

    Integer idVotingAgenda;

}
