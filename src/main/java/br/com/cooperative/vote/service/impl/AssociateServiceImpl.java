package br.com.cooperative.vote.service.impl;

import br.com.cooperative.vote.entity.Associate;
import br.com.cooperative.vote.entity.dto.AssociateDTO;
import br.com.cooperative.vote.exception.CpfCannotBeNullException;
import br.com.cooperative.vote.exception.InvalidCpfException;
import br.com.cooperative.vote.exception.NameCannotBeNullException;
import br.com.cooperative.vote.exception.UserAlreadyRegisteredException;
import br.com.cooperative.vote.repository.AssociateRepository;
import br.com.cooperative.vote.service.AssociateService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static br.com.cooperative.vote.util.ValidatesCpf.isCPF;
import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AssociateServiceImpl implements AssociateService {

    private final AssociateRepository repository;

    @Override
    public Associate createNewAssociate(AssociateDTO associateDTO) {

        if (isNull(associateDTO.getCpf())) {
            throw new CpfCannotBeNullException("Please enter a CPF.");
        }

        if (!isCPF(associateDTO.getCpf())) {
            throw new InvalidCpfException("Please enter a valid CPF.");
        }

        if (isNull(associateDTO.getName())) {
            throw new NameCannotBeNullException("Please enter a name.");
        }

        if (nonNull(repository.findByCpf(associateDTO.getCpf()))){
            throw new UserAlreadyRegisteredException("There is already a user with this cpf.");
        }

        return repository.save(Associate.builder()
                .name(associateDTO.getName())
                .cpf(associateDTO.getCpf())
                .build());
    }

    @Override
    public List<Associate> findAllAssociates() {
        return repository.findAll();
    }

}
