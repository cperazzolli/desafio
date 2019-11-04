package br.com.desafio.service.implementacaoService;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.desafio.model.Clientes;
import br.com.desafio.repository.ClientesRepository;
import br.com.desafio.security.UserSS;


@Service
public class UserDetailsServiceImpl implements UserDetailsService {
  
	@Autowired
	private ClientesRepository clientesRepository;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Clientes cli = clientesRepository.findByEmail(email);
		if(cli == null) {
			throw new UsernameNotFoundException(email);
		}
		return new UserSS(cli.getIdCliente(),cli.getEmail(),cli.getSenha(),cli.getPerfis(),cli.getSessao());
	}

}
