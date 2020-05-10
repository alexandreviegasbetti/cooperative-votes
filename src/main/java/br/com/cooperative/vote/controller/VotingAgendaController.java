package br.com.cooperative.vote.controller;

import br.com.cooperative.vote.entity.VotingAgenda;
import br.com.cooperative.vote.service.VotingAgendaService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/votingAgenda")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class VotingAgendaController {

    private final VotingAgendaService service;

    @PostMapping
    public ResponseEntity<VotingAgenda> create(@RequestBody VotingAgenda votingAgenda) {
        return ResponseEntity.status(CREATED).body(service.createVotingAgenda(votingAgenda));
    }

    @GetMapping
    public List<VotingAgenda> findAll() {
        return service.findAllVotingAgenda();
    }
}
