package br.com.svitorroberto.business;

import java.util.Collection;

import br.com.svitorroberto.dao.InventarioDao;
import br.com.svitorroberto.modelo.Inventario;

public class InventarioNegocio {
	
	public void salvar(Inventario inventario){
		InventarioDao inventarioDao = new InventarioDao();
		inventarioDao.salvarInventario(inventario);
	}
	
	public Inventario recuperarPorId(Inventario inventario){
		InventarioDao inventarioDao = new InventarioDao();
		return inventarioDao.getInventarioById(inventario.getId());
	}

	public Collection<Inventario> recuperarTodos() {
		InventarioDao inventarioDao = new InventarioDao();
		return inventarioDao.getAll();
	}
	
}
