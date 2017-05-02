package br.com.svitorroberto.rest;

import java.util.ArrayList;
import java.util.Arrays;

import javax.ws.rs.Consumes;

/**
 * @author svitorroberto
 */

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import br.com.svitorroberto.business.InventarioNegocio;
import br.com.svitorroberto.modelo.Escambo;
import br.com.svitorroberto.modelo.Inventario;
import br.com.svitorroberto.modelo.Item;
import br.com.svitorroberto.modelo.Usuario;

/**
 * @author Vítor Roberto
 *
 */
@Path("/inventarios")
public class InventarioRest {
	InventarioNegocio inventarioNegocio;

	/**
	 * 
	 * @return
	 */
	@GET
	@Path("/get")
	@Produces("application/json")
	public ArrayList<Inventario> listarInventarios() {
		return (ArrayList<Inventario>) inventarioNegocio.recuperarTodos();
	}

	/**
	 * 
	 * @param idItem
	 * @param idPessoa
	 * @return
	 */
	@GET
	@Path("/adicionar/{idItem}/{idPessoa}")
	@Consumes("application/json")
	public Response cadastrarInventario(@PathParam("idItem") Long idItem, @PathParam("idPessoa") Long idPessoa) {
		String result = inventarioNegocio.adicinarItemNoInventario(new Item(idItem), new Usuario(idPessoa));
		return result == "Item incluido com sucesso" ? Response.status(200).entity(result).build()
				: Response.status(417).entity(result).build();
	}

	/**
	 * 
	 * @param idItem
	 * @param idPessoa
	 * @return
	 */
	@GET
	@Path("/remover/{idItem}/{idPessoa}")
	@Consumes("application/json")
	public Response removerInventario(@PathParam("idItem") Long idItem, @PathParam("idPessoa") Long idPessoa) {
		String result = inventarioNegocio.removerItemNoInventario(new Item(idItem), new Usuario(idPessoa));
		return result == "Item removido com sucesso" ? Response.status(200).entity(result).build()
				: Response.status(417).entity(result).build();
	}

	/**
	 * 
	 * @param idPessoa1
	 * @param idPessoa2
	 * @param itens1
	 * @param itens2
	 * @return
	 */
	@GET
	@Path("/{idPessoa1}/{itens1}/escambo/{idPessoa2}/{itens2}")
	@Consumes("application/json")
	public Response realizarEscambo(@PathParam("idPessoa1") Long idPessoa1, @PathParam("idPessoa2") Long idPessoa2,
			@PathParam("itens1") String itens1, @PathParam("itens2") String itens2) {
		ArrayList<String> itensPessoa1 = new ArrayList<>(Arrays.asList(itens1.split(",")));
		ArrayList<Item> item1 = new ArrayList<>();
		itensPessoa1.forEach(i -> {
			item1.add(new Item(Long.valueOf(i)));
		});
		ArrayList<String> itensPessoa2 = new ArrayList<>(Arrays.asList(itens2.split(",")));
		ArrayList<Item> item2 = new ArrayList<>();
		;
		itensPessoa2.forEach(i -> {
			item2.add(new Item(Long.valueOf(i)));
		});
		String result = inventarioNegocio.realizarEscambo(new Escambo(new Usuario(idPessoa1), item1),
				new Escambo(new Usuario(idPessoa2), item2));
		return result == "Escambo realizado com sucesso" ? Response.status(200).entity(result).build()
				: Response.status(417).entity(result).build();
	}

	public InventarioNegocio getInventarioNegocio() {
		return inventarioNegocio;
	}

	public void setInventarioNegocio(InventarioNegocio inventarioNegocio) {
		this.inventarioNegocio = inventarioNegocio;
	}

}
