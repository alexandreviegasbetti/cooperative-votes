package br.com.cooperative.vote;

import br.com.cooperative.vote.entity.Associate;
import br.com.cooperative.vote.entity.dto.AssociateDTO;
import br.com.cooperative.vote.exception.CpfCannotBeNullException;
import br.com.cooperative.vote.exception.InvalidCpfException;
import br.com.cooperative.vote.exception.NameCannotBeNullException;
import br.com.cooperative.vote.repository.AssociateRepository;
import br.com.cooperative.vote.service.impl.AssociateServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class AssociateServiceImplTests {

    @InjectMocks
    public AssociateServiceImpl service;

    @Mock
    public AssociateRepository repository;

    @Test(expected = CpfCannotBeNullException.class)
    public void testsIfTheCpfIsNotNull() {

        AssociateDTO associateDTO = new AssociateDTO();
        associateDTO.setCpf(null);
        associateDTO.setName("Teste");

        Associate associate = Associate.builder()
                .cpf(associateDTO.getCpf())
                .name(associateDTO.getName())
                .build();

        service.createNewAssociate(associateDTO);

        verify(repository, times(0)).save(associate);

    }

    @Test(expected = InvalidCpfException.class)
    public void testsWhetherTheCpfFormatIsValid() {

        AssociateDTO associateDTO = new AssociateDTO();
        associateDTO.setCpf("12345678999");
        associateDTO.setName("Teste");

        Associate associate = Associate.builder()
                .cpf(associateDTO.getCpf())
                .name(associateDTO.getName())
                .build();

        service.createNewAssociate(associateDTO);

        verify(repository, times(0)).save(associate);

    }

    @Test(expected = NameCannotBeNullException.class)
    public void testsIfTheNameIsNotNull() {

        AssociateDTO associateDTO = new AssociateDTO();
        associateDTO.setCpf("65551909069");
        associateDTO.setName(null);

        Associate associate = Associate.builder()
                .cpf(associateDTO.getCpf())
                .name(associateDTO.getName())
                .build();

        service.createNewAssociate(associateDTO);

        verify(repository, times(0)).save(associate);

    }

    @Test
    public void testsTheCreationOfAnewAssociate() {

        AssociateDTO associateDTO = AssociateDTO.builder()
                .cpf("65551909069")
                .name("Associate Teste")
                .build();

        Associate associate = Associate.builder()
                .cpf(associateDTO.getCpf())
                .name(associateDTO.getName())
                .build();

        service.createNewAssociate(associateDTO);

        verify(repository, times(1)).save(associate);

    }
}
