package com.cursomc.service.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.cursomc.dto.ClienteNewDTO;
import com.cursomc.enums.TipoCliente;
import com.cursomc.model.Cliente;
import com.cursomc.repository.ClienteRepository;
import com.cursomc.service.exception.FieldMessage;
import com.cursomc.service.validation.utils.BR;

public class ClienteInsertValidator implements ConstraintValidator<ClienteInsert, ClienteNewDTO> {

	@Autowired
	private ClienteRepository clienteRepository;

	@Override
	public void initialize(ClienteInsert ann) {

	}

	@Override
	public boolean isValid(ClienteNewDTO objDTO, ConstraintValidatorContext context) {
		List<FieldMessage> list = new ArrayList<>();

		if (objDTO.getTipo().equals(TipoCliente.PESSOA_FISICA.getCod()) && !BR.isValidCpf(objDTO.getCpfOuCnpj())) {
			list.add(new FieldMessage("cpfOuCnpj", "CPF Inválido"));
		}

		if (objDTO.getTipo().equals(TipoCliente.PESSOA_JURIDICA.getCod()) && !BR.isValidCnpj(objDTO.getCpfOuCnpj())) {
			list.add(new FieldMessage("cpfOuCnpj", "CNPJ Inválido"));
		}

		Cliente aux = clienteRepository.findByEmail(objDTO.getEmail());
		if (aux != null) {
			list.add(new FieldMessage("email", "E-mail já existente."));
		}

		for (FieldMessage fieldMessage : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(fieldMessage.getMessage())
					.addPropertyNode(fieldMessage.getFieldName()).addConstraintViolation();
		}
		return list.isEmpty();
	}

}
