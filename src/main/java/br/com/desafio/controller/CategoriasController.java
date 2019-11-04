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

import br.com.desafio.dto.CategoriasDTO;
import br.com.desafio.model.Categorias;
import br.com.desafio.service.CategoriasService;

@RestController
@RequestMapping(value = "/categorias")
public class CategoriasController {
    
    @Autowired
    private CategoriasService categoriasService;

    @RequestMapping(value="/{idCategoria}",method=RequestMethod.GET)
	public ResponseEntity<Categorias> find(@PathVariable Integer idCategoria) {
		
		Categorias categorias = categoriasService.findById(idCategoria);
		return ResponseEntity.ok().body(categorias);
	}
    
	//@PreAuthorize("hasAnyRole('ADMIN')")
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Void> insert(@Valid @RequestBody CategoriasDTO categoriasDTO){
		Categorias obj = categoriasService.fromDTO(categoriasDTO);
		obj = categoriasService.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{idCategoria}").buildAndExpand(obj.getIdCategoria()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	//@PreAuthorize("hasAnyRole('ADMIN')")
	@RequestMapping(value="/{idCategoria}",method=RequestMethod.PUT)
	public ResponseEntity<Void> update(@Valid @RequestBody CategoriasDTO objDTO,@PathVariable Integer id){
		Categorias obj = categoriasService.fromDTO(objDTO);
		obj.setIdCategoria(id);
		obj = categoriasService.update(obj);
		return ResponseEntity.noContent().build();
	}
	
	//@PreAuthorize("hasAnyRole('ADMIN')")
	@RequestMapping(value="/{idCategoria}",method=RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Integer idCategoria) {
	    
		categoriasService.delete(idCategoria);
		
		return ResponseEntity.noContent().build();
	}	
	
	@RequestMapping(value="/page",method=RequestMethod.GET)
	public ResponseEntity<Page<CategoriasDTO>> findPage(
			@RequestParam(value="page",defaultValue="0")Integer page,
			@RequestParam(value="linesPerPage",defaultValue="24")Integer linesPerPage, 
			@RequestParam(value="orderBy",defaultValue="nome")String orderBy,
			@RequestParam(value="direction",defaultValue="ASC")String direction) {
		
		Page<Categorias> list = categoriasService.findPage(page, linesPerPage, orderBy, direction);
		Page<CategoriasDTO> listDTO = list.map(obj -> new CategoriasDTO(obj));
		return ResponseEntity.ok().body(listDTO);
	}
}