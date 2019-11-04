package br.com.desafio.service;

import java.util.List;

import org.springframework.data.domain.Page;

import br.com.desafio.model.PedidosItens;



public interface PedidosItensService {

    public PedidosItens findById(String idItem);

    public List<PedidosItens> findAll();

    public Page<PedidosItens> findByPage(Integer page, Integer linesPage, String orderBy, String direction);

    public PedidosItens insert(PedidosItens itens);

    public PedidosItens update(PedidosItens itens);

    public void delete(String idItem);
}