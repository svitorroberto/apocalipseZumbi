package br.com.svitorroberto.business;

import java.util.ArrayList;
import java.util.Collection;

import br.com.svitorroberto.dao.ReportDao;
import br.com.svitorroberto.dao.UsuarioDao;
import br.com.svitorroberto.modelo.Report;
import br.com.svitorroberto.modelo.Usuario;

/**
 * @author Vítor Roberto
 *
 */
public class UsuarioNegocio {
	UsuarioDao usuarioDao;
	ReportDao reportDao;

	/**
	 * 
	 * @param usuario
	 * @return
	 */
	public String salvar(Usuario usuario) {
		return usuarioDao.salvarUsuario(usuario);
	}

	/**
	 * 
	 * @param usuario
	 * @return
	 */
	public Usuario recuperarPorId(Usuario usuario) {
		return usuarioDao.getUsuarioById(usuario.getId());
	}

	/**
	 * 
	 * @return
	 */
	public Collection<Usuario> recuperarTodos() {
		return usuarioDao.getAll();
	}

	/**
	 * 
	 * @param usuario
	 * @return
	 */
	public String atualizarLocalizacao(Usuario usuario) {
		Usuario usuario2 = recuperarPorId(usuario);

		if (usuario2 == null) {
			return "Usuario inexistente";
		} else {
			if (usuario2.getIsInfectado() == 'S') {
				return "Usuario esta infectado. Operacao invalida";
			} else {
				usuario2.setUltimaLocalizacao(usuario.getUltimaLocalizacao());
				UsuarioDao usuarioDao = new UsuarioDao();
				usuarioDao.atualizarLocalizacao(usuario2);
				return "Localizacao atualizada";
			}
		}
	}

	/**
	 * 
	 * @param id
	 * @param idReporter
	 * @return
	 */
	public String reportarUsuario(Long id, Long idReporter) {
		Usuario usuarioInfectado = recuperarPorId(new Usuario(id));
		Usuario usuarioReporter = recuperarPorId(new Usuario(idReporter));
		String msg = "Usuario reportado";

		if (usuarioReporter == null || usuarioInfectado == null) {
			return "Usuario inexistente";
		} else {
			reportDao.salvarReport(new Report(usuarioInfectado, usuarioReporter));

			if (reportDao.getQtdReports(usuarioInfectado.getId()) > 2) {
				usuarioInfectado.setIsInfectado('S');
				msg += " e inativado";
			}
		}
		usuarioDao.atualizarUsuario(usuarioInfectado);
		return msg;
	}

	public String getUsuariosInfectados() {
		return getRelatorio(Boolean.TRUE);
	}

	public String getUsuariosNaoInfectados() {
		return getRelatorio(Boolean.FALSE);
	}

	private String getRelatorio(Boolean infectado) {
		Double total = Double.valueOf(usuarioDao.getAll().size());
		Double infectados = Double.valueOf(usuarioDao.getUsuariosInfectados().size());
		if (infectado) {
			return (infectados / total) * 100 + "%";
		} else {
			return ((total - infectados) / total) * 100 + "%";
		}
	}

	public String mediaItemPorUsuario() {
		ArrayList<Double> quantidades = usuarioDao.mediaItemPorUsuario();
		Double qtdUsuarios = usuarioDao.getQtdUsuarioAtivos();

		StringBuilder sb = new StringBuilder();
		sb.append("{");
		sb.append("Água:");
		sb.append(quantidades.get(0) / qtdUsuarios);
		sb.append(", ");
		sb.append("Comida:");
		sb.append(quantidades.get(1) / qtdUsuarios);
		sb.append(", ");
		sb.append("Remédio:");
		sb.append(quantidades.get(2) / qtdUsuarios);
		sb.append(", ");
		sb.append("Munição:");
		sb.append(quantidades.get(3) / qtdUsuarios);
		sb.append("}");

		return sb.toString();
	}

	public UsuarioDao getUsuarioDao() {
		return usuarioDao;
	}

	public void setUsuarioDao(UsuarioDao usuarioDao) {
		this.usuarioDao = usuarioDao;
	}

	public ReportDao getReportDao() {
		return reportDao;
	}

	public void setReportDao(ReportDao reportDao) {
		this.reportDao = reportDao;
	}

}
