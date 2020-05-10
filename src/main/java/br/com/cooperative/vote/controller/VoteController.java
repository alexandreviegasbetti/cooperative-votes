package br.com.cooperative.vote.controller;

import br.com.cooperative.vote.entity.Vote;
import br.com.cooperative.vote.entity.dto.VoteDTO;
import br.com.cooperative.vote.entity.dto.VotesCountDTO;
import br.com.cooperative.vote.service.VoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/vote")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class VoteController {

    private final VoteService service;

    @PostMapping
    public ResponseEntity<Vote> create(@RequestBody VoteDTO voteDTO) {
        return ResponseEntity.status(CREATED).body(service.createNewVote(voteDTO));
    }

    @GetMapping(path = "/result/{idVotingAgenda}")
    public VotesCountDTO votesCount(@PathVariable Integer idVotingAgenda) {
        return service.votesCount(idVotingAgenda);
    }

}
