package br.com.svitorroberto.dao;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;

import br.com.svitorroberto.modelo.Report;
import br.com.svitorroberto.modelo.Report;

public class ReportDao {
	//Com entityManager	
			protected EntityManager entityManager;
			 
		    public ReportDao() {
		        entityManager = getEntityManager();
		    }
			
			private EntityManager getEntityManager() {
		        EntityManagerFactory factory = Persistence.createEntityManagerFactory("ApocalipseZumbi");
		        if (entityManager == null) {
		            entityManager = factory.createEntityManager();
		        }
		 
		        return entityManager;
		    }
			
			public Report getReportById(Long id){
				Report report = new Report();
				try{
				 report = (Report) entityManager.createQuery("SELECT c from Report c where c.id = :id").setParameter("id", id).getSingleResult();
				}catch (NoResultException e) {
					return new Report();
				}
				return report;
			}
			
			public void salvarReport(Report report){
				try {
					entityManager.getTransaction().begin();
					entityManager.merge(report);
					entityManager.getTransaction().commit();
				} catch (Exception ex) {
		            ex.printStackTrace();
		            entityManager.getTransaction().rollback();
		        }
				
			}

			public Collection<Report> getAll() {
				Collection<Report> reports = new ArrayList<Report>();
				try{
					reports = (Collection<Report>) entityManager.createQuery("SELECT c from Report c").getResultList();
					}catch (NoResultException e) {
						return new ArrayList<Report>();
					}
				return reports;
			}
			public int getQtdReports(Long idReportReportado) {
				int qtdReports = 0;
				try{
					qtdReports =  (int) entityManager.createQuery("SELECT COUNT(DISTINCT c.reporter) FROM Report c where c.infectado = :reportInfectado").setParameter("reportInfectado", idReportReportado).getSingleResult();
				}catch (NoResultException e) {
					return -1;
				}
				return qtdReports;
			}
}
