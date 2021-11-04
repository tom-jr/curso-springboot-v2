package com.tom.curso.resources;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import com.tom.curso.domain.Cliente;
import com.tom.curso.domain.dtos.ClienteDTO;
import com.tom.curso.services.ClienteService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/clientes")
public class ClienteResource {
    @Autowired
    private ClienteService clienteService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Cliente> buscarClientePorId(@PathVariable Integer id) {
        Cliente cliente = this.clienteService.buscarClientePorId(id);
        return ResponseEntity.ok().body(cliente);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Void> alterarCliente(@Valid @RequestBody ClienteDTO clienteDto, @PathVariable Integer id) {
        Cliente cliente = this.clienteService.fromClienteDTOToCliente(clienteDto);
        cliente.setId(id);
        cliente = this.clienteService.alterarCliente(cliente);

        return ResponseEntity.noContent().build();
    }

    @RequestMapping(value = "/{id}")
    public ResponseEntity<Void> deletarCliente(@PathVariable Integer id) {
        this.clienteService.deletarCliente(id);
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<ClienteDTO>> listarClientes() {
        List<Cliente> clientes = this.clienteService.listarClientes();
        List<ClienteDTO> clientesDTO = clientes.stream().map(item -> new ClienteDTO(item)).collect(Collectors.toList());
        return ResponseEntity.ok().body(clientesDTO);
    }

    @RequestMapping(value = "/page", method = RequestMethod.GET)
    public ResponseEntity<Page<ClienteDTO>> listarPageClientes(
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "size", defaultValue = "4") Integer size,
            @RequestParam(value = "direction", defaultValue = "ASC") String direction,
            @RequestParam(value = "orderBy", defaultValue = "nome") String orderBy) {
        Page<Cliente> pageClientes = this.clienteService.listarPageCliente(page, size, direction, orderBy);
        Page<ClienteDTO> pageClientesDTO = pageClientes.map(item -> new ClienteDTO(item));
        return ResponseEntity.ok().body(pageClientesDTO);
    }
}
