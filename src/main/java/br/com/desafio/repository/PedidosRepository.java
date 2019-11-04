package br.com.desafio.repository;



import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.desafio.model.Clientes;
import br.com.desafio.model.Pedidos;

@Repository
public interface PedidosRepository extends JpaRepository<Pedidos, Integer> {

    @Transactional(readOnly  = true)
	Page<Pedidos> findByCliente(Clientes cliente,Pageable pageRequest);

}