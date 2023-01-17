package br.com.attornatus.desafio.repository;

import br.com.attornatus.desafio.domain.model.Pessoa;
import br.com.attornatus.desafio.domain.repository.PessoaRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;


@DataJpaTest
public class PessoaRepositoryTest {

    @Autowired
    private PessoaRepository pessoaRepository;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    void deveriaDevolverTodasPessoas() {

        Pessoa pessoa1 = new Pessoa( "Rafael", LocalDate.of(1992,6,8));
        Pessoa pessoa2 = new Pessoa( "RafaelBessa", LocalDate.of(1992,6,8));
        Pessoa pessoa3 = new Pessoa( "Bessa", LocalDate.of(1992,6,8));

        entityManager.persist(pessoa1);
        entityManager.persist(pessoa2);
        entityManager.persist(pessoa3);

        List<Pessoa> lista = pessoaRepository.findAll();

        assertEquals(3, lista.size());
        assertNotNull(lista);
    }

    @Test
    void deveriaDevolverUmaPessoa() {

        Pessoa pessoa1 = new Pessoa( "Rafael", LocalDate.of(1992,6,8));
        Pessoa pessoa2 = new Pessoa( "RafaelBessa", LocalDate.of(1992,6,8));
        Pessoa pessoa3 = new Pessoa( "Bessa", LocalDate.of(1992,6,8));

        entityManager.persist(pessoa1);
        entityManager.persist(pessoa2);
        entityManager.persist(pessoa3);

        Optional<Pessoa> pessoa = pessoaRepository.findById(pessoa2.getId());

        assertEquals("RafaelBessa", pessoa.get().getNome());
        assertNotNull(pessoa);
    }

}
