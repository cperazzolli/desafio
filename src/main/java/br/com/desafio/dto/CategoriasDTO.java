package br.com.desafio.dto;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import br.com.desafio.model.Categorias;


public class CategoriasDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer idCategoria;

    @NotEmpty(message="Preenchimento obrigatório")
	@Length(min=5,max=80,message="O tamanho deve ser entre 5 e 80 caracteres")
    private String nome;

    public CategoriasDTO() {
    }

    public CategoriasDTO(Categorias categorias) {
        idCategoria = categorias.getIdCategoria();
        nome = categorias.getNomeCategoria();
    }

    public Integer getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(Integer idCategoria) {
        this.idCategoria = idCategoria;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
   
    
}