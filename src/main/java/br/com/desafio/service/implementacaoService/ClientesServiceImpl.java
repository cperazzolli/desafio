package br.com.desafio.service.implementacaoService;

import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.desafio.dto.ClienteDTO;
import br.com.desafio.dto.ClienteNewDTO;
import br.com.desafio.exception.AuthorizationException;
import br.com.desafio.exception.ObjectNotFoundException;
import br.com.desafio.model.Clientes;
import br.com.desafio.model.Enuns.Perfil;
import br.com.desafio.repository.ClientesRepository;
import br.com.desafio.security.UserSS;
import br.com.desafio.service.ClientesService;

@Service
public class ClientesServiceImpl implements ClientesService {

    @Autowired
    private ClientesRepository clientesRepository;

    @Autowired
    private BCryptPasswordEncoder pe;

    @Override
    public Clientes findById(Integer idCliente) {
        UserSS user = UserService.authenticated();
		if(user == null || user.hasHole(Perfil.ADMIN) || !idCliente.equals(user.getId())) {
			throw new AuthorizationException("Acesso negado");
		}
        Optional<Clientes> cliente = clientesRepository.findById(idCliente);
        return cliente.orElseThrow(() -> new ObjectNotFoundException("Cliente não encontrado!"));
    }

    public Clientes findByEmail(String email) {
		
		UserSS user = UserService.authenticated();
		if(user==null || !user.hasHole(Perfil.ADMIN)&&!email.equals(user.getUsername())) {
			throw new AuthorizationException("Acesso negado");
		}
		
		Clientes obj = clientesRepository.findByEmail(email);
		if(obj == null) {
			throw new ObjectNotFoundException("Email não encontrado" + user.getId()
					+ ", tipo: " + Clientes.class.getName());
		}
		return obj;
	}

    @Override
    public List<Clientes> findAll() {
        return clientesRepository.findAll();
    }

    @Override
    public Page<Clientes> findByPage(Integer page, Integer linesPage, String orderBy, String direction) {
        PageRequest pageRequest = PageRequest.of(page, linesPage, Direction.valueOf(direction), orderBy);
        return clientesRepository.findAll(pageRequest);
    }

    @Override
    public Clientes insert(Clientes cliente) {
        cliente.setIdCliente(null);
        cliente = clientesRepository.save(cliente);
        return cliente;
    }

    @Override
    public Clientes update(Clientes cliente) {
        Clientes novoCliente = findById(cliente.getIdCliente());
        updateData(novoCliente,cliente);
        return clientesRepository.save(novoCliente);
    }

    private void updateData(Clientes novoCliente, Clientes cliente) {
        novoCliente.setNome(cliente.getNome());
        novoCliente.setEmail(cliente.getEmail());
    }

    @Override
    public void delete(Integer idCliente) {
       findById(idCliente);
       try {
           clientesRepository.deleteById(idCliente);
       } catch (DataIntegrityViolationException e) {
           throw new DataIntegrityViolationException("Não e possivel excluir um cliente com entidades relacionada!");
       }
    }

    public Clientes fromDTO(ClienteDTO objDTO) {
        return new Clientes(objDTO.getId(), objDTO.getNome(), objDTO.getEmail(), null, null, null, 
                            null, null, null);
    }
        
    public Clientes fromDTO(ClienteNewDTO objDTO) {
        Clientes cli =  new Clientes(null,objDTO.getNome(),objDTO.getEmail(), pe.encode(objDTO.getSenha()), 
                                     objDTO.getRua(),objDTO.getCidade(), objDTO.getBairro(), objDTO.getCep(), 
                                     objDTO.getEstado());
		return cli;
	}

}