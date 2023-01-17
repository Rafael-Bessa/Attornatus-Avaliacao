package br.com.attornatus.desafio.controller;

import br.com.attornatus.desafio.domain.dto.EnderecoDTO;
import br.com.attornatus.desafio.domain.model.Endereco;
import br.com.attornatus.desafio.service.EnderecoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/enderecos")
public class EnderecoController {
    @Autowired
    private EnderecoService service;

    @PostMapping("/{idPessoa}")
    @Transactional
    public ResponseEntity<EnderecoDTO> insereNovoEndereco(@RequestBody @Valid Endereco endereco,@PathVariable Long idPessoa, UriComponentsBuilder builder){
        EnderecoDTO enderecoDTO = service.criaNovoEnderecoParaUmaPessoa(endereco, idPessoa);

        URI uri = builder.path("/pessoas/{id}").buildAndExpand(idPessoa).toUri();
        return ResponseEntity.created(uri).body(enderecoDTO);
    }

    @GetMapping("/{idPessoa}")
    public ResponseEntity<Page<EnderecoDTO>> listaEnderecosDeUmaPessoa(@PageableDefault(size = 5, sort = {"numero"}) Pageable pageable,@PathVariable Long idPessoa){
        return service.buscaTodosEnderecosDeUmaPessoaComPaginacao(pageable, idPessoa);
    }


}
