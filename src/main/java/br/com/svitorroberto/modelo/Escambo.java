package br.com.svitorroberto.modelo;

import java.util.ArrayList;
import java.util.Collection;

/**
 * @author Newbie
 *
 */
public class Escambo {
	
	private Usuario cambista;
	private Collection<Item> itens;
	
	public Escambo(Usuario cambista,ArrayList<Item> itens) {
		this.cambista = cambista;
		this.itens= itens;
	}
	public Escambo() {
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
