package com.tom.curso.services;

import java.util.Optional;

import com.tom.curso.domain.Cliente;
import com.tom.curso.repository.ClienteRepository;
import com.tom.curso.services.exception.ObjectNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteService {
    @Autowired
    private ClienteRepository clienteRepository;

    public Cliente buscarClientePorId(Integer id) {
        Optional<Cliente> cliente = this.clienteRepository.findById(id);

        return cliente.orElseThrow(() -> new ObjectNotFoundException("ObjetoNão encontrado, id: " + id));
    }
}
