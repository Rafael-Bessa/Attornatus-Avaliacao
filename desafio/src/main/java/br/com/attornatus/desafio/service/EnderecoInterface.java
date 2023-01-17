package br.com.attornatus.desafio.service;

import br.com.attornatus.desafio.domain.dto.EnderecoDTO;
import br.com.attornatus.desafio.domain.model.Endereco;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface EnderecoInterface {

    public EnderecoDTO criaNovoEnderecoParaUmaPessoa(Endereco endereco, Long idPessoa);
    public ResponseEntity<Page<EnderecoDTO>> buscaTodosEnderecosDeUmaPessoaComPaginacao(Pageable pageable, Long pessoaId);
}
