package br.com.attornatus.desafio.domain.dto;

import br.com.attornatus.desafio.domain.model.EnderecoPrincipal;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PessoaDTO {

    private Long id;
    private String nome;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate dataNascimento;
    private List<EnderecoDTO> enderecos;
    private EnderecoPrincipal enderecoPrincipal;
}
