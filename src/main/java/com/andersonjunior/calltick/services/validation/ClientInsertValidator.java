package com.andersonjunior.calltick.services.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.andersonjunior.calltick.controllers.exception.FieldMessage;
import com.andersonjunior.calltick.dto.ClientDto;
import com.andersonjunior.calltick.models.Client;
import com.andersonjunior.calltick.models.enums.ClientType;
import com.andersonjunior.calltick.repositories.ClientRepository;
import com.andersonjunior.calltick.services.validation.utils.BR;

import org.springframework.beans.factory.annotation.Autowired;

public class ClientInsertValidator implements ConstraintValidator<ClientInsert, ClientDto> {

	@Autowired
	private ClientRepository clientRepo;

	@Override
	public void initialize(ClientInsert ann) {
	}

	@Override
	public boolean isValid(ClientDto objDto, ConstraintValidatorContext context) {
		
		List<FieldMessage> list = new ArrayList<>();
		
		if (objDto.getType().equals(ClientType.PESSOAFISICA.getCode()) && !BR.isValidCPF(objDto.getDocument())) {
			list.add(new FieldMessage("document", "CPF inválido"));
		}

		if (objDto.getType().equals(ClientType.PESSOAJURIDICA.getCode()) && !BR.isValidCNPJ(objDto.getDocument())) {
			list.add(new FieldMessage("document", "CNPJ inválido"));
		}

		Client aux = clientRepo.findByDocument(objDto.getDocument());
		
		if (objDto.getType().equals(ClientType.PESSOAJURIDICA.getCode()) && aux != null) {
			list.add(new FieldMessage("document", "CNPJ já cadastrado"));
		}

		if (objDto.getType().equals(ClientType.PESSOAFISICA.getCode()) && aux != null) {
			list.add(new FieldMessage("document", "CPF já cadastrado"));
		}
						
		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName()).addConstraintViolation();
		}

		return list.isEmpty();

	}
}
