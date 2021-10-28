package com.tom.curso.resources;

import java.util.Arrays;
import java.util.List;

import com.tom.curso.domain.Categoria;
import com.tom.curso.services.CategoriaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/categorias")
public class CategoriaResource {

    @Autowired
    private CategoriaService categoriaService;

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public ResponseEntity<Categoria> listarCategoria(@PathVariable Integer id) {
        Categoria categoria =  this.categoriaService.buscarCategoria(id);
        return ResponseEntity.ok().body(categoria);
    }
}