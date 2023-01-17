package br.com.attornatus.desafio.service;

import br.com.attornatus.desafio.domain.dto.PessoaDTO;
import br.com.attornatus.desafio.domain.model.EnderecoPrincipal;
import br.com.attornatus.desafio.domain.model.Pessoa;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface PessoaInterface {

    public ResponseEntity<PessoaDTO> buscaPessoaPeloID(Long id);

    public ResponseEntity<Page<PessoaDTO>> buscaTodasPessoasComPaginacao(Pageable pageable);

    public PessoaDTO criaNovaPessoa(Pessoa pessoa);

    public ResponseEntity<?> atualizaPessoa(Long id, Pessoa pessoa);

    public ResponseEntity<?> deletaPessoa(Long id);

    public PessoaDTO defineEnderecoPrincipalDaPessoa(Long id, EnderecoPrincipal enderecoPrincipal);
}
