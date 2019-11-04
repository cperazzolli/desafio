package br.com.desafio.service.implementacaoService;

import java.util.Date;
import java.util.Optional;

import com.amazonaws.services.licensemanager.model.AuthorizationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import br.com.desafio.exception.ObjectNotFoundException;
import br.com.desafio.model.PedidosItens;
import br.com.desafio.model.Clientes;
import br.com.desafio.model.Pedidos;
import br.com.desafio.repository.PedidoItensRepository;
import br.com.desafio.repository.PedidosRepository;
import br.com.desafio.security.UserSS;
import br.com.desafio.service.ClientesService;
import br.com.desafio.service.PedidosService;
import br.com.desafio.service.ProdutosService;

@Service
public class PedidoServiceImpl implements PedidosService {

    @Autowired
    private PedidosRepository pedidosRepository;

    @Autowired
    private ClientesService clientesServices;

    @Autowired
    private ProdutosService produtosServices;

    @Autowired
    private PedidoItensRepository pedidoItensRepository;

    @Override
    public Pedidos findById(Integer idPedido) {
        Optional<Pedidos> obj = pedidosRepository.findById(idPedido);
		
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				       "Pedido não encontrado" + Pedidos.class.getName()));
    }

    @Override
    public Page<Pedidos> findByPage(Integer page, Integer linesPage, String orderBy, String direction) {
        UserSS user = UserService.authenticated();
		if(user == null) {
			throw new AuthorizationException("Acesso negado");
		}
        PageRequest pageRequest = PageRequest.of(page, linesPage, Direction.valueOf(direction),orderBy);
		Clientes cliente = clientesServices.findById(user.getId());
		return pedidosRepository.findByCliente(cliente, pageRequest);
    }

    @Override
    public Pedidos insert(Pedidos pedido) {
        pedido.setIdPedido(null);
        pedido.setData(new Date()); 
        pedido.setCliente(clientesServices.findById(pedido.getCliente().getIdCliente()));
        pedidosRepository.save(pedido);

        for(PedidosItens item: pedido.getItens()) {
            item.setProduto(produtosServices.findById(item.getProduto().getIdProduto()));
            item.setValor(item.getValor());
            item.setQuantidade(item.getQuantidade());
            item.setPedido(pedido);
        }        
        pedidoItensRepository.saveAll(pedido.getItens());
        return pedido;
    }

}