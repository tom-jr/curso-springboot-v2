package com.tom.curso.services.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.tom.curso.domain.dtos.ClienteNewDTO;
import com.tom.curso.domain.enums.TipoCliente;
import com.tom.curso.resources.exception.FieldMessage;
import com.tom.curso.services.validation.utils.BR;

public class ClienteInsertValidator implements ConstraintValidator<ClienteInsert, ClienteNewDTO> {
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



        for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getDefaulMessage()).addPropertyNode(e.getFieldMessage())
					.addConstraintViolation();
		}

        return list.isEmpty();
    }

}
