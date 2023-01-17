package br.com.attornatus.desafio.controller;

import br.com.attornatus.desafio.domain.dto.PessoaDTO;
import br.com.attornatus.desafio.domain.model.EnderecoPrincipal;
import br.com.attornatus.desafio.domain.model.Pessoa;
import br.com.attornatus.desafio.service.PessoaService;
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
@RequestMapping("/pessoas")
public class PessoaController {
    @Autowired
    private PessoaService service;

    @GetMapping
    public ResponseEntity<Page<PessoaDTO>> listaPessoas(@PageableDefault(size = 5, sort = {"nome"}) Pageable pageable){
        return service.buscaTodasPessoasComPaginacao(pageable);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PessoaDTO> consultarUmaPessoa(@PathVariable Long id){
        return service.buscaPessoaPeloID(id);
    }

    @PostMapping
    @Transactional
    public ResponseEntity<PessoaDTO> criaPessoa(@RequestBody @Valid Pessoa pessoa, UriComponentsBuilder builder){
        PessoaDTO pessoaDTO = service.criaNovaPessoa(pessoa);
        URI uri = builder.path("/pessoas/{id}").buildAndExpand(pessoa.getId()).toUri();

        return ResponseEntity.created(uri).body(pessoaDTO);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<?> editarPessoa(@PathVariable Long id, @RequestBody @Valid Pessoa pessoa){
        return service.atualizaPessoa(id, pessoa);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> deletarPessoa(@PathVariable Long id){
        return service.deletaPessoa(id);
    }

    @PatchMapping("/{id}")
    @Transactional
    public ResponseEntity<?> defineEnderecoPrincipal(@PathVariable Long id, @RequestBody @Valid EnderecoPrincipal endereco){
        return ResponseEntity.ok(service.defineEnderecoPrincipalDaPessoa(id, endereco));
    }

}
