package com.tom.curso.services;

import java.util.Optional;

import com.tom.curso.domain.Categoria;
import com.tom.curso.repository.CategoriaRepository;
import com.tom.curso.services.exception.ObjectNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
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

}
