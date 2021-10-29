package com.tom.curso.repository;

import com.tom.curso.domain.Cidade;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CidadeRepository extends JpaRepository<Cidade, Integer> {

}
