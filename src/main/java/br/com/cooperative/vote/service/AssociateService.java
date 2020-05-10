package br.com.cooperative.vote.service;

import br.com.cooperative.vote.entity.Associate;
import br.com.cooperative.vote.entity.dto.AssociateDTO;

import java.util.List;

public interface AssociateService {
    Associate createNewAssociate(AssociateDTO associateDTO);

    List<Associate> findAllAssociates();
}
