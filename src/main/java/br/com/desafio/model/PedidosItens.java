package br.com.desafio.model;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class PedidosItens implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonIgnore
    @EmbeddedId
    private PedidoItemPK id = new PedidoItemPK(); 

    private Long quantidade;

    private Double valor;

    public PedidosItens() {
    }

    public PedidosItens(Integer idItem,Pedidos pedido,Produtos produto, Long quantidade, Double valor) {
        super();
        id.setPedido(pedido);
        id.setProduto(produto);
        this.quantidade = quantidade;
        this.valor = valor;
    }

    public double getSubTotal() {
		return valor * quantidade;
    }
    
    @JsonIgnore
	public Pedidos getPedido() {
		return id.getPedido();
	}
	
	public void setPedido(Pedidos pedido) {
		id.setPedido(pedido);
	}
	
	public Produtos getProduto() {
		return id.getProduto();
	}
	
	public void setProduto(Produtos produto) {
		id.setProduto(produto);
	}

    public PedidoItemPK getId() {
        return id;
    }

    public void setId(PedidoItemPK id) {
        this.id = id;
    }
    
    public Long getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Long quantidade) {
        this.quantidade = quantidade;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
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
        PedidosItens other = (PedidosItens) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

    
}