package br.com.cooperative.vote.controller;

import br.com.cooperative.vote.entity.VotingSession;
import br.com.cooperative.vote.entity.dto.VotingSessionDTO;
import br.com.cooperative.vote.entity.dto.VotingSessionListDTO;
import br.com.cooperative.vote.service.VotingSessionService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/votingSession")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class VotingSessionController {

    private final VotingSessionService service;

    @PostMapping
    public ResponseEntity<VotingSession> create(@RequestBody VotingSessionDTO votingSessionDTO) {
        return ResponseEntity.status(CREATED).body(service.createVoteSession(votingSessionDTO));
    }

    @GetMapping
    public List<VotingSessionListDTO> listAll() {
        return service.listAll();
    }

    @GetMapping(path = "/{id}")
    public VotingSessionListDTO findById(@PathVariable Integer id) {
        return service.findById(id);
    }
}
