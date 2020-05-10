package br.com.cooperative.vote.repository;

import br.com.cooperative.vote.entity.VotingSession;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VotingSessionRepository extends JpaRepository<VotingSession, Integer> {
}
