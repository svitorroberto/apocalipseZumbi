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
	
}
