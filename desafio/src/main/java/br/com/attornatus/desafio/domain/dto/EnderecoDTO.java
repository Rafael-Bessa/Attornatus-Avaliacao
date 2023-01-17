package br.com.attornatus.desafio.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EnderecoDTO {

    private String logradouro;
    private String cep;
    private String numero;
    private String cidade;
}
