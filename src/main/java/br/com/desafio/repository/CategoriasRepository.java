package br.com.desafio.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.desafio.model.Categorias;

@Repository
public interface CategoriasRepository extends JpaRepository<Categorias, Integer> {

}