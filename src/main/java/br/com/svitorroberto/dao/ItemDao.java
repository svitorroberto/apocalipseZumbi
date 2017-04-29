package br.com.svitorroberto.dao;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;

import br.com.svitorroberto.modelo.Item;

public class ItemDao {

	//Com entityManager	
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
		
		public Item getItemById(Long id){
			Item item = new Item();
			try{
			 item = (Item) entityManager.createQuery("SELECT c from Item c where c.id = :id").setParameter("id", id).getSingleResult();
			}catch (NoResultException e) {
				return new Item();
			}
			return item;
		}
		
		public void salvarItem(Item item){
			try {
				String f2 = String.valueOf(entityManager.createQuery("select max(id) from Item").getSingleResult());
				Long codigo = Long.valueOf(f2)+1L;
				item.setId(codigo);
				entityManager.getTransaction().begin();
				entityManager.persist(item);
				entityManager.getTransaction().commit();
			} catch (Exception ex) {
	            ex.printStackTrace();
	            entityManager.getTransaction().rollback();
	        }
			
		}

		public Collection<Item> getAll() {
			Collection<Item> items = new ArrayList<Item>();
			try{
				items = (Collection<Item>) entityManager.createQuery("SELECT c from Item c").getResultList();
				}catch (NoResultException e) {
					return new ArrayList<Item>();
				}
			return items;
		}
}
