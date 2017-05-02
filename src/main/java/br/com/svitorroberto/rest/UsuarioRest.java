package br.com.svitorroberto.rest;

import java.util.ArrayList;
import java.util.Collection;

import javax.ws.rs.Consumes;

/**
 * @author svitorroberto
 */
 
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.com.svitorroberto.business.UsuarioNegocio;
import br.com.svitorroberto.dao.UsuarioDao;
import br.com.svitorroberto.modelo.Usuario;

/**
 * @author V�tor Roberto
 *
 */
@Path("/usuarios")
public class UsuarioRest {
	private UsuarioNegocio usuarioNegocio;
	UsuarioDao usuarioDao;

	/**
	 * 
	 * @return
	 */
	@GET
	@Path("/get")
	@Produces("application/json")
	public Collection<Usuario> listarUsuarios() {
		return (ArrayList<Usuario>) usuarioNegocio.recuperarTodos();
	}
	
	/**
	 * @param nome
	 * @param sexo
	 * @param idade
	 * @param loc
	 * @return
	 */
	@GET
	@Path("/cadastrar/{nome}/{sexo}/{idade}/{loc}")
	@Consumes("application/json")
	@Produces(MediaType.APPLICATION_JSON)
	public Response cadastrarUsuario(@PathParam("nome")String nome, @PathParam("sexo")String sexo, @PathParam("idade")int idade, @PathParam("loc")String loc) {
		String result = usuarioNegocio.salvar(new Usuario(nome, idade, sexo.charAt(0), loc));
		return result == "Salvo com sucesso" ? Response.status(201).entity(result).build() : Response.status(417).entity(result).build();
	}

	/**
	 * @param id
	 * @param loc
	 * @return
	 */
	@GET
	@Path("/atualizarLocalizacao/{id}/{loc}")
	@Consumes("application/json")
	public Response atualizarUltimaLocalizacaoUsuario(@PathParam("id")Long id, @PathParam("loc")String loc) {
		String result = usuarioNegocio.atualizarLocalizacao(new Usuario(id, loc));
		return result == "Localizacao atualizada" ? Response.status(200).entity(result).build() : Response.status(417).entity(result).build();
	}
	
	/**
	 * @param id
	 * @param idReporter
	 * @return
	 */
	@GET
	@Path("/reportar/{id}/{idReporter}")
	@Produces("application/json")
	public Response reportarUsuarioContaminado(@PathParam("id")Long id, @PathParam("idReporter")Long idReporter) {
		String result = usuarioNegocio.reportarUsuario(id,idReporter);
		return result == "Localizacao atualizada" ? Response.status(200).entity(result).build() : Response.status(417).entity(result).build();
	}
	
	/**
	 * @return
	 * Relat�rio 1
	 */
	@GET
	@Path("/infectados")
	@Produces("application/json")
	public Response relatorioUsuariosInfectados() {
		String result = usuarioNegocio.getUsuariosInfectados();
		return Response.status(200).entity(result).build();
	}
	
	/**
	 * @return
	 * Relat�rio 2
	 */
	@GET
	@Path("/naoInfectados")
	@Produces("application/json")
	public Response relatorioUsuariosNaoInfectados() {
		String result = usuarioNegocio.getUsuariosNaoInfectados();
		return Response.status(201).entity(result).build();
	}
	
	/**
	 * @return
	 * Relat�rio 3
	 */
	@GET
	@Path("/mediaItens")
	@Produces("application/json")
	public Response relatorioItensPorUsuario() {
		String result = usuarioNegocio.mediaItemPorUsuario();
		return Response.status(200).entity(result).build();
	}
	
	/**
	 * @return
	 * 
	 * Relat�rio 4
	 */
	@GET
	@Path("/pontosPerdidos")
	@Produces("application/json")
	public Response relatorioPontosPerdidos() {
		Long result = usuarioDao.getPontosPerdidos();
		return Response.status(200).entity(result).build();
	}

	public UsuarioNegocio getUsuarioNegocio() {
		return usuarioNegocio;
	}

	public void setUsuarioNegocio(UsuarioNegocio usuarioNegocio) {
		this.usuarioNegocio = usuarioNegocio;
	}

	public UsuarioDao getUsuarioDao() {
		return usuarioDao;
	}

	public void setUsuarioDao(UsuarioDao usuarioDao) {
		this.usuarioDao = usuarioDao;
	}
	
}
