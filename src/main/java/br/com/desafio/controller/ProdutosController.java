package br.com.desafio.controller;

import java.net.URI;
import java.util.List;

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
import org.springframework.web.multipart.MultipartFile;

import br.com.desafio.dto.ProdutoDTO;
import br.com.desafio.model.Produtos;
import br.com.desafio.service.ProdutosService;
import br.com.desafio.util.URL;

@Controller
@RequestMapping(value = "/produtos")
public class ProdutosController {

    @Autowired
    private ProdutosService produtosService;

    @RequestMapping(value="/{idProduto}",method=RequestMethod.GET)
	public ResponseEntity<Produtos> listar(@PathVariable Integer idProduto) {
		
		Produtos obj = produtosService.findById(idProduto);
		return ResponseEntity.ok().body(obj);
	}

	@RequestMapping(value = "insert", method=RequestMethod.POST)
	public ResponseEntity<Produtos> insert(@RequestParam("file") MultipartFile file, 
										   @Valid @RequestBody Produtos produtos) {
		
		URI uri = produtosService.uploadProdutoPicture(file);
        produtos.setFoto(file.toString());
		produtosService.insert(produtos);
		return ResponseEntity.created(uri).build();
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<Page<ProdutoDTO>> findPage(
			@RequestParam(value="nome",defaultValue="")String nome,
			@RequestParam(value="categorias",defaultValue="")String categorias,
			@RequestParam(value="page",defaultValue="0")Integer page,
			@RequestParam(value="linesPerPage",defaultValue="24")Integer linesPerPage, 
			@RequestParam(value="orderBy",defaultValue="nome")String orderBy,
			@RequestParam(value="direction",defaultValue="ASC")String direction) {
		
		String nomeDecode = URL.decodeParam(nome);
		List<Integer> ids = URL.decodeIntList(categorias);
		Page<Produtos> list = produtosService.findByPage(nomeDecode, ids, page, linesPerPage, orderBy, direction);
		Page<ProdutoDTO> listDTO = list.map(obj -> new ProdutoDTO(obj));
		return ResponseEntity.ok().body(listDTO);
	}
}