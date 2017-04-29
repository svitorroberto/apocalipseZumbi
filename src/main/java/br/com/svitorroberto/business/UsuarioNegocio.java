package br.com.svitorroberto.business;

import java.util.Collection;

import br.com.svitorroberto.dao.UsuarioDao;
import br.com.svitorroberto.modelo.Usuario;

public class UsuarioNegocio {
	
	public void salvar(Usuario usuario){
		UsuarioDao usuarioDao = new UsuarioDao();
		usuarioDao.salvarUsuario(usuario);
	}
	
	public Usuario recuperarPorId(Usuario usuario){
		UsuarioDao usuarioDao = new UsuarioDao();
		return usuarioDao.getUsuarioById(usuario.getId());
	}

	public Collection<Usuario> recuperarTodos() {
		UsuarioDao usuarioDao = new UsuarioDao();
		return usuarioDao.getAll();
	}

	public String atualizarLocalizacao(Usuario usuario) {
		UsuarioDao usuarioDao = new UsuarioDao();
		Usuario usuario2 = recuperarPorId(usuario);
		
		if(usuario2.getIsInfectado() == 'S'){
			return "Usuario esta infectado. Operacao invalida";
		}else{
			usuario2.setUltimaLocalizacao(usuario.getUltimaLocalizacao());
			usuarioDao.atualizarLocalizacao(usuario2);
			return "Localizacao atualizada";
		}
	}

	public String reportarUsuario(Long id) {
		UsuarioDao usuarioDao = new UsuarioDao();
		Usuario usuario2 = recuperarPorId(new Usuario(id));
		
		String msg = "Usuario reportado ";
		
		usuario2.setQtdReportado(usuario2.getQtdReportado()+1);
		if(usuario2.getQtdReportado()>2){
			usuario2.setIsInfectado('S');
			msg += "e inativado";
		}
		usuarioDao.atualizarUsuario(usuario2);
		
		return msg;
	}
	
}
