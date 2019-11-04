package br.com.desafio.service.implementacaoService;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import br.com.desafio.dto.ProdutoDTO;
import br.com.desafio.exception.ObjectNotFoundException;
import br.com.desafio.model.Categorias;
import br.com.desafio.model.Produtos;
import br.com.desafio.repository.CategoriasRepository;
import br.com.desafio.repository.ProdutosRepository;
import br.com.desafio.service.ProdutosService;


@Service
public class ProdutosServiceImpl implements ProdutosService {

    @Autowired
    private ProdutosRepository produtosRepository;

    @Autowired
    private CategoriasRepository categoriasRepository;

    @Autowired
    private S3Service s3Service;

    @Override
    public Produtos findById(Integer idProduto) {
        Optional<Produtos> produto = produtosRepository.findById(idProduto);
		
		return produto.orElseThrow(() -> new ObjectNotFoundException(
				              "Produto não encontrado!"));
    }

    @Override
    public Page<Produtos> findByPage(String produto, List<Integer> ids,Integer page, Integer linesPage, String orderBy, String direction) {
        PageRequest pageRequest = PageRequest.of(page, linesPage, Direction.valueOf(direction),orderBy);
		List<Categorias> categorias = categoriasRepository.findAllById(ids);
        return produtosRepository.findDistinctByProdutoContainingAndCategorias(produto, categorias, pageRequest);
    }

    @Override
    public Produtos insert(Produtos produto) {
        produto.setIdProduto(null);
        return produtosRepository.save(produto);
    }


    @Override
    public Produtos update(Produtos produto) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void delete(Integer idProduto) {
        // TODO Auto-generated method stub 

    }

    @Override
    public URI uploadProdutoPicture(MultipartFile multipartFile) {
        return s3Service.uploadFile(multipartFile);
    }

}