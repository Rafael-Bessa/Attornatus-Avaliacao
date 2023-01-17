package br.com.attornatus.desafio.exception;

import lombok.Data;

@Data
public class Erro {

    private String defaultMessage;
    private String field;
}
