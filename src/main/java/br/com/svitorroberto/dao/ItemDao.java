package br.com.svitorroberto.dao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;

import br.com.svitorroberto.modelo.Item;

public class ItemDao {

	private static final Logger LOGGER = Logger.getLogger(ItemDao.class.getName());

	// Com entityManager
	protected EntityManager entityManager;

	public ItemDao() {
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
	public Item getItemById(Long id) {
		Item item = new Item();
		try {
			item = (Item) entityManager.createQuery("SELECT c from Item c where c.id = :id").setParameter("id", id)
					.getSingleResult();
		} catch (NoResultException e) {
			LOGGER.info(e);
			return new Item();
		}
		return item;
	}

	/**
	 * 
	 * @param item
	 */
	public void salvarItem(Item item) {
		try {
			entityManager.getTransaction().begin();
			entityManager.persist(item);
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
	public Collection<Item> getAll() {
		Collection<Item> items = new ArrayList<>();
		try {
			items = (Collection<Item>) entityManager.createQuery("SELECT c from Item c").getResultList();
		} catch (NoResultException e) {
			LOGGER.info(e);
			return new ArrayList<>();
		}
		return items;
	}

}
