package br.com.cooperative.vote.service.impl;

import br.com.cooperative.vote.entity.VotingAgenda;
import br.com.cooperative.vote.exception.DescriptionCannotBeNullException;
import br.com.cooperative.vote.repository.VotingAgendaRepository;
import br.com.cooperative.vote.service.VotingAgendaService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.Objects.isNull;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class VotingAgendaServiceImpl implements VotingAgendaService {

    private final VotingAgendaRepository repository;

    @Override
    public VotingAgenda createVotingAgenda(VotingAgenda votingAgenda) {

        if (isNull(votingAgenda.getDescription())) {
            throw new DescriptionCannotBeNullException("Enter a description for voting agenda.");
        }

        VotingAgenda newVotingAgenda = VotingAgenda.builder()
                .description(votingAgenda.getDescription())
                .build();

        repository.save(newVotingAgenda);
        return newVotingAgenda;
    }

    @Override
    public List<VotingAgenda> findAllVotingAgenda() {
        return repository.findAll();
    }
}
