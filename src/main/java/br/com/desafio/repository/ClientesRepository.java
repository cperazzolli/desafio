package br.com.desafio.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.desafio.model.Clientes;

@Repository
public interface ClientesRepository extends JpaRepository<Clientes, Integer> {

    @Transactional(readOnly=true)
	Clientes findByEmail(String email);
}