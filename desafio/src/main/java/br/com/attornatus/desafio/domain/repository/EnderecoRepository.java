package br.com.attornatus.desafio.domain.repository;

import br.com.attornatus.desafio.domain.model.Endereco;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, Long> {
    void deleteAllByPessoaId(Long id);
    Page<Endereco> findAllByPessoaId(Pageable pageable, Long id);
}
