package br.com.svitorroberto.modelo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="inventario")
public class Inventario {

	@Id
	@Column(name="id")
	private Long id;
	
	@Column(name="id_usuario")
	private Usuario usuario;
	
	@Column(name="id_item")
	private Item item;

	public Inventario() {
		
	}
	
	public Inventario(Long id){
		this.id = id;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}
	
	
}
