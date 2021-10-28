package com.tom.curso.resources;

import java.util.Arrays;
import java.util.List;

import com.tom.curso.domain.Categoria;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/categorias")
public class CategoriaResource {

    @RequestMapping(method = RequestMethod.GET)
    public List<Categoria> listarCategoria() {
        List<Categoria> categorias = Arrays.asList(new Categoria(1, "noInformática"), new Categoria(2, "Escritório"));
        return categorias;
    }
}