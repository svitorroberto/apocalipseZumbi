package br.com.svitorroberto.rest;

import java.util.ArrayList;

import javax.ws.rs.Consumes;

/**
 * @author svitorroberto
 */
 
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import br.com.svitorroberto.business.UsuarioNegocio;
import br.com.svitorroberto.modelo.Usuario;

@Path("/usuarios")
public class UsuarioRest {

	@GET
	@Path("/get")
	@Produces("application/json")
	public ArrayList<Usuario> listarUsuarios() {
		UsuarioNegocio usuarioNegocio = new UsuarioNegocio();
		return (ArrayList<Usuario>) usuarioNegocio.recuperarTodos();
	}
	
	@GET
	@Path("/get/{id}")
	@Produces("application/json")
	public Usuario recuperarPorId(@PathParam("id")Long id) {
		UsuarioNegocio usuarioNegocio = new UsuarioNegocio();
		return usuarioNegocio.recuperarPorId(new Usuario(id));
		
	}
	
	@POST
	@Path("/cadastrar")
	@Consumes("application/json")
	public Response cadastrarUsuario(Usuario usuario) {
 
		UsuarioNegocio usuarioNegocio = new UsuarioNegocio();
		usuarioNegocio.salvar(usuario);
		String result = "Product created : " + usuario;
		return Response.status(201).entity(result).build();
	}
	
	@POST
	@Path("/cadastrar")
	@Consumes("application/json")
	public Response atualizarUltimaLocalizacaoUsuario(Usuario usuario) {
 
		UsuarioNegocio usuarioNegocio = new UsuarioNegocio();
		String result = usuarioNegocio.atualizarLocalizacao(usuario);
		return Response.status(201).entity(result).build();
	}
	
	@POST
	@Path("/reportar/{id}")
	@Consumes("application/json")
	public Response reportarUsuarioContaminado(@PathParam("id")Long id) {
 
		UsuarioNegocio usuarioNegocio = new UsuarioNegocio();
		String result = usuarioNegocio.reportarUsuario(id);
		return Response.status(201).entity(result).build();
	}
	
}
