package br.com.svitorroberto.dao;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;

import br.com.svitorroberto.modelo.Inventario;

public class InventarioDao {

	//Com entityManager	
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
		
		public Inventario getInventarioById(Long id){
			Inventario inventario = new Inventario();
			try{
			 inventario = (Inventario) entityManager.createQuery("SELECT c from Inventario c where c.id = :id").setParameter("id", id).getSingleResult();
			}catch (NoResultException e) {
				return new Inventario();
			}
			return inventario;
		}
		
		public void salvarInventario(Inventario inventario){
			try {
				String f2 = String.valueOf(entityManager.createQuery("select max(id) from Inventario").getSingleResult());
				Long codigo = Long.valueOf(f2)+1L;
				inventario.setId(codigo);
				entityManager.getTransaction().begin();
				entityManager.persist(inventario);
				entityManager.getTransaction().commit();
			} catch (Exception ex) {
	            ex.printStackTrace();
	            entityManager.getTransaction().rollback();
	        }
			
		}

		public Collection<Inventario> getAll() {
			Collection<Inventario> inventarios = new ArrayList<Inventario>();
			try{
				inventarios = (Collection<Inventario>) entityManager.createQuery("SELECT c from Inventario c").getResultList();
				}catch (NoResultException e) {
					return new ArrayList<Inventario>();
				}
			return inventarios;
		}
}
