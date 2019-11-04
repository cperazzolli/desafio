package br.com.desafio.service;

import java.util.List;

import org.springframework.data.domain.Page;

import br.com.desafio.dto.CategoriasDTO;
import br.com.desafio.model.Categorias;

public interface CategoriasService {

    public Categorias findById(Integer idCategoria);

    public List<Categorias> findAll();

    public Page<Categorias> findPage(Integer page, Integer linesPage, String orderBy, String direction);

    public Categorias insert(Categorias categoria);

    public Categorias update(Categorias categoria);

    public void delete(Integer idCategoria);

    public Categorias fromDTO(CategoriasDTO objDTO);

}