package br.com.desafio.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.desafio.model.Categorias;
import br.com.desafio.model.Clientes;
import br.com.desafio.model.Pedidos;
import br.com.desafio.model.PedidosItens;
import br.com.desafio.model.Produtos;
import br.com.desafio.model.Enuns.Perfil;
import br.com.desafio.model.Enuns.Status;
import br.com.desafio.repository.CategoriasRepository;
import br.com.desafio.repository.ClientesRepository;
import br.com.desafio.repository.PedidoItensRepository;
import br.com.desafio.repository.PedidosRepository;
import br.com.desafio.repository.ProdutosRepository;

@Service
public class DBService {

    @Autowired
    private CategoriasRepository categoriasService;

    @Autowired
    private ProdutosRepository produtosService;


    @Autowired
    private ClientesRepository clientesService;

    @Autowired
    private PedidosRepository pedidosService;

    @Autowired
    private PedidoItensRepository pedidoItensRepository;
    
    @Autowired
	private BCryptPasswordEncoder pe;

    

    public void instantiateTestDatabase() throws ParseException {

        Categorias cat1 = new Categorias(null, "Informatica");
		Categorias cat2 = new Categorias(null, "Escritorio");
		Categorias cat3 = new Categorias(null, "Cama mesa e banho");
		Categorias cat4 = new Categorias(null, "Eletronicos");
		Categorias cat5 = new Categorias(null, "Jardinagem");
        Categorias cat6 = new Categorias(null, "Decoração");
        
        Categorias cat7 = new Categorias(null, "Perfumaria");

        categoriasService.save(cat1);
        categoriasService.save(cat2);
        categoriasService.save(cat3);
        categoriasService.save(cat4);
        categoriasService.save(cat5);
        categoriasService.save(cat6);
        categoriasService.save(cat7);

        Produtos p1 = new Produtos(null, "Computador", 2000.00, 100L, "Computador Dell", null);
		Produtos p2 = new Produtos(null,"Impressora", 800.00,10L,"Impressora Hp",null);
		Produtos p3 = new Produtos(null, "Mouse", 80.00,10L,"Impressora Hp",null);
		Produtos p4 = new Produtos(null, "Mesa de escritorio", 300.00,10L,"Mesa de madeira morrom",null);
		Produtos p5 = new Produtos(null, "Toalha", 50.00,10L,"Toalha confort",null);
		Produtos p6 = new Produtos(null, "Colcha", 2000.00,10L,"Coucha casal",null);
		Produtos p7 = new Produtos(null, "TV true color", 1200.00,10L,"40 polegadas",null);
		Produtos p8 = new Produtos(null, "Roçadeira", 800.00,10L,"A gasolina",null);
		Produtos p9 = new Produtos(null, "Abajour", 100.00,10L,"Disponivel nas cores branca e marrom",null);
		Produtos p10 = new Produtos(null, "Pendente", 180.00,10L,"Impressora Hp",null);
		Produtos p11= new Produtos(null, "Shampoo", 90.00,10L,"Shampoo dove",null);
        
        cat1.getProdutos().addAll(Arrays.asList(p1,p2,p3));
		cat2.getProdutos().addAll(Arrays.asList(p2,p4));
		cat3.getProdutos().addAll(Arrays.asList(p5,p6));
		cat4.getProdutos().addAll(Arrays.asList(p1,p2,p3,p7));
		cat5.getProdutos().addAll(Arrays.asList(p8));
		cat6.getProdutos().addAll(Arrays.asList(p9,p10));
		cat7.getProdutos().addAll(Arrays.asList(p11));
		
		p1.getCategorias().addAll(Arrays.asList(cat1,cat4));
		p2.getCategorias().addAll(Arrays.asList(cat1,cat2,cat4));
		p3.getCategorias().addAll(Arrays.asList(cat1,cat4));
		p4.getCategorias().addAll(Arrays.asList(cat2));
		p5.getCategorias().addAll(Arrays.asList(cat3));
		p6.getCategorias().addAll(Arrays.asList(cat3));
		p7.getCategorias().addAll(Arrays.asList(cat4));
		p8.getCategorias().addAll(Arrays.asList(cat5));
		p9.getCategorias().addAll(Arrays.asList(cat6));
		p10.getCategorias().addAll(Arrays.asList(cat6));
        p11.getCategorias().addAll(Arrays.asList(cat7));
        
        produtosService.save(p1);
        produtosService.save(p2);
        produtosService.save(p3);
        produtosService.save(p4);
        produtosService.save(p5);
        produtosService.save(p6);
        produtosService.save(p7);
        produtosService.save(p8);
        produtosService.save(p9);
        produtosService.save(p10);
        produtosService.save(p11);

        Clientes cli1 = new Clientes(null, "Maria Silva", "maria.silva@gmail.com", pe.encode("123"), 
                                     "Rua Anelia Silva", "baruri", "centro", "00000-000", "SP");

        Clientes cli2 = new Clientes(null, "Ana Costa", "cperazzolli@outlook.com", pe.encode("123"), 
                                    "Rua Amelia souza","Boituva", "centro", "00000-000", "SP");
		cli2.addPerfis(Perfil.ADMIN);
		
        clientesService.save(cli1);
        clientesService.save(cli2);
        
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyy hh:MM");
        Pedidos ped1 = new Pedidos(null, sdf.parse("30/09/2017 10:32"),Status.PENDENTE,null,cli1);
        Pedidos ped2 = new Pedidos(null, sdf.parse("10/10/2017 19:35"),Status.PENDENTE,null,cli2);
		
		
		cli1.getPedidos().addAll(Arrays.asList(ped1,ped2)); 
		
        pedidosService.save(ped1);
        pedidosService.save(ped2);
		
		
		PedidosItens ip1 = new PedidosItens(null,ped1, p1, 1L, 2000.00);
		PedidosItens ip2 = new PedidosItens(null,ped1, p3, 2L, 80.00);
        PedidosItens ip3 = new PedidosItens(null,ped2, p2,  1L, 800.00);
		
		ped1.getItens().addAll(Arrays.asList(ip1,ip2));
		ped2.getItens().addAll(Arrays.asList(ip3));
		
		pedidoItensRepository.saveAll(Arrays.asList(ip1,ip2,ip3));

    }
}