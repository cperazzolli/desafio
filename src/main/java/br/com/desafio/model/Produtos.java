package br.com.desafio.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Produtos implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idProduto;

    private String produto;

    private Double preco;

    private Long quantidade;

    private String descricao;

    private String foto;

    @JsonIgnore
	@ManyToMany
	@JoinTable(name="PRODUTO_CATEGORIA",
		joinColumns= @JoinColumn(name="produto_id"),
		inverseJoinColumns = @JoinColumn(name = "categoria_id")
	)
    private List<Categorias> categorias = new ArrayList<Categorias>();
    
    @JsonIgnore
	@OneToMany(mappedBy="id.produto")
	private Set<PedidosItens> itens = new HashSet<>();

    public Produtos() {
    }

    public Produtos(Integer idProduto, String produto, Double preco, Long quantidade, 
                    String descricao, String foto) {
        this.idProduto = idProduto;
        this.produto = produto;
        this.preco = preco;
        this.quantidade = quantidade;
        this.descricao = descricao;
        this.foto = foto;
    }

    @JsonIgnore
	public List<Pedidos> getPedidos(){
	 	List<Pedidos> lista = new ArrayList<>();
	 	for(PedidosItens x:itens) {
	 		lista.add(x.getPedido());
	 	}
	 	return lista;
	}

    public Set<PedidosItens> getItens() {
        return itens;
    }

    public void setItens(Set<PedidosItens> itens) {
        this.itens = itens;
    }
    
    public Integer getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(Integer idProduto) {
        this.idProduto = idProduto;
    }

    public String getProduto() {
        return produto;
    }

    public void setProduto(String produto) {
        this.produto = produto;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public Long getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Long quantidade) {
        this.quantidade = quantidade;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public List<Categorias> getCategorias() {
        return categorias;
    }

    public void setCategorias(List<Categorias> categorias) {
        this.categorias = categorias;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((idProduto == null) ? 0 : idProduto.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Produtos other = (Produtos) obj;
        if (idProduto == null) {
            if (other.idProduto != null)
                return false;
        } else if (!idProduto.equals(other.idProduto))
            return false;
        return true;
    }

}