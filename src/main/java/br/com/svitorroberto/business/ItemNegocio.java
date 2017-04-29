package br.com.svitorroberto.business;

import java.util.Collection;

import br.com.svitorroberto.dao.ItemDao;
import br.com.svitorroberto.modelo.Item;

public class ItemNegocio {
	
	public void salvar(Item item){
		ItemDao itemDao = new ItemDao();
		itemDao.salvarItem(item);
	}
	
	public Item recuperarPorId(Item item){
		ItemDao itemDao = new ItemDao();
		return itemDao.getItemById(item.getId());
	}

	public Collection<Item> recuperarTodos() {
		ItemDao itemDao = new ItemDao();
		return itemDao.getAll();
	}
	
}
