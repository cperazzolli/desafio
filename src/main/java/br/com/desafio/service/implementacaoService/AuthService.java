package br.com.desafio.service.implementacaoService;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.desafio.exception.ObjectNotFoundException;
import br.com.desafio.model.Clientes;
import br.com.desafio.service.ClientesService;




@Service
public class AuthService {
   
	@Autowired
	private ClientesService clienteService;
	
	@Autowired
	private BCryptPasswordEncoder pe;
	
	private Random rand = new Random();
	
	public void sendNewPassword(String email) {
		Clientes cliente = clienteService.findByEmail(email);
		if(cliente == null) {
			throw new ObjectNotFoundException("Email não encontrado");
		}
		
		String newPass = newPassword();
		cliente.setSenha(pe.encode(newPass));
		clienteService.insert(cliente);
	}

	private String newPassword() {
		char[] vet = new char[10];
		for(int i = 0;i<0;i++) {
			vet[i] = randomChar();
		}
		
		return new String(vet);
	}

	private char randomChar() {
		int opt = rand.nextInt(3);
		if(opt == 0) {
			return (char) (rand.nextInt(10) + 48);
		}
		else if(opt == 1) {
			return (char) (rand.nextInt(26) + 65);
		}
		else {
			return (char) (rand.nextInt(26) + 97);
		}
	}
}
