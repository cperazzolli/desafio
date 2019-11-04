package br.com.desafio.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.desafio.model.Categorias;
import br.com.desafio.model.Produtos;

@Repository
public interface ProdutosRepository extends JpaRepository<Produtos, Integer> {
    
    @Transactional(readOnly=true)
	@Query("SELECT DISTINCT obj FROM Produtos obj INNER JOIN obj.categorias cat WHERE obj.produto LIKE %:produto% AND cat IN :categorias")
	Page<Produtos> findDistinctByProdutoContainingAndCategorias(@Param("produto") String produto,@Param("categorias") 
															 List<Categorias> categorias,Pageable pageRequest);
}