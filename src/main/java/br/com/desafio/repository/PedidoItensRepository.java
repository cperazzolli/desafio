package br.com.desafio.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.desafio.model.PedidosItens;


@Repository
public interface PedidoItensRepository extends JpaRepository<PedidosItens, Integer> {

}