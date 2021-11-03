package com.tom.curso.domain.dtos;

import java.io.Serializable;

import com.tom.curso.domain.Categoria;

public class CategoriaDTO implements Serializable{
    
    // attributes
    
    private Integer id;

    private String nome;

    // constructors

    public CategoriaDTO(){

    }

    public CategoriaDTO(Categoria categoria){
        this.id =  categoria.getId();
        this.nome = categoria.getNome();
    }

    //getters and setters

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
