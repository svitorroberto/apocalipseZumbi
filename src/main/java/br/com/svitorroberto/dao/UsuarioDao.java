package br.com.svitorroberto.dao;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;

import br.com.svitorroberto.modelo.Usuario;

public class UsuarioDao {

	//Com entityManager	
		protected EntityManager entityManager;
		 
	    public UsuarioDao() {
	        entityManager = getEntityManager();
	    }
		
		private EntityManager getEntityManager() {
	        EntityManagerFactory factory = Persistence.createEntityManagerFactory("ApocalipseZumbi");
	        if (entityManager == null) {
	            entityManager = factory.createEntityManager();
	        }
	 
	        return entityManager;
	    }
		
		public Usuario getUsuarioById(Long id){
			Usuario usuario = new Usuario();
			try{
			 usuario = (Usuario) entityManager.createQuery("SELECT c from Usuario c where c.id = :id").setParameter("id", id).getSingleResult();
			}catch (NoResultException e) {
				return new Usuario();
			}
			return usuario;
		}
		
		public void salvarUsuario(Usuario usuario){
			try {
				String f2 = String.valueOf(entityManager.createQuery("select max(id) from Usuario").getSingleResult());
				Long codigo = Long.valueOf(f2)+1L;
				usuario.setId(codigo);
				entityManager.getTransaction().begin();
				entityManager.persist(usuario);
				entityManager.getTransaction().commit();
			} catch (Exception ex) {
	            ex.printStackTrace();
	            entityManager.getTransaction().rollback();
	        }
			
		}

		public Collection<Usuario> getAll() {
			Collection<Usuario> usuarios = new ArrayList<Usuario>();
			try{
				usuarios = (Collection<Usuario>) entityManager.createQuery("SELECT c from Usuario c").getResultList();
				}catch (NoResultException e) {
					return new ArrayList<Usuario>();
				}
			return usuarios;
		}
}
