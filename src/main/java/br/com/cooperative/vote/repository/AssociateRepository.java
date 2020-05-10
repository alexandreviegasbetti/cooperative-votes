package br.com.cooperative.vote.repository;

import br.com.cooperative.vote.entity.Associate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AssociateRepository extends JpaRepository<Associate, Integer> {

    Associate findByCpf(String cpf);
}
