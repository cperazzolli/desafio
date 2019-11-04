package br.com.desafio.util;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.desafio.dto.ClienteNewDTO;
import br.com.desafio.exception.FieldMessage;
import br.com.desafio.model.Clientes;
import br.com.desafio.repository.ClientesRepository;


public class ClienteInsertValidator implements ConstraintValidator<ClienteInsert, ClienteNewDTO>{

	@Autowired
	private ClientesRepository repo;
	
	@Override
	public void initialize(ClienteInsert ann) {
		
	}
	@Override
	public boolean isValid(ClienteNewDTO objDTO, ConstraintValidatorContext context) {
		List<FieldMessage> list = new ArrayList<>();
		
		Clientes aux = repo.findByEmail(objDTO.getEmail());
		if(aux != null) {
			list.add(new FieldMessage("email","O email ja existe"));
		}
		
		for(FieldMessage e:list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName()).addConstraintViolation();
		}
		return list.isEmpty();
	}

}
