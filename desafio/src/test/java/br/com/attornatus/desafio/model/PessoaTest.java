package br.com.attornatus.desafio.model;

import br.com.attornatus.desafio.domain.dto.PessoaDTO;
import br.com.attornatus.desafio.domain.model.Pessoa;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;

import java.lang.reflect.InvocationTargetException;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PessoaTest {

    private ModelMapper mapper = new ModelMapper();

    @Test
    void testandoParsePessoaParaPessoaDTO(){

        Pessoa pessoa = new Pessoa("Rafael", LocalDate.of(1992,6,8));
        PessoaDTO dto = mapper.map(pessoa, PessoaDTO.class);
        assertEquals(PessoaDTO.class, dto.getClass());
    }

    @Test
    void testandoMetodosDaClassePessoa() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {

        Pessoa pessoa = new Pessoa("Rafael", LocalDate.of(1992,6,8));

        assertEquals("Rafael", pessoa.getClass().getMethod("getNome").invoke(pessoa));
        assertEquals(LocalDate.of(1992,6,8), pessoa.getClass().getMethod("getDataNascimento").invoke(pessoa));
        pessoa.setNome("Bessa");
        assertEquals("Bessa", pessoa.getNome());

    }



}
