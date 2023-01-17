package br.com.attornatus.desafio.controller;

import br.com.attornatus.desafio.domain.model.Endereco;
import br.com.attornatus.desafio.domain.model.Pessoa;
import br.com.attornatus.desafio.domain.repository.EnderecoRepository;
import br.com.attornatus.desafio.domain.repository.PessoaRepository;
import br.com.attornatus.desafio.service.PessoaService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.net.URI;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@AutoConfigureMockMvc
public class PessoaControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private PessoaService pessoaService;
    @Autowired
    private PessoaRepository pessoaRepository;
    @Autowired
    private EnderecoRepository enderecoRepository;

    @Test
    void deveriaBuscarUmaPessoaPeloID() throws Exception {

        List<Endereco> enderecos = new ArrayList<>();

        Endereco endereco = new Endereco("Rua de Teste", "03715-500", "144", "São Paulo");
        enderecos.add(endereco);
        enderecoRepository.save(endereco);

        Pessoa pessoa = new Pessoa("Rafael", LocalDate.of(1992,6,8));
        pessoaRepository.save(pessoa);

        enderecos.get(0).setPessoa(pessoa);
        pessoa.setEnderecos(enderecos);

        URI uri = new URI("/pessoas/1");

        String conteudo = "{\n" +
                "\t\"id\": 1,\n" +
                "\t\"nome\": \"Rafael\",\n" +
                "\t\"dataNascimento\": \"08/06/1992\",\n" +
                "\t\"enderecos\": [\n" +
                "\t\t{\n" +
                "\t\t\t\"logradouro\": \"Rua de Teste\",\n" +
                "\t\t\t\"cep\": \"03715-500\",\n" +
                "\t\t\t\"numero\": \"144\",\n" +
                "\t\t\t\"cidade\": \"São Paulo\"\n" +
                "\t\t}\n" +
                "\t],\n" +
                "\t\"enderecoPrincipal\": null\n" +
                "}";

        mockMvc
                .perform(MockMvcRequestBuilders
                        .get(uri))
                .andExpect(MockMvcResultMatchers
                        .status()
                .isOk())
                .andExpect(MockMvcResultMatchers.content()
                .json(conteudo));
    }

    @Test
    void deveriaBuscarTodasAsPessoasComPaginacao() throws Exception {

        List<Endereco> enderecos1 = new ArrayList<>();
        List<Endereco> enderecos2 = new ArrayList<>();

        Endereco endereco1 = new Endereco("Rua de Teste", "03715-500", "144", "São Paulo");
        Endereco endereco2 = new Endereco ("Rua de Teste", "03715-500", "144", "São Paulo");

        enderecos1.add(endereco1);
        enderecos2.add(endereco2);

        Pessoa pessoa1 = new Pessoa( "Rafael", LocalDate.of(1992,6,8));
        Pessoa pessoa2 = new Pessoa( "Bessa", LocalDate.of(1992,6,8));

        enderecos1.get(0).setPessoa(pessoa1);
        enderecos2.get(0).setPessoa(pessoa2);
        pessoa1.setEnderecos(enderecos1);
        pessoa2.setEnderecos(enderecos2);

        pessoaRepository.save(pessoa1);
        pessoaRepository.save(pessoa2);

        URI uri = new URI("/pessoas");

        String conteudo ="{\n" +
                "\t\"content\": [\n" +
                "\t\t{\n" +
                "\t\t\t\"id\": 2,\n" +
                "\t\t\t\"nome\": \"Bessa\",\n" +
                "\t\t\t\"dataNascimento\": \"08/06/1992\",\n" +
                "\t\t\t\"enderecos\": [\n" +
                "\t\t\t\t{\n" +
                "\t\t\t\t\t\"logradouro\": \"Rua de Teste\",\n" +
                "\t\t\t\t\t\"cep\": \"03715-500\",\n" +
                "\t\t\t\t\t\"numero\": \"144\",\n" +
                "\t\t\t\t\t\"cidade\": \"São Paulo\"\n" +
                "\t\t\t\t}\n" +
                "\t\t\t],\n" +
                "\t\t\t\"enderecoPrincipal\": null\n" +
                "\t\t},\n" +
                "\t\t{\n" +
                "\t\t\t\"id\": 1,\n" +
                "\t\t\t\"nome\": \"Rafael\",\n" +
                "\t\t\t\"dataNascimento\": \"08/06/1992\",\n" +
                "\t\t\t\"enderecos\": [\n" +
                "\t\t\t\t{\n" +
                "\t\t\t\t\t\"logradouro\": \"Rua de Teste\",\n" +
                "\t\t\t\t\t\"cep\": \"03715-500\",\n" +
                "\t\t\t\t\t\"numero\": \"144\",\n" +
                "\t\t\t\t\t\"cidade\": \"São Paulo\"\n" +
                "\t\t\t\t}\n" +
                "\t\t\t],\n" +
                "\t\t\t\"enderecoPrincipal\": null\n" +
                "\t\t}\n" +
                "\t],\n" +
                "\t\"pageable\": {\n" +
                "\t\t\"sort\": {\n" +
                "\t\t\t\"empty\": false,\n" +
                "\t\t\t\"sorted\": true,\n" +
                "\t\t\t\"unsorted\": false\n" +
                "\t\t},\n" +
                "\t\t\"offset\": 0,\n" +
                "\t\t\"pageSize\": 5,\n" +
                "\t\t\"pageNumber\": 0,\n" +
                "\t\t\"paged\": true,\n" +
                "\t\t\"unpaged\": false\n" +
                "\t},\n" +
                "\t\"last\": true,\n" +
                "\t\"totalElements\": 2,\n" +
                "\t\"totalPages\": 1,\n" +
                "\t\"size\": 5,\n" +
                "\t\"number\": 0,\n" +
                "\t\"sort\": {\n" +
                "\t\t\"empty\": false,\n" +
                "\t\t\"sorted\": true,\n" +
                "\t\t\"unsorted\": false\n" +
                "\t},\n" +
                "\t\"first\": true,\n" +
                "\t\"numberOfElements\": 2,\n" +
                "\t\"empty\": false\n" +
                "}";

        mockMvc
                .perform(MockMvcRequestBuilders
                        .get(uri))
                .andExpect(MockMvcResultMatchers
                        .status()
                        .isOk())
                .andExpect(MockMvcResultMatchers.content()
                        .json(conteudo));
    }

    @Test
    void deveriaDevolverNotFoundNaBuscaDeUmaPessoaComIDInexistente() throws Exception {

        URI uri = new URI("/pessoas/1000");

        mockMvc
                .perform(MockMvcRequestBuilders
                        .get(uri))
                .andExpect(MockMvcResultMatchers
                        .status()
                        .isNotFound());
    }

    @Test
    void deveriaAdicionarUmaPessoa() throws Exception {

        URI uri = new URI("/pessoas");
        String json = "{\n" +
                "\t\"nome\":\"Gabriel\",\n" +
                "\t\"dataNascimento\":\"28/10/1988\",\n" +
                "\t\"enderecos\": [\t\n" +
                "\t\t{\n" +
                "\t\t\t\"logradouro\":\"Rua das Flores\",\n" +
                "\t\t\t\"cep\":\"03710-800\",\n" +
                "\t\t\t\"numero\":\"522\",\n" +
                "\t\t\t\"cidade\":\"Rio de Janeiro\"\n" +
                "\t\t}\n" +
                "\t]\n" +
                "}";

        mockMvc
                .perform(MockMvcRequestBuilders
                        .post(uri)
                        .content(json)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers
                        .status()
                        .isCreated());
    }

}
