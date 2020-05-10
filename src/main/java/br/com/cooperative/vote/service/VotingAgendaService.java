package br.com.cooperative.vote.service;

import br.com.cooperative.vote.entity.VotingAgenda;

import java.util.List;

public interface VotingAgendaService {

    VotingAgenda createVotingAgenda(VotingAgenda votingAgenda);

    List<VotingAgenda> findAllVotingAgenda();
}
