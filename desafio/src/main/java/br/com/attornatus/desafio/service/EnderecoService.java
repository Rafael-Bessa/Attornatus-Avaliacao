package br.com.attornatus.desafio.service;

import br.com.attornatus.desafio.domain.dto.EnderecoDTO;
import br.com.attornatus.desafio.domain.model.Endereco;
import br.com.attornatus.desafio.domain.model.Pessoa;
import br.com.attornatus.desafio.domain.repository.EnderecoRepository;
import br.com.attornatus.desafio.domain.repository.PessoaRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EnderecoService implements EnderecoInterface{

    @Autowired
    private EnderecoRepository enderecoRepository;
    @Autowired
    private PessoaRepository pessoaRepository;
    @Autowired
    private ModelMapper mapper;

    @Override
    public EnderecoDTO criaNovoEnderecoParaUmaPessoa(Endereco endereco, Long idPessoa) {

        Optional<Pessoa> pessoaAlvo = pessoaRepository.findById(idPessoa);

        if(pessoaAlvo.isPresent()){
            endereco.setPessoa(pessoaAlvo.get());
            enderecoRepository.save(endereco);
            return mapper.map(endereco, EnderecoDTO.class);
        }
        return null;
    }

    @Override
    public ResponseEntity<Page<EnderecoDTO>> buscaTodosEnderecosDeUmaPessoaComPaginacao(Pageable pageable, Long pessoaId) {

        Page<Endereco> enderecos = enderecoRepository.findAllByPessoaId(pageable, pessoaId);
        Page<EnderecoDTO> enderecosDTO = enderecos.map(endereco -> mapper.map(endereco, EnderecoDTO.class));

        return ResponseEntity.ok(enderecosDTO);
    }
}
