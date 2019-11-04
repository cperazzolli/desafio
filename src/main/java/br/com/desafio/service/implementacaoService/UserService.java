package br.com.desafio.service.implementacaoService;

import org.springframework.security.core.context.SecurityContextHolder;

import br.com.desafio.security.UserSS;

public class UserService {
    
	public static UserSS authenticated() {
		try{
		   return (UserSS) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		}catch (Exception e) {
			return null;
		}
	}
}
