package br.com.cooperative.vote.service;

import br.com.cooperative.vote.entity.VotingSession;
import br.com.cooperative.vote.entity.dto.VotingSessionDTO;
import br.com.cooperative.vote.entity.dto.VotingSessionListDTO;
import br.com.cooperative.vote.entity.enums.VotingSessionStatus;

import java.util.List;

public interface VotingSessionService {
    VotingSession createVoteSession(VotingSessionDTO votingSession);

    List<VotingSessionListDTO> listAll();

    VotingSessionListDTO findById(Integer id);

    VotingSessionStatus checkStatus(VotingSession session);
}
