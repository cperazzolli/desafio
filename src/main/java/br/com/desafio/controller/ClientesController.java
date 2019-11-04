package br.com.desafio.controller;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.desafio.dto.ClienteDTO;
import br.com.desafio.dto.ClienteNewDTO;
import br.com.desafio.model.Clientes;
import br.com.desafio.service.ClientesService;

@RestController
@RequestMapping(value = "/clientes")
public class ClientesController {

    @Autowired
    private ClientesService clientesService;

    @RequestMapping(value="/{idCliente}",method=RequestMethod.GET)
	public ResponseEntity<Clientes> findById(@PathVariable Integer idCliente) {
		
		Clientes cleintes = clientesService.findById(idCliente);
		return ResponseEntity.ok().body(cleintes);
	}
	
	@RequestMapping(value="/email",method=RequestMethod.GET)
	public ResponseEntity<Clientes> findByEmail(@RequestParam(value="value", required = false) String email) {
		
		Clientes clientes = clientesService.findByEmail(email);
		return ResponseEntity.ok().body(clientes);
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Void> insert(@Valid @RequestBody ClienteNewDTO objDTO){
		Clientes cliente = clientesService.fromDTO(objDTO);
		cliente = clientesService.insert(cliente);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{idCliente}").buildAndExpand(cliente.getIdCliente()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@RequestMapping(value="/{idCliente}",method=RequestMethod.PUT)
	public ResponseEntity<Void> update(@Valid @RequestBody ClienteDTO objDTO,@PathVariable Integer id){
		Clientes cliente = clientesService.fromDTO(objDTO);
		cliente.setIdCliente(id);
		cliente = clientesService.update(cliente);
		return ResponseEntity.noContent().build();
	}
	
	//@PreAuthorize("hasAnyRole('ADMIN')")
	@RequestMapping(value="/{idCliente}",method=RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
	    
		clientesService.delete(id);
		
		return ResponseEntity.noContent().build();
	}	
	
	
	//@PreAuthorize("hasAnyRole('ADMIN')")
	@RequestMapping(value="/page",method=RequestMethod.GET)
	public ResponseEntity<Page<ClienteDTO>> findPage(
			@RequestParam(value="page",defaultValue="0")Integer page,
			@RequestParam(value="linesPerPage",defaultValue="24")Integer linesPerPage, 
			@RequestParam(value="orderBy",defaultValue="nome")String orderBy,
			@RequestParam(value="direction",defaultValue="ASC")String direction) {
		
		Page<Clientes> list = clientesService.findByPage(page, linesPerPage, orderBy, direction);
		Page<ClienteDTO> listDTO = list.map(obj -> new ClienteDTO(obj));
		return ResponseEntity.ok().body(listDTO);
	}
}