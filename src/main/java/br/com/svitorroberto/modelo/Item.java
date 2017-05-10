package br.com.svitorroberto.modelo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author V�tor Roberto
 *
 */
@Entity
@Table(name = "item")
public class Item {

	@Id
	@GeneratedValue
	@Column(name = "id")
	private Long id;

	@Column(name = "descricao")
	private String descricao;

	@Column(name = "pontos")
	private int pontos;

	/**
	 * 
	 * @param id
	 */
	public Item(Long id) {
		this.id = id;
	}
	/**
	 * 
	 * @param id
	 */
	public Item(Long id, int pontos) {
		this.id = id;
		this.pontos = pontos;
	}

	public Item() { // Construtor vazio
	}

	public Item(int pontos) {
		this.pontos = pontos;
	}
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public int getPontos() {
		return pontos;
	}

	public void setPontos(int pontos) {
		this.pontos = pontos;
	}

}
