package br.com.attornatus.desafio.service;

import br.com.attornatus.desafio.domain.dto.PessoaDTO;
import br.com.attornatus.desafio.domain.model.Endereco;
import br.com.attornatus.desafio.domain.model.EnderecoPrincipal;
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
public class PessoaService implements PessoaInterface{

    @Autowired
    private EnderecoRepository enderecoRepository;
    @Autowired
    private PessoaRepository pessoaRepository;
    @Autowired
    private ModelMapper mapper;

    @Override
    public ResponseEntity<PessoaDTO> buscaPessoaPeloID(Long id) {

        Optional<Pessoa> pessoaBuscada = pessoaRepository.findById(id);

        if(pessoaBuscada.isPresent()){
            return ResponseEntity.ok(mapper.map(pessoaBuscada.get(), PessoaDTO.class));
        }
        return ResponseEntity.notFound().build();
    }

    @Override
    public ResponseEntity<Page<PessoaDTO>> buscaTodasPessoasComPaginacao(Pageable pageable) {

        Page<Pessoa> pessoas = pessoaRepository.findAll(pageable);
        Page<PessoaDTO> pessoasdto = pessoas.map(pessoa -> mapper.map(pessoa, PessoaDTO.class));

        return ResponseEntity.ok(pessoasdto);
    }

    @Override
    public PessoaDTO criaNovaPessoa(Pessoa pessoa) {
        pessoaRepository.save(pessoa);
        if(!pessoa.getEnderecos().isEmpty()) {
            for (Endereco endereco : pessoa.getEnderecos()) {
                Optional<Endereco> end = enderecoRepository.findById(endereco.getId());
                end.get().setPessoa(pessoa);
            }
        }
        return mapper.map(pessoa, PessoaDTO.class);
    }

    @Override
    public ResponseEntity<?> atualizaPessoa(Long id, Pessoa pessoa) {

        Optional<Pessoa> pessoaAlvo = pessoaRepository.findById(id);

        if(pessoaAlvo.isPresent()){
            pessoaAlvo.get().setNome(pessoa.getNome());
            pessoaAlvo.get().setDataNascimento(pessoa.getDataNascimento());
            pessoaAlvo.get().setEnderecoPrincipal(pessoa.getEnderecoPrincipal());

            enderecoRepository.deleteAllByPessoaId(id);
            pessoaAlvo.get().setEnderecos(pessoa.getEnderecos());

            for (Endereco endereco: pessoaAlvo.get().getEnderecos()) {
                endereco.setPessoa(pessoaAlvo.get());
            }

            return ResponseEntity.ok(mapper.map(pessoaAlvo.get(), PessoaDTO.class));
        }
        return ResponseEntity.notFound().build();
    }

    @Override
    public ResponseEntity<?> deletaPessoa(Long id) {

        Optional<Pessoa> pessoa = pessoaRepository.findById(id);
        if(pessoa.isPresent()){
            pessoaRepository.delete(pessoa.get());
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @Override
    public PessoaDTO defineEnderecoPrincipalDaPessoa(Long id, EnderecoPrincipal enderecoPrincipal) {

        Optional<Pessoa> pessoaBuscada = pessoaRepository.findById(id);

        if(pessoaBuscada.isPresent()){
            pessoaBuscada.get().setEnderecoPrincipal(enderecoPrincipal);
            return mapper.map(pessoaBuscada.get(), PessoaDTO.class);
        }
        return null;
    }

}
