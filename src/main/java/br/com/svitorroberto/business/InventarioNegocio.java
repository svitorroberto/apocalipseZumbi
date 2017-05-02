package br.com.svitorroberto.business;

import java.util.ArrayList;
import java.util.Collection;

import br.com.svitorroberto.dao.InventarioDao;
import br.com.svitorroberto.dao.ItemDao;
import br.com.svitorroberto.dao.UsuarioDao;
import br.com.svitorroberto.modelo.Escambo;
import br.com.svitorroberto.modelo.Inventario;
import br.com.svitorroberto.modelo.Item;
import br.com.svitorroberto.modelo.Usuario;

/**
 * @author Vítor Roberto
 *
 */
public class InventarioNegocio {
	InventarioDao inventarioDao;
	UsuarioDao usuarioDao;
	ItemDao itemDao;

	/**
	 * 
	 * @param item
	 * @param usuario
	 * @return
	 */
	public String adicinarItemNoInventario(Item item, Usuario usuario) {
		if (inventarioValido(new Inventario(item, usuario)) && validarSeTemItens(new Inventario(item, usuario))) {
			return "Ação inválida";
		} else {
			inventarioDao.salvarNoInventario(new Inventario(item, usuario));
			return "Item incluido com sucesso";
		}
	}

	/**
	 * 
	 * @param item
	 * @param usuario
	 * @return
	 */
	public String removerItemNoInventario(Item item, Usuario usuario) {
		if (inventarioValido(new Inventario(item, usuario))) {
			return "Ação inválida";
		} else {
			ArrayList<Inventario> itens = (ArrayList<Inventario>) inventarioDao
					.buscarNoInventario(new Inventario(item, usuario));
			if (itens.isEmpty()) {
				return "Item não disponível no inventário";
			} else {
				inventarioDao.removerDoInventario(itens.get(0));
				return "Item removido com sucesso";
			}
		}
	}

	/**
	 * 
	 * @param inventario
	 * @return
	 */
	public Inventario recuperarPorId(Inventario inventario) {
		return inventarioDao.getInventarioById(inventario.getId());
	}

	/**
	 * 
	 * @return
	 */
	public Collection<Inventario> recuperarTodos() {
		return inventarioDao.getAll();
	}

	/**
	 * 
	 * @param solicitante
	 * @param aceitador
	 * @return
	 */
	public String realizarEscambo(Escambo solicitante, Escambo aceitador) {
		if (!escamboValido(solicitante.getItens(), aceitador.getItens())) {
			return "Quantidade de pontos totais precisam ser iguais";
		}
		for (Item item : solicitante.getItens()) {
			if (inventarioValido(new Inventario(item, solicitante.getCambista()))) {
				return "Usuário não possui estes itens no inventário";
			}
		}
		for (Item item : aceitador.getItens()) {
			if (inventarioValido(new Inventario(item, aceitador.getCambista()))) {
				return "Usuário não possui estes itens no inventário";
			}
		}
		operarItens(true, solicitante.getItens(), aceitador.getCambista());
		operarItens(false, solicitante.getItens(), solicitante.getCambista());
		operarItens(true, aceitador.getItens(), solicitante.getCambista());
		operarItens(false, aceitador.getItens(), aceitador.getCambista());

		return "Escambo realizado com sucesso";
	}

	/**
	 * 
	 * @param inventario
	 * @return
	 */
	private Boolean inventarioValido(Inventario inventario) {
		Item item2 = itemDao.getItemById(inventario.getItem().getId());
		Usuario usuario2 = usuarioDao.getUsuarioById(inventario.getUsuario().getId());

		return item2.getId() == null || usuario2.getId() == null || usuario2.getIsInfectado() == 'S';
	}

	/**
	 * 
	 * @param itens1
	 * @param itens2
	 * @return
	 */
	private Boolean escamboValido(Collection<Item> itens1, Collection<Item> itens2) {
		Integer pontos1 = itens1.stream().mapToInt(i -> i.getPontos()).sum();
		Integer pontos2 = itens2.stream().mapToInt(i -> i.getPontos()).sum();

		return pontos1 == pontos2;
	}

	/**
	 * 
	 * @param inserir
	 * @param itens
	 * @param usuario
	 */
	private void operarItens(Boolean inserir, Collection<Item> itens, Usuario usuario) {
		if (inserir) {
			itens.forEach(i -> inventarioDao.salvarNoInventario(new Inventario(new Item(i.getId()), usuario)));
		} else {
			itens.forEach(i -> {
				removerItemNoInventario(new Item(i.getId()), usuario);
			});
		}
	}

	/**
	 * 
	 * @param inventario
	 * @return
	 */
	private boolean validarSeTemItens(Inventario inventario) {
		ArrayList<Inventario> inventarios = inventarioDao.buscarNoInventario(inventario);
		return inventarios.size() == 0;
	}

	public InventarioDao getInventarioDao() {
		return inventarioDao;
	}

	public void setInventarioDao(InventarioDao inventarioDao) {
		this.inventarioDao = inventarioDao;
	}

	public UsuarioDao getUsuarioDao() {
		return usuarioDao;
	}

	public void setUsuarioDao(UsuarioDao usuarioDao) {
		this.usuarioDao = usuarioDao;
	}

	public ItemDao getItemDao() {
		return itemDao;
	}

	public void setItemDao(ItemDao itemDao) {
		this.itemDao = itemDao;
	}

}
