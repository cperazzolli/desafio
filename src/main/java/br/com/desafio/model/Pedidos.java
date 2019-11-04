package br.com.desafio.model;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.desafio.model.Enuns.Status;

@Entity
public class Pedidos implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idPedido;

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    private Date data;

    private Status Status;

    private String sessao;

    @ManyToOne 
	@JoinColumn(name="cliente_id")
    private Clientes cliente;
    
    @OneToMany(mappedBy="id.pedido")
	private Set<PedidosItens> itens = new HashSet<>();

    public Pedidos() {
    }

    public Pedidos(Integer idPedido,Date data, Status status, String sessao, Clientes cliente) {
        this.idPedido = idPedido;
        this.data = data;
        Status = status;
        this.sessao = sessao;
        this.cliente = cliente;
    }

    public double getValorTotal() {
		double soma = 0.0;
		for(PedidosItens ip : itens) {
			soma = soma + ip.getSubTotal();
		}
		return soma;
	}

    public Integer getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(Integer idPedido) {
        this.idPedido = idPedido;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public Status getStatus() {
        return Status;
    }

    public void setStatus(Status status) {
        Status = status;
    }

    public String getSessao() {
        return sessao;
    }

    public void setSessao(String sessao) {
        this.sessao = sessao;
    }

    public Clientes getCliente() {
        return cliente;
    }

    public void setCliente(Clientes cliente) {
        this.cliente = cliente;
    }

    public Set<PedidosItens> getItens() {
        return itens;
    }

    public void setItens(Set<PedidosItens> itens) {
        this.itens = itens;
    }

    

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((idPedido == null) ? 0 : idPedido.hashCode());
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
        Pedidos other = (Pedidos) obj;
        if (idPedido == null) {
            if (other.idPedido != null)
                return false;
        } else if (!idPedido.equals(other.idPedido))
            return false;
        return true;
    }

    

}