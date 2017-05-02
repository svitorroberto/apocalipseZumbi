package br.com.svitorroberto.dao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;

import br.com.svitorroberto.modelo.Inventario;
import br.com.svitorroberto.modelo.Usuario;

/**
 * @author Vítor Roberto
 *
 */
public class InventarioDao {

	private static final Logger LOGGER = Logger.getLogger(InventarioDao.class.getName());
	private static final String ID_USUARIO = ID_USUARIO;

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

	/**
	 * 
	 * @param id
	 * @return
	 */
	public Inventario getInventarioById(Long id) {
		Inventario inventario = new Inventario();
		try {
			inventario = (Inventario) entityManager.createQuery("SELECT c from Inventario c where c.id = :id")
					.setParameter("id", id).getSingleResult();
		} catch (NoResultException e) {
			LOGGER.info(e);
			return new Inventario();
		}
		return inventario;
	}

	/**
	 * 
	 * @param id
	 * @return
	 */
	public String removerInventarioById(Long id) {
		try {
			entityManager.createQuery("DELETE c from Inventario c where c.id = :id").setParameter("id", id)
					.executeUpdate();
		} catch (Exception e) {
			LOGGER.info(e);
			return "Erro";
		}
		return "Removido";
	}

	/**
	 * 
	 * @param inventario
	 */
	public void salvarNoInventario(Inventario inventario) {
		try {
			entityManager.getTransaction().begin();
			entityManager.persist(inventario);
			entityManager.getTransaction().commit();
		} catch (Exception ex) {
			LOGGER.info(ex);
			entityManager.getTransaction().rollback();
		}
	}

	/**
	 * 
	 * @param inventario
	 * @return
	 */
	public Collection<Inventario> buscarNoInventario(Inventario inventario) {
		ArrayList<Inventario> itens;
		try {
			itens = (ArrayList<Inventario>) entityManager
					.createQuery("SELECT c from Inventario c where c.usuario.id = :id_usuario AND c.item.id = :id_item")
					.setParameter("id_usuario", inventario.getUsuario().getId())
					.setParameter("id_item", inventario.getItem().getId()).getResultList();
		} catch (NoResultException e) {
			LOGGER.info(e);
			return new ArrayList<>();
		}
		return itens;
	}

	/**
	 * 
	 * @param inventario
	 * @return
	 */
	public Inventario buscarUnicoInventario(Inventario inventario) {
		ArrayList<Inventario> itens;
		try {
			itens = (ArrayList<Inventario>) entityManager
					.createQuery("SELECT c from Inventario c where c.usuario.id = :id_usuario AND c.item.id = :id_item")
					.setParameter(ID_USUARIO, inventario.getUsuario().getId())
					.setParameter("id_item", inventario.getItem().getId()).getResultList();
		} catch (NoResultException e) {
			LOGGER.info(e);
			return new Inventario();
		}
		return itens.get(0);
	}

	/**
	 * 
	 * @param inventario
	 */
	public void removerDoInventario(Inventario inventario) {
		try {
			entityManager.getTransaction().begin();
			entityManager.remove(inventario);
			entityManager.getTransaction().commit();
		} catch (Exception ex) {
			LOGGER.info(ex);
			entityManager.getTransaction().rollback();
		}
	}

	/**
	 * 
	 * @return
	 */
	public Collection<Inventario> getAll() {
		Collection<Inventario> inventarios = new ArrayList<>();
		try {
			inventarios = (Collection<Inventario>) entityManager.createQuery("SELECT c from Inventario c")
					.getResultList();
		} catch (NoResultException e) {
			LOGGER.info(e);
			return new ArrayList<>();
		}
		return inventarios;
	}

	/**
	 * 
	 * @param usuario
	 * @return
	 */
	public Collection<Inventario> getItensPorUsuario(Usuario usuario) {
		Collection<Inventario> inventarios = new ArrayList<>();
		try {
			inventarios = (Collection<Inventario>) entityManager
					.createQuery("SELECT c from Inventario c where c.usuario.id = :id_usuario")
					.setParameter(ID_USUARIO, usuario.getId()).getResultList();
		} catch (NoResultException e) {
			LOGGER.info(e);
			return new ArrayList<>();
		}
		return inventarios;
	}
}
