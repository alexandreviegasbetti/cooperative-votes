package br.com.cooperative.vote.service.impl;

import br.com.cooperative.vote.entity.VotingAgenda;
import br.com.cooperative.vote.entity.VotingSession;
import br.com.cooperative.vote.entity.dto.VotingSessionDTO;
import br.com.cooperative.vote.entity.dto.VotingSessionListDTO;
import br.com.cooperative.vote.entity.enums.VotingSessionStatus;
import br.com.cooperative.vote.exception.VotingAgendaIdCannotBeNullException;
import br.com.cooperative.vote.repository.VotingAgendaRepository;
import br.com.cooperative.vote.repository.VotingSessionRepository;
import br.com.cooperative.vote.service.VotingSessionService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class VotingSessionServiceImpl implements VotingSessionService {

    private final VotingSessionRepository repository;

    private final VotingAgendaRepository votingAgendaRepository;

    @Override
    public VotingSession createVoteSession(VotingSessionDTO votingSession) {

        if (isNull(votingSession.getIdVotingAgenda())) {
            throw new VotingAgendaIdCannotBeNullException("Inform the voting agenda id.");
        }

        VotingAgenda votingAgenda = votingAgendaRepository.findById(votingSession.getIdVotingAgenda())
                .orElse(null);

        long minutes = 1L;

        if (nonNull(votingSession.getMinutes())) {
            minutes = votingSession.getMinutes();
        }

        LocalDateTime start = LocalDateTime.now();

        return repository.save(VotingSession.builder()
                .sessionStart(start)
                .endOfSession(start.plusMinutes(minutes))
                .votingAgenda(votingAgenda)
                .build());
    }

    @Override
    public List<VotingSessionListDTO> listAll() {

        List<VotingSession> list = repository.findAll();

        List<VotingSessionListDTO> dtoList = new ArrayList<>();

        list.forEach(session -> {
            dtoList.add(VotingSessionListDTO.builder()
                    .id(session.getId())
                    .start(dateTimeFormatter(session.getSessionStart()))
                    .end(dateTimeFormatter(session.getEndOfSession()))
                    .status(checkStatus(session))
                    .idVotingAgenda(session.getVotingAgenda().getId())
                    .build());
        });

        dtoList.sort(Comparator.comparing(x -> {
            if (VotingSessionStatus.OPEN.equals(x.getStatus())) {
                return -1;
            } else if (VotingSessionStatus.CLOSE.equals(x.getStatus())) {
                return 1;
            } else {
                return 0;
            }
        }));

        return dtoList;
    }

    @Override
    public VotingSessionListDTO findById(Integer id) {
        VotingSession session = repository.findById(id).orElse(null);
        if (nonNull(session)) {
            return VotingSessionListDTO.builder()
                    .id(session.getId())
                    .start(dateTimeFormatter(session.getSessionStart()))
                    .end(dateTimeFormatter(session.getEndOfSession()))
                    .status(checkStatus(session))
                    .idVotingAgenda(session.getVotingAgenda().getId())
                    .build();
        }
        return null;
    }

    @Override
    public VotingSessionStatus checkStatus(VotingSession session) {
        VotingSessionStatus status;
        if (session.getEndOfSession().isBefore(LocalDateTime.now())) {
            return status = VotingSessionStatus.CLOSE;
        } else {
            return status = VotingSessionStatus.OPEN;
        }
    }

    private String dateTimeFormatter(LocalDateTime localDateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        return localDateTime.format(formatter);
    }
}
