package br.com.desafio.service;

import java.util.List;

import org.springframework.data.domain.Page;

import br.com.desafio.dto.ClienteDTO;
import br.com.desafio.dto.ClienteNewDTO;
import br.com.desafio.model.Clientes;

public interface ClientesService {

    public Clientes findById(Integer idCliente);

    public List<Clientes> findAll();

    public Page<Clientes> findByPage(Integer page, Integer linesPage, String orderBy, String direction);

    public Clientes insert(Clientes cliente);

    public Clientes update(Clientes cliente);

    public void delete(Integer idCliente);

    public Clientes fromDTO(ClienteDTO objDTO);

    public Clientes fromDTO(ClienteNewDTO objDTO);

    public Clientes findByEmail(String email);
}