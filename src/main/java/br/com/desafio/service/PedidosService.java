package br.com.desafio.service;

import org.springframework.data.domain.Page;

import br.com.desafio.model.Pedidos;

public interface PedidosService {

    public Pedidos findById(Integer idPedido);

    public Page<Pedidos> findByPage(Integer page, Integer linesPage, String orderBy, String direction);

    public Pedidos insert(Pedidos pedido);

}