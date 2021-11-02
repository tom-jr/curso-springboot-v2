package com.tom.curso.services;

import java.util.Optional;

import com.tom.curso.domain.Pedido;
import com.tom.curso.repository.PedidoRepository;
import com.tom.curso.services.exception.ObjectNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    public Pedido buscarPedidoPorId(Integer id) {
        Optional<Pedido> pedido = this.pedidoRepository.findById(id);
        return pedido.orElseThrow(()->  new ObjectNotFoundException(String.format("Pedido de id: %d não encontrado!", id)));
    }

}
