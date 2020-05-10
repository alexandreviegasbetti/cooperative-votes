package br.com.cooperative.vote.controller;

import br.com.cooperative.vote.entity.Associate;
import br.com.cooperative.vote.entity.dto.AssociateDTO;
import br.com.cooperative.vote.service.AssociateService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/associate")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AssociateController {

    private final AssociateService service;

    @PostMapping
    public ResponseEntity<Associate> create(@RequestBody AssociateDTO associateDTO) {
        return ResponseEntity.status(CREATED).body(service.createNewAssociate(associateDTO));
    }

    @GetMapping
    public List<Associate> findAllAssociates() {
        return service.findAllAssociates();
    }
}
