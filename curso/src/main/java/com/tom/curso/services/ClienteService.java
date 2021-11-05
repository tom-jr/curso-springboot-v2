package com.tom.curso.services;

import java.util.List;
import java.util.Optional;

import com.tom.curso.domain.Cidade;
import com.tom.curso.domain.Cliente;
import com.tom.curso.domain.Endereco;
import com.tom.curso.domain.dtos.ClienteDTO;
import com.tom.curso.domain.dtos.ClienteNewDTO;
import com.tom.curso.domain.enums.TipoCliente;
import com.tom.curso.repository.CidadeRepository;
import com.tom.curso.repository.ClienteRepository;
import com.tom.curso.repository.EnderecoRepository;
import com.tom.curso.services.exception.DataIntegrityViolationException;
import com.tom.curso.services.exception.ObjectNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ClienteService {
    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private CidadeRepository cidadeRepository;
    @Autowired
    private EnderecoRepository enderecoRepository;

    public Cliente buscarClientePorId(Integer id) {
        Optional<Cliente> cliente = this.clienteRepository.findById(id);

        return cliente.orElseThrow(() -> new ObjectNotFoundException("ObjetoNão encontrado, id: " + id));
    }

    public Cliente fromClienteDTOToCliente(ClienteDTO clienteDto) {
        Cliente cliente = new Cliente();
        cliente.setNome(clienteDto.getNome());
        cliente.setEmail(clienteDto.getEmail());
        return cliente;
    }

    public Cliente alterarCliente(Cliente cliente) {
        Cliente clienteDaPersistencia = this.buscarClientePorId(cliente.getId());
        clienteDaPersistencia.setNome(cliente.getNome());
        clienteDaPersistencia.setEmail(cliente.getEmail());
        this.clienteRepository.save(clienteDaPersistencia);
        return clienteDaPersistencia;
    }

    public void deletarCliente(Integer id) {
        Cliente cliente = this.buscarClientePorId(id);

        try {
            this.clienteRepository.delete(cliente);
        } catch (org.springframework.dao.DataIntegrityViolationException e) {
            // TODO: handle exception
            throw new DataIntegrityViolationException("Não é possível excluir porque há pedidos relacionados");
        }
    }

    public List<Cliente> listarClientes() {
        List<Cliente> clientes = this.clienteRepository.findAll();
        return clientes;
    }

    public Page<Cliente> listarPageCliente(Integer page, Integer size, String direction, String orderBy) {
        PageRequest pageRequest = PageRequest.of(page, size, Direction.valueOf(direction), orderBy);
        return this.clienteRepository.findAll(pageRequest);
    }

    public Cliente fromClienteDTOToCliente(ClienteNewDTO clienteNewDTO) {
        Cliente cliente = new Cliente(null, clienteNewDTO.getNome(), clienteNewDTO.getEmail(),
                clienteNewDTO.getCpfOuCnpj(), TipoCliente.integerToEnum(clienteNewDTO.getTipo()));

        Optional<Cidade> cidade = this.cidadeRepository.findById(clienteNewDTO.getCidadeId());

        Endereco endereco = new Endereco(null, clienteNewDTO.getLogradouro(), clienteNewDTO.getNumero(),
                clienteNewDTO.getComplemento(), clienteNewDTO.getBairro(), clienteNewDTO.getCep(), cliente,
                cidade.get());

        cliente.getEnderecos().add(endereco);
        cliente.getTelefones().add(clienteNewDTO.getTelefone1());

        if (clienteNewDTO.getTelefone2() != null) {
            cliente.getTelefones().add(clienteNewDTO.getTelefone2());
        }
        if (clienteNewDTO.getTelefone3() != null) {
            cliente.getTelefones().add(clienteNewDTO.getTelefone3());
        }

        return cliente;
    }

    @Transactional
    public Cliente inserirNovoCliente(Cliente cliente) {
        this.clienteRepository.save(cliente);
        this.enderecoRepository.saveAll(cliente.getEnderecos());

        return cliente;
    }
}
