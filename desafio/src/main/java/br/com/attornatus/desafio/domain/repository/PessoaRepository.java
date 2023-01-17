package br.com.attornatus.desafio.domain.repository;

import br.com.attornatus.desafio.domain.model.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Long> {
}
