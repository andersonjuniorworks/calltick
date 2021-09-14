package com.andersonjunior.calltick.services.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.andersonjunior.calltick.controllers.exception.FieldMessage;
import com.andersonjunior.calltick.dto.ClientDto;
import com.andersonjunior.calltick.models.enums.ClientType;
import com.andersonjunior.calltick.services.validation.utils.BR;

public class ClientInsertValidator implements ConstraintValidator<ClientInsert, ClientDto> {

	@Override
	public void initialize(ClientInsert ann) {
	}

	@Override
	public boolean isValid(ClientDto objDto, ConstraintValidatorContext context) {
		
		List<FieldMessage> list = new ArrayList<>();
		
		if (objDto.getType().equals(ClientType.PESSOAFISICA.getCode()) && !BR.isValidCPF(objDto.getDocument())) {
			list.add(new FieldMessage("cpfOuCnpj", "CPF inválido"));
		}

		if (objDto.getType().equals(ClientType.PESSOAJURIDICA.getCode()) && !BR.isValidCNPJ(objDto.getDocument())) {
			list.add(new FieldMessage("cpfOuCnpj", "CNPJ inválido"));
		}
		
		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
					.addConstraintViolation();
		}
		return list.isEmpty();
	}
}
