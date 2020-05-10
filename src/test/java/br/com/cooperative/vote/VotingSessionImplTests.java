package br.com.cooperative.vote;

import br.com.cooperative.vote.entity.VotingSession;
import br.com.cooperative.vote.entity.dto.VotingSessionDTO;
import br.com.cooperative.vote.exception.VotingAgendaIdCannotBeNullException;
import br.com.cooperative.vote.repository.VotingSessionRepository;
import br.com.cooperative.vote.service.impl.VotingSessionServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDateTime;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class VotingSessionImplTests {

    @InjectMocks
    public VotingSessionServiceImpl service;

    @Mock
    public VotingSessionRepository repository;

    @Test(expected = VotingAgendaIdCannotBeNullException.class)
    public void testsIfTheVotingAgendaIdIsNull() {

        VotingSessionDTO votingSessionDTO = VotingSessionDTO.builder()
                .idVotingAgenda(null)
                .minutes(10L)
                .build();

        VotingSession votingSession = VotingSession.builder()
                .votingAgenda(null)
                .sessionStart(LocalDateTime.now())
                .endOfSession(LocalDateTime.now().plusMinutes(10))
                .build();

        service.createVoteSession(votingSessionDTO);

        verify(repository, times(0)).save(votingSession);
    }
}
