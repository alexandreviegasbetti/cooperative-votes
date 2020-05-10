package br.com.cooperative.vote;

import br.com.cooperative.vote.entity.Vote;
import br.com.cooperative.vote.entity.dto.VoteDTO;
import br.com.cooperative.vote.exception.CpfCannotBeNullException;
import br.com.cooperative.vote.exception.IdVotingAgendaCannotBeNullException;
import br.com.cooperative.vote.exception.VoteCannotBeNullException;
import br.com.cooperative.vote.repository.VoteRepository;
import br.com.cooperative.vote.service.impl.VoteServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class VoteServiceImplTests {

    @InjectMocks
    public VoteServiceImpl service;

    @Mock
    public VoteRepository repository;

    @Test(expected = CpfCannotBeNullException.class)
    public void testsIfTheCpfIsNotNull() {

        VoteDTO voteDTO = VoteDTO.builder()
                .cpf(null)
                .idVotingAgenda(1)
                .vote("sim")
                .build();

        Vote vote = new Vote();

        service.createNewVote(voteDTO);

        verify(repository, times(0)).save(vote);

    }

    @Test(expected = IdVotingAgendaCannotBeNullException.class)
    public void testsIfTheVotingAgendaIdIsNull() {

        VoteDTO voteDTO = VoteDTO.builder()
                .cpf("65551909069")
                .idVotingAgenda(null)
                .vote("sim")
                .build();

        Vote vote = new Vote();

        service.createNewVote(voteDTO);

        verify(repository, times(0)).save(vote);

    }

    @Test(expected = VoteCannotBeNullException.class)
    public void testsIfVoteIsNull() {

        VoteDTO voteDTO = VoteDTO.builder()
                .cpf("65551909069")
                .idVotingAgenda(1)
                .vote(null)
                .build();

        Vote vote = new Vote();

        service.createNewVote(voteDTO);

        verify(repository, times(0)).save(vote);

    }


}
