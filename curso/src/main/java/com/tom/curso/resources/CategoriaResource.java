package com.tom.curso.resources;

import java.net.URI;

import com.tom.curso.domain.Categoria;
import com.tom.curso.services.CategoriaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping(value = "/categorias")
public class CategoriaResource {

    @Autowired
    private CategoriaService categoriaService;

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public ResponseEntity<Categoria> listarCategoria(@PathVariable Integer id) {
        Categoria categoria = this.categoriaService.buscarCategoria(id);
        return ResponseEntity.ok().body(categoria);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> inserirCategoria(@RequestBody Categoria categoria) {
        categoria = this.categoriaService.inserirCategoria(categoria);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(categoria.getId())
                .toUri();
        return ResponseEntity.created(uri).build();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)

    public ResponseEntity<Void> alterarCategoria(@RequestBody Categoria categoria, @PathVariable Integer id) {
            this.categoriaService.alterarCategoria( categoria,id);
        return ResponseEntity.noContent().build();
    }
}