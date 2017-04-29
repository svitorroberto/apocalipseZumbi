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

import br.com.svitorroberto.business.ItemNegocio;
import br.com.svitorroberto.modelo.Item;

@Path("/items")
public class ItemRest {

	@GET
	@Path("/get")
	@Produces("application/json")
	public ArrayList<Item> listarItems() {
		ItemNegocio itemNegocio = new ItemNegocio();
		return (ArrayList<Item>) itemNegocio.recuperarTodos();
	}
	
	@GET
	@Path("/get/{id}")
	@Produces("application/json")
	public Item recuperarPorId(@PathParam("id")Long id) {
		ItemNegocio itemNegocio = new ItemNegocio();
		return itemNegocio.recuperarPorId(new Item(id));
	}
	
	@POST
	@Path("/cadastrar")
	@Consumes("application/json")
	public Response cadastrarItem(Item item) {
		ItemNegocio itemNegocio = new ItemNegocio();
		itemNegocio.salvar(item);
		String result = "Product created : " + item;
		return Response.status(201).entity(result).build();
	}
	
}
