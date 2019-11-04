package br.com.desafio.dto;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

public class ClienteNewDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotEmpty(message="Preenchimento obrigatório")
	@Length(min=5,max=80,message="O tamanho deve ser entre 5 e 80 caracteres")
	private String nome;
	
	@NotEmpty(message="Preenchimento Obrigatorio")
	@Email(message="Email invalido")
	private String email;
	
	@NotEmpty(message="Preenchimento Obrigatorio")
	private String senha;
	
	@NotEmpty(message="Preenchimento Obrigatorio")
	private String rua;
	
	@NotEmpty(message="Preenchimento Obrigatorio")
    private String bairro;
    
    @NotEmpty(message="Preenchimento Obrigatorio")
	private String cidade;
	
	@NotEmpty(message="Preenchimento Obrigatorio")
	private String cep;
    
    @NotEmpty(message="Preenchimento Obrigatorio")
	private String estado;

    public ClienteNewDTO() {
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    
    
}