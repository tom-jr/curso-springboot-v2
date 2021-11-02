package com.tom.curso.repository;

import com.tom.curso.domain.Pagamento;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PagamentoRepository extends JpaRepository<Pagamento, Integer>{
    
}
