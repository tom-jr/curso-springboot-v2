package com.tom.curso.resources;

import com.tom.curso.domain.Pedido;
import com.tom.curso.services.PedidoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/pedidos")
public class PedidoResource {
    @Autowired
    private PedidoService pedidoService;


    @RequestMapping(method = RequestMethod.GET,value = "/{id}")
    public ResponseEntity<Pedido> buscarPedidoPorId(@PathVariable Integer id){

        Pedido pedido =  this.pedidoService.buscarPedidoPorId(id);
        return ResponseEntity.ok().body(pedido);
    }
}
