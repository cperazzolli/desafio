package br.com.desafio.service.implementacaoService;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import br.com.desafio.dto.CategoriasDTO;
import br.com.desafio.exception.ObjectNotFoundException;
import br.com.desafio.model.Categorias;
import br.com.desafio.repository.CategoriasRepository;
import br.com.desafio.service.CategoriasService;

@Service
public class CategoriasServiceImpl implements CategoriasService {

    @Autowired
    private CategoriasRepository categoriasRepository;

    @Override
    public Categorias findById(Integer idCategoria) {
        Optional<Categorias> categoria = categoriasRepository.findById(idCategoria);
        return categoria.orElseThrow(() -> new ObjectNotFoundException(
                            "Categoria não encontrada!"
        ));
    }

    @Override
    public List<Categorias> findAll() {
        return categoriasRepository.findAll();
    }

    @Override
    public Page<Categorias> findPage(Integer page, Integer linesPage, String orderBy, String direction) {
        PageRequest pageRequest = PageRequest.of(page, linesPage, Direction.valueOf(direction),orderBy);
        return categoriasRepository.findAll(pageRequest);
    }

    @Override
    public Categorias insert(Categorias categoria) {
        categoria.setIdCategoria(null);
        return categoriasRepository.save(categoria);
    }

    @Override
    public Categorias update(Categorias categoria) {
        Categorias novaCategoria = findById(categoria.getIdCategoria());
        updateCategoria(novaCategoria,categoria);
        return categoriasRepository.save(novaCategoria);
    }

    private void updateCategoria(Categorias novaCategoria, Categorias categoria) {
        novaCategoria.setNomeCategoria(categoria.getNomeCategoria());
    }

    @Override
    public void delete(Integer idCategoria) {
        findById(idCategoria);
        try {
            categoriasRepository.deleteById(idCategoria);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityViolationException("Não e possivel apagar uma categoria que possui produtos");
        }
    }

    public Categorias fromDTO(CategoriasDTO objDTO) {
		return new Categorias(objDTO.getIdCategoria(),objDTO.getNome());
	}
}