package com.tom.curso.domain.dtos;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;

import com.tom.curso.domain.Categoria;

import org.hibernate.validator.constraints.Length;



public class CategoriaDTO implements Serializable {

    // attributes

    private Integer id;

    @NotEmpty(message = "Preenchimento obrigatório")
    @Length(min=5, max=80, message="O tamanho deve ser entre 5 e 80 caracteres")
    private String nome;

    // constructors

    public CategoriaDTO() {

    }

    public CategoriaDTO(Categoria categoria) {
        this.id = categoria.getId();
        this.nome = categoria.getNome();
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

}
