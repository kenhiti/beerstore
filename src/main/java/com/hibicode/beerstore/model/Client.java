package com.hibicode.beerstore.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Client {

    private Long idCliente;
    private String nome;
    private Date dataNascimento;
    private String cpf;
    private String endereco;

}
