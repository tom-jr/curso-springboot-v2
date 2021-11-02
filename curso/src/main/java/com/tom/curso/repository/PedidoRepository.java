package com.tom.curso.repository;

import com.tom.curso.domain.Pedido;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PedidoRepository extends JpaRepository<Pedido,  Integer>{
    
}
