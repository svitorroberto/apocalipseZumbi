package br.com.svitorroberto.modelo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="inventario")
public class Inventario {

	@Id
	@GeneratedValue
	@Column(name="id")
	private Long id;
	
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="id_usuario")
	private Usuario usuario;
	
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="id_item")
	private Item item;

	public Inventario() {
		
	}
	
	public Inventario(Long id){
		this.id = id;
	}
	
	public Inventario(Item item, Usuario usuario){
		this.item = item;
		this.usuario = usuario;
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
