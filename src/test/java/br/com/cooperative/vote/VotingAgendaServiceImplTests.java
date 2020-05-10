package br.com.cooperative.vote;

import br.com.cooperative.vote.entity.VotingAgenda;
import br.com.cooperative.vote.exception.DescriptionCannotBeNullException;
import br.com.cooperative.vote.repository.VotingAgendaRepository;
import br.com.cooperative.vote.service.impl.VotingAgendaServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class VotingAgendaServiceImplTests {

    @InjectMocks
    public VotingAgendaServiceImpl service;

    @Mock
    public VotingAgendaRepository repository;

    @Test(expected = DescriptionCannotBeNullException.class)
    public void testsIfTheDescriptionIsNotNull() {

        VotingAgenda votingAgenda = VotingAgenda.builder()
                .description(null)
                .build();

        service.createVotingAgenda(votingAgenda);

        verify(repository, times(0)).save(votingAgenda);

    }

    @Test
    public void testsTheCreationOfAnewVotingAgenda() {

        VotingAgenda votingAgenda = VotingAgenda.builder()
                .description("description")
                .build();

        service.createVotingAgenda(votingAgenda);

        verify(repository, times(1)).save(votingAgenda);

    }
}
