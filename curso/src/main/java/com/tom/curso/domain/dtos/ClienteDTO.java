package com.tom.curso.domain.dtos;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import com.tom.curso.domain.Cliente;

import org.hibernate.validator.constraints.Length;

public class ClienteDTO implements Serializable {

    // attributes

    private Integer id;

    @NotEmpty(message = "Obrigatório o prenchimento")
    @Length(min = 5, max = 120, message = "O tamanho deve ser entre 5 e 120 caracteres")
    private String nome;

    @NotEmpty(message = "Obrigatório o prenchimento")
    @Email(message = "e-mail inválido")
    private String email;

    // constructors

    public ClienteDTO() {
    }

    public ClienteDTO(Cliente cliente) {

        this.id = cliente.getId();
        this.nome = cliente.getNome();
        this.email = cliente.getEmail();
    }

    // getters and setters

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    

}
