package com.tom.curso.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import com.tom.curso.domain.Categoria;
import com.tom.curso.domain.dtos.CategoriaDTO;
import com.tom.curso.repository.CategoriaRepository;
import com.tom.curso.services.exception.ObjectNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

@Service
public class CategoriaService {
    @Autowired
    private CategoriaRepository categoriaRepository;

    public Categoria buscarCategoria(Integer id) {

        Optional<Categoria> categoria = this.categoriaRepository.findById(id);
        return categoria.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado, id: " + id));

    }

    public Categoria inserirCategoria(Categoria categoria) {
        categoria.setId(null);
        return this.categoriaRepository.save(categoria);
    }

    public void alterarCategoria(Categoria categoria, Integer id) {
        categoria.setId(id);
        Categoria categoriaDePersitencia = this.buscarCategoria(categoria.getId());
        categoriaDePersitencia.setNome(categoria.getNome());
        this.categoriaRepository.save(categoria);
    }

    public void deletarCategoriaPorId(Integer id) {
        this.buscarCategoria(id);
        try {
            this.categoriaRepository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            // TODO: handle exception
            throw new com.tom.curso.services.exception.DataIntegrityViolationException(
                    String.format("Não é possível deletar categoria de id %d, pois a mesma possui vinculos", id));
        }
    }

    public List<CategoriaDTO> listarCategorias() {
        List<Categoria> categorias = this.categoriaRepository.findAll();
        List<CategoriaDTO> categoriasDTO = categorias.stream().map(item -> new CategoriaDTO(item))
                .collect(Collectors.toList());
        return categoriasDTO;
    }

    public Page<Categoria> listaPaginadaDeCategoria(Integer page,Integer size,String direction,String properties) {
        PageRequest pageRequest =  PageRequest.of(page, size, Direction.valueOf(direction), properties);

        
        return this.categoriaRepository.findAll(pageRequest);
    }

    public Categoria fromCategoriaDTOToCategoria(@Valid CategoriaDTO categoriaDTO) {
        Categoria categoria = new Categoria(null, categoriaDTO.getNome());
        this.inserirCategoria(categoria);
        return categoria;
    }

}
