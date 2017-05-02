package br.com.svitorroberto.dao;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;

import br.com.svitorroberto.modelo.Inventario;
import br.com.svitorroberto.modelo.Usuario;

public class InventarioDao {

	// Com entityManager
	protected EntityManager entityManager;

	public InventarioDao() {
		entityManager = getEntityManager();
	}

	private EntityManager getEntityManager() {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("ApocalipseZumbi");
		if (entityManager == null) {
			entityManager = factory.createEntityManager();
		}

		return entityManager;
	}

	public Inventario getInventarioById(Long id) {
		Inventario inventario = new Inventario();
		try {
			inventario = (Inventario) entityManager.createQuery("SELECT c from Inventario c where c.id = :id")
					.setParameter("id", id).getSingleResult();
		} catch (NoResultException e) {
			return new Inventario();
		}
		return inventario;
	}

	public String removerInventarioById(Long id) {
		try {
			entityManager.createQuery("DELETE c from Inventario c where c.id = :id").setParameter("id", id)
					.executeUpdate();
		} catch (Exception e) {
			return "Erro";
		}
		return "Removido";
	}

	public void salvarNoInventario(Inventario inventario) {
		try {
			entityManager.getTransaction().begin();
			entityManager.persist(inventario);
			entityManager.getTransaction().commit();
		} catch (Exception ex) {
			ex.printStackTrace();
			entityManager.getTransaction().rollback();
		}
	}

	public ArrayList<Inventario> buscarNoInventario(Inventario inventario) {
		ArrayList<Inventario> itens;
		try {
			itens = (ArrayList<Inventario>) entityManager
					.createQuery("SELECT c from Inventario c where c.usuario.id = :id_usuario AND c.item.id = :id_item")
					.setParameter("id_usuario", inventario.getUsuario().getId())
					.setParameter("id_item", inventario.getItem().getId()).getResultList();
		} catch (NoResultException e) {
			return new ArrayList<>();
		}
		return itens;
	}
	
	public Inventario buscarUnicoInventario(Inventario inventario) {
		ArrayList<Inventario> itens;
		try {
			itens = (ArrayList<Inventario>) entityManager
					.createQuery("SELECT c from Inventario c where c.usuario.id = :id_usuario AND c.item.id = :id_item")
					.setParameter("id_usuario", inventario.getUsuario().getId())
					.setParameter("id_item", inventario.getItem().getId()).getResultList();
		} catch (NoResultException e) {
			return new Inventario();
		}
		return itens.get(0);
	}

	public void removerDoInventario(Inventario inventario) {
		try {
			entityManager.getTransaction().begin();
			entityManager.remove(inventario);
			entityManager.getTransaction().commit();
		} catch (Exception ex) {
			ex.printStackTrace();
			entityManager.getTransaction().rollback();
		}
	}

	public Collection<Inventario> getAll() {
		Collection<Inventario> inventarios = new ArrayList<Inventario>();
		try {
			inventarios = (Collection<Inventario>) entityManager.createQuery("SELECT c from Inventario c")
					.getResultList();
		} catch (NoResultException e) {
			return new ArrayList<Inventario>();
		}
		return inventarios;
	}

	public Collection<Inventario> getItensPorUsuario(Usuario usuario) {
		Collection<Inventario> inventarios = new ArrayList<Inventario>();
		try {
			inventarios = (Collection<Inventario>) entityManager
					.createQuery("SELECT c from Inventario c where c.usuario.id = :id_usuario")
					.setParameter("id_usuario", usuario.getId()).getResultList();
		} catch (NoResultException e) {
			return new ArrayList<Inventario>();
		}
		return inventarios;
	}
}
