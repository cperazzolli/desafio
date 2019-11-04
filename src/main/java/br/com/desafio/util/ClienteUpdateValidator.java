package br.com.desafio.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerMapping;

import br.com.desafio.dto.ClienteDTO;
import br.com.desafio.exception.FieldMessage;
import br.com.desafio.model.Clientes;
import br.com.desafio.repository.ClientesRepository;


public class ClienteUpdateValidator implements ConstraintValidator<ClienteUpdate, ClienteDTO>{

	@Autowired
	private HttpServletRequest request;
	
	@Autowired
	private ClientesRepository repo;
	
	@Override
	public void initialize(ClienteUpdate ann) {
		
	}
	@Override
	public boolean isValid(ClienteDTO objDTO, ConstraintValidatorContext context) {
		
		@SuppressWarnings("unchecked")
		Map<String,String> map = (Map<String, String>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
		String uriId = map.get("id");
		List<FieldMessage> list = new ArrayList<>();
		
		
		Clientes aux = repo.findByEmail(objDTO.getEmail());

		if(aux != null && !aux.getIdCliente().equals(uriId)) {
			list.add(new FieldMessage("email","O email ja existe"));
		}
		
		for(FieldMessage e:list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName()).addConstraintViolation();
		}
		return list.isEmpty();
	}

}
