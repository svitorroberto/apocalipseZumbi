package br.com.svitorroberto.modelo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="usuario")
public class Usuario {
	
	@Id
	@Column(name="id")
	private Long id;
	
	@Column(name="nome")
	private String nome;
	
	@Column(name="idade")
	private int idade;
	
	@Column(name="sexo")
	private char sexo;
	
	@Column(name="localizacao")
	private String ultimaLocalizacao;

	@Column(name="infectado")
	private char isInfectado;
	

	
	public Usuario(Long id, String nome,int idade,char sexo,String ultimaLocalizacao,char isInfectado){
		this.id = id;
		this.nome = nome;
		this.idade = idade;
		this.sexo = sexo;
		this.ultimaLocalizacao = ultimaLocalizacao;
		this.isInfectado = isInfectado;
	}
	
	public Usuario(Long id){
		this.id = id;
	}

	public Usuario(){
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getIdade() {
		return idade;
	}

	public void setIdade(int idade) {
		this.idade = idade;
	}

	public char getSexo() {
		return sexo;
	}

	public void setSexo(char sexo) {
		this.sexo = sexo;
	}

	public String getUltimaLocalizacao() {
		return ultimaLocalizacao;
	}

	public void setUltimaLocalizacao(String ultimaLocalizacao) {
		this.ultimaLocalizacao = ultimaLocalizacao;
	}

	public char getIsInfectado() {
		return isInfectado;
	}

	public void setIsInfectado(char isInfectado) {
		this.isInfectado = isInfectado;
	}

	
}
