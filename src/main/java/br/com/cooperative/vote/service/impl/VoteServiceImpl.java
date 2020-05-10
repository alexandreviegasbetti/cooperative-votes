package br.com.cooperative.vote.service.impl;

import br.com.cooperative.vote.entity.Associate;
import br.com.cooperative.vote.entity.Vote;
import br.com.cooperative.vote.entity.VotingAgenda;
import br.com.cooperative.vote.entity.dto.CheckVoteDTO;
import br.com.cooperative.vote.entity.dto.VoteDTO;
import br.com.cooperative.vote.entity.dto.VotesCountDTO;
import br.com.cooperative.vote.entity.enums.VotingSessionStatus;
import br.com.cooperative.vote.exception.*;
import br.com.cooperative.vote.repository.AssociateRepository;
import br.com.cooperative.vote.repository.VoteRepository;
import br.com.cooperative.vote.repository.VotingAgendaRepository;
import br.com.cooperative.vote.service.VoteService;
import br.com.cooperative.vote.service.VotingSessionService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static br.com.cooperative.vote.util.RemoveAccents.removeAccents;
import static java.util.Objects.isNull;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class VoteServiceImpl implements VoteService {

    private final VoteRepository repository;

    private final VotingAgendaRepository votingAgendaRepository;

    private final AssociateRepository associateRepository;

    private final VotingSessionService votingSessionService;

    @Override
    public Vote createNewVote(VoteDTO voteDTO) {

        if (isNull(voteDTO.getCpf())) {
            throw new CpfCannotBeNullException("Please enter a CPF.");
        }

        if (isNull(voteDTO.getIdVotingAgenda())) {
            throw new IdVotingAgendaCannotBeNullException("Please enter a id voting agenda.");
        }

        if (isNull(voteDTO.getVote())) {
            throw new VoteCannotBeNullException("Please enter your vote.");
        }

        Optional<VotingAgenda> votingAgenda = votingAgendaRepository.findById(voteDTO.getIdVotingAgenda());

        if (!votingAgenda.isPresent()) {
            throw new EnterAValidIdForVotingAgendaException("The informed id for voting agenda is not valid.");
        }

        if (votingSessionService.checkStatus(votingAgenda.get().getVotingSession()).equals(VotingSessionStatus.CLOSE)) {
            throw new SessionVotingIsAlreadyClosedException("This section is closed to new votes.");
        }

        Associate associate = associateRepository.findByCpf(voteDTO.getCpf());

        if (repository.findByAssociate(associate).isPresent()) {
            throw new AssociateAlreadyVotedException("You already voted for this voting agenda.");
        }

        if (isNull(associate)) {
            throw new EnterAValidAssociateException("Informed a valid associate.");
        }

        String status = checkIfCanVote(voteDTO.getCpf()).getStatus();

        if (status.equals("UNABLE_TO_VOTE")) {
            throw new AssociateCannotVoteException(status);
        }

        Vote vote = Vote.builder()
                .Vote(removeAccents(voteDTO.getVote().toUpperCase()))
                .votingAgenda(votingAgenda.get())
                .associate(associate)
                .build();
        repository.save(vote);
        return vote;
    }

    @Override
    public VotesCountDTO votesCount(Integer idVotingAgenda) {

        Optional<VotingAgenda> votingAgenda = votingAgendaRepository.findById(idVotingAgenda);

        if (!votingAgenda.isPresent()) {
            throw new EnterAValidIdForVotingAgendaException("The informed id for voting agenda is not valid.");
        }

        List<Vote> votesList = repository.findAllByIdVotingAgenda(idVotingAgenda);

        List<Vote> votesYesList = votesList.stream().filter(x -> x.getVote().equals("SIM")).collect(Collectors.toList());

        VotingSessionStatus sessionStatus = votingSessionService.checkStatus(votingAgenda.get().getVotingSession());

        int votesYes = votesYesList.size();

        int votesNot = votesList.size() - votesYes;

        return VotesCountDTO.builder()
                .idVotingAgenda(votingAgenda.get().getId())
                .description(votingAgenda.get().getDescription())
                .votesYes(votesYes)
                .votesNot(votesNot)
                .sessionStatus(sessionStatus)
                .build();
    }

    @Override
    public CheckVoteDTO checkIfCanVote(String cpf) {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject("https://user-info.herokuapp.com/users/" + cpf, CheckVoteDTO.class);
    }

}
