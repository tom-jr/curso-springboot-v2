package com.tom.curso.services;

import java.util.Optional;

import com.tom.curso.domain.Categoria;
import com.tom.curso.repository.CategoriaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoriaService {
    @Autowired
    private CategoriaRepository categoriaRepository;

    public Categoria buscarCategoria(Integer id) {

        Optional<Categoria> categoria = this.categoriaRepository.findById(id);
        return categoria.orElse(null);
    }

}
