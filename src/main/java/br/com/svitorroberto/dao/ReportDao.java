package br.com.svitorroberto.dao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;

import br.com.svitorroberto.modelo.Report;

public class ReportDao {

	private static final Logger LOGGER = Logger.getLogger(ReportDao.class.getName());

	// Com entityManager
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

	/**
	 * 
	 * @param id
	 * @return
	 */
	public Report getReportById(Long id) {
		Report report = new Report();
		try {
			report = (Report) entityManager.createQuery("SELECT c from Report c where c.id = :id")
					.setParameter("id", id).getSingleResult();
		} catch (NoResultException e) {
			LOGGER.info(e);
			return new Report();
		}
		return report;
	}

	/**
	 * 
	 * @param report
	 */
	public void salvarReport(Report report) {
		try {
			entityManager.getTransaction().begin();
			entityManager.merge(report);
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
	public Collection<Report> getAll() {
		Collection<Report> reports = new ArrayList<>();
		try {
			reports = (Collection<Report>) entityManager.createQuery("SELECT c from Report c").getResultList();
		} catch (NoResultException e) {
			LOGGER.info(e);
			return new ArrayList<>();
		}
		return reports;
	}

	/**
	 * 
	 * @param idReportReportado
	 * @return
	 */
	public int getQtdReports(Long idReportReportado) {
		int qtdReports = 0;
		try {
			qtdReports = (int) entityManager
					.createQuery("SELECT COUNT(DISTINCT c.reporter) FROM Report c where c.infectado = :reportInfectado")
					.setParameter("reportInfectado", idReportReportado).getSingleResult();
		} catch (NoResultException e) {
			LOGGER.info(e);
			return -1;
		}
		return qtdReports;
	}
}
