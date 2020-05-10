package br.com.cooperative.vote.repository;

import br.com.cooperative.vote.entity.VotingAgenda;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VotingAgendaRepository extends JpaRepository<VotingAgenda, Integer> {

}
