package br.com.desafio.service;

import java.net.URI;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

import br.com.desafio.model.Produtos;

public interface ProdutosService {
    
    public Produtos findById(Integer idProduto);

    public Page<Produtos> findByPage(String nome, List<Integer> ids,Integer page, Integer linesPage, String orderBy, String direction);

    public Produtos insert(Produtos produto);

    public Produtos update(Produtos produto);

    public void delete(Integer idProduto);

    public URI uploadProdutoPicture(MultipartFile multipartFile);
}