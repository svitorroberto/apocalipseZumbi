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
		
		public String salvarUsuario(Usuario usuario){
			try {
				entityManager.getTransaction().begin();
				entityManager.persist(usuario);
				entityManager.getTransaction().commit();
			} catch (Exception ex) {
	            entityManager.getTransaction().rollback();
	            return "Erro";
	        }
			return "Salvo com sucesso";
		}
		
		public void atualizarUsuario(Usuario usuario){
			try {
				entityManager.getTransaction().begin();
				entityManager.merge(usuario);
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

		public Usuario atualizarLocalizacao(Usuario usuario) {
			try{
				entityManager.getTransaction().begin();
				entityManager.merge(usuario);
				entityManager.getTransaction().commit();
			}catch (NoResultException e) {
				e.printStackTrace();
			}
			return usuario;
		}

		public Collection<Usuario> getUsuariosInfectados() {
			Collection<Usuario> usuarios = new ArrayList<Usuario>();
			try{
				usuarios = (Collection<Usuario>) entityManager.createQuery("SELECT c from Usuario c WHERE c.isInfectado = 'S'").getResultList();
				}catch (Exception e) {
					e.printStackTrace();
				}
			return usuarios;
		}

		public Long getPontosPerdidos() {
			Long pontos = null;
			try{
				pontos =  (Long) entityManager.createQuery("SELECT sum(i.pontos) from Inventario v inner join v.usuario u inner join v.item i WHERE u.isInfectado = 'S'").getSingleResult();
			}catch (Exception e) {
					e.printStackTrace();
				}
			return pontos;
		}
		
		public Double getQtdUsuarioAtivos(){
			return Double.valueOf( (Long)entityManager.createQuery("SELECT COUNT(c) from Usuario c WHERE c.isInfectado = 'N'").getSingleResult());
		}
		
		public ArrayList<Double> mediaItemPorUsuario(){
			ArrayList<Double> quantidades = new ArrayList<>();
			try{
				for(Long i=1L;i<5;i++){
					quantidades.add( Double.valueOf( (Long)entityManager.createQuery("SELECT COUNT(DISTINCT u.id) from Inventario v inner join v.usuario u inner join v.item i WHERE u.isInfectado = 'N' AND i.id = :item").setParameter("item", i).getSingleResult()));
				}
			}catch (Exception e) {
					e.printStackTrace();
				}
			return quantidades;
		}
		
		
		
}
