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

import br.com.svitorroberto.business.InventarioNegocio;
import br.com.svitorroberto.modelo.Inventario;

@Path("/invetntarios")
public class InventarioRest {

	@GET
	@Path("/get")
	@Produces("application/json")
	public ArrayList<Inventario> listarInventarios() {
		InventarioNegocio inventarioNegocio = new InventarioNegocio();
		return (ArrayList<Inventario>) inventarioNegocio.recuperarTodos();
	}
	
	@GET
	@Path("/get/{id}")
	@Produces("application/json")
	public Inventario recuperarPorId(@PathParam("id")Long id) {
		InventarioNegocio inventarioNegocio = new InventarioNegocio();
		return inventarioNegocio.recuperarPorId(new Inventario(id));
	}
	
	@POST
	@Path("/cadastrar")
	@Consumes("application/json")
	public Response cadastrarInventario(Inventario inventario) {
 
		InventarioNegocio inventarioNegocio = new InventarioNegocio();
		inventarioNegocio.salvar(inventario);
		String result = "Product created : " + inventario;
		return Response.status(201).entity(result).build();
	}
	
}
