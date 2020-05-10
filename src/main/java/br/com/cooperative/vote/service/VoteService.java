package br.com.cooperative.vote.service;

import br.com.cooperative.vote.entity.dto.CheckVoteDTO;
import br.com.cooperative.vote.entity.Vote;
import br.com.cooperative.vote.entity.dto.VoteDTO;
import br.com.cooperative.vote.entity.dto.VotesCountDTO;

import java.io.IOException;

public interface VoteService {
    Vote createNewVote(VoteDTO voteDTO);

    VotesCountDTO votesCount(Integer idVotingAgenda);

    CheckVoteDTO checkIfCanVote(String cpf) throws IOException;
}
