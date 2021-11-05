package com.tom.curso.services.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.tom.curso.domain.Cliente;
import com.tom.curso.domain.dtos.ClienteNewDTO;
import com.tom.curso.domain.enums.TipoCliente;
import com.tom.curso.repository.ClienteRepository;
import com.tom.curso.resources.exception.FieldMessage;
import com.tom.curso.services.validation.utils.BR;

import org.springframework.beans.factory.annotation.Autowired;

public class ClienteInsertValidator implements ConstraintValidator<ClienteInsert, ClienteNewDTO> {
    @Autowired
    private ClienteRepository clienteRepository;

    @Override
    public void initialize(ClienteInsert ann) {
    }

    @Override
    public boolean isValid(ClienteNewDTO value, ConstraintValidatorContext context) {
        // TODO Auto-generated method stub

        List<FieldMessage> list = new ArrayList<>();

        if (value.getTipo().equals(TipoCliente.PESSOA_FISICA.getCod()) && !BR.isValidCPF(value.getCpfOuCnpj())) {
            list.add(new FieldMessage("cpfOuCnpj", "CPF inválido"));
        }

        if (value.getTipo().equals(TipoCliente.PESSOA_JURIDICA.getCod()) && !BR.isValidCNPJ(value.getCpfOuCnpj())) {
            list.add(new FieldMessage("cpfOuCnpj", "CPF inválido"));
        }

        Cliente aux = this.clienteRepository.findByEmail(value.getEmail());

        if (aux != null) {
            list.add(new FieldMessage("email", "Email já existente"));
        }

        for (FieldMessage e : list) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(e.getDefaulMessage()).addPropertyNode(e.getFieldMessage())
                    .addConstraintViolation();
        }

        return list.isEmpty();
    }

}
