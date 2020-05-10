package br.com.cooperative.vote.entity.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VoteDTO {

    private String cpf;

    private String vote;

    private Integer idVotingAgenda;

}
