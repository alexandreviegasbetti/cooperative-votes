package br.com.cooperative.vote.repository;

import br.com.cooperative.vote.entity.Associate;
import br.com.cooperative.vote.entity.Vote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface VoteRepository extends JpaRepository<Vote, Integer> {

    Optional<Vote> findByAssociate(Associate associate);

    @Query("SELECT v FROM Vote v " +
            "WHERE v.votingAgenda.id = :idVotingAgenda")
    List<Vote> findAllByIdVotingAgenda(Integer idVotingAgenda);
}
