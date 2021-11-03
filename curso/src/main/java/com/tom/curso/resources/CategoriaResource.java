package com.tom.curso.resources;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import com.tom.curso.domain.Categoria;
import com.tom.curso.domain.dtos.CategoriaDTO;
import com.tom.curso.services.CategoriaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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
    public ResponseEntity<Void> inserirCategoria(@Valid @RequestBody CategoriaDTO categoriaDTO) {
        Categoria categoria = this.categoriaService.fromCategoriaDTOToCategoria(categoriaDTO);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(categoria.getId())
                .toUri();
        return ResponseEntity.created(uri).build();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)

    public ResponseEntity<Void> alterarCategoria(@RequestBody Categoria categoria, @PathVariable Integer id) {
        this.categoriaService.alterarCategoria(categoria, id);
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deletarCategoriaPorId(@PathVariable Integer id) {
        this.categoriaService.deletarCategoriaPorId(id);
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<CategoriaDTO>> listarCategorias() {
        List<CategoriaDTO> categoriasDTO = this.categoriaService.listarCategorias();
        return ResponseEntity.ok().body(categoriasDTO);
    }

    @RequestMapping(value = "/pages", method = RequestMethod.GET)
    public ResponseEntity<Page<CategoriaDTO>> listaPaginadaDeCategoria(
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "size", defaultValue = "4") Integer size,
            @RequestParam(value = "direction", defaultValue = "ASC") String direction,
            @RequestParam(value = "orderBy", defaultValue = "nome") String orderBy) {
        Page<Categoria> categorias = this.categoriaService.listaPaginadaDeCategoria(page, size, direction, orderBy);
        Page<CategoriaDTO> categoriaDTO = categorias.map(item -> new CategoriaDTO(item));
        return ResponseEntity.ok().body(categoriaDTO);
    }
}