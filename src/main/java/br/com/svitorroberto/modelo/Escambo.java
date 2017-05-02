package br.com.svitorroberto.modelo;

import java.util.Collection;

/**
 * @author V�tor Roberto
 *
 */
public class Escambo {

	private Usuario cambista;
	private Collection<Item> itens;

	/**
	 * 
	 * @param cambista
	 * @param itens
	 */
	public Escambo(Usuario cambista, Collection<Item> itens) {
		this.cambista = cambista;
		this.itens = itens;
	}

	public Escambo() {
		// Construtor vazio
	}

	public Usuario getCambista() {
		return cambista;
	}

	public void setCambista(Usuario cambista) {
		this.cambista = cambista;
	}

	public Collection<Item> getItens() {
		return itens;
	}

	public void setItens(Collection<Item> itens) {
		this.itens = itens;
	}
}
