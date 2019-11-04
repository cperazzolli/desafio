package br.com.desafio.controller;

import java.net.URI;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.desafio.model.Pedidos;
import br.com.desafio.service.PedidosService;

@Controller
@RequestMapping(value = "pedidos")
public class PedidosController {

	
    @Autowired
    private PedidosService pedidosService;
    
    @RequestMapping(value="/{idPedido}",method=RequestMethod.GET)
	public ResponseEntity<Pedidos> listar(@PathVariable Integer idPedido) {
		
		Pedidos obj = pedidosService.findById(idPedido);
		return ResponseEntity.ok().body(obj);
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Void> insert(@Valid @RequestBody Pedidos obj, HttpSession http){
		obj.setSessao(http.getId());
		obj = pedidosService.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{idPediso}").buildAndExpand(obj.getIdPedido()).toUri();
		return ResponseEntity.created(uri).build();
	}

	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<Page<Pedidos>> findPage(
			@RequestParam(value="page",defaultValue="0")Integer page,
			@RequestParam(value="linesPerPage",defaultValue="24")Integer linesPerPage, 
			@RequestParam(value="orderBy",defaultValue="instante")String orderBy,
			@RequestParam(value="direction",defaultValue="DESC")String direction) {
		
		Page<Pedidos> list = pedidosService.findByPage(page, linesPerPage, orderBy, direction);
		return ResponseEntity.ok().body(list);
	}
}