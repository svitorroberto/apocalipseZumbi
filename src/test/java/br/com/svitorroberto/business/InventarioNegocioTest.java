package br.com.svitorroberto.business;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.powermock.api.mockito.PowerMockito.when;

import java.util.ArrayList;
import java.util.Collection;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.modules.junit4.PowerMockRunner;

import br.com.svitorroberto.dao.InventarioDao;
import br.com.svitorroberto.dao.ItemDao;
import br.com.svitorroberto.dao.ReportDao;
import br.com.svitorroberto.dao.UsuarioDao;
import br.com.svitorroberto.modelo.Escambo;
import br.com.svitorroberto.modelo.Inventario;
import br.com.svitorroberto.modelo.Item;
import br.com.svitorroberto.modelo.Usuario;

/**
 * @author V�tor Roberto
 *
 */
@RunWith(PowerMockRunner.class)
public class InventarioNegocioTest {

	@Mock
	UsuarioDao usuarioDao = PowerMockito.mock(UsuarioDao.class);

	@Mock
	ReportDao reportDao = PowerMockito.mock(ReportDao.class);

	@Mock
	ItemDao itemDao = PowerMockito.mock(ItemDao.class);

	@Mock
	InventarioDao inventarioDao = PowerMockito.mock(InventarioDao.class);

	@InjectMocks
	InventarioNegocio negocio;

	/**
	 * @throws Exception
	 */
	@Test
	public void deveriaAdicinarItemNoInventario() throws Exception {
		when(usuarioDao.salvarUsuario(any(Usuario.class))).thenReturn("Salvo com sucesso");
		when(itemDao.getItemById(any())).thenReturn(itemMock());
		when(usuarioDao.getUsuarioById(any())).thenReturn(usuarioMock());
		when(inventarioDao.buscarNoInventario(any())).thenReturn(collectionMock());

		Assert.assertEquals("Item incluido com sucesso", negocio.adicinarItemNoInventario(itemMock(), usuarioMock()));
	}

	/**
	 * @throws Exception
	 */
	@Test
	public void naoDeveriaAdicinarItemNoInventario() throws Exception {
		when(usuarioDao.salvarUsuario(any(Usuario.class))).thenReturn("Salvo com sucesso");
		when(itemDao.getItemById(any())).thenReturn(itemMock());
		when(usuarioDao.getUsuarioById(any())).thenReturn(new Usuario());
		when(inventarioDao.buscarNoInventario(any())).thenReturn(collectionMock());

		Assert.assertEquals("Ação inválida", negocio.adicinarItemNoInventario(itemMock(), usuarioMock()));
	}

	/**
	 * @throws Exception
	 */
	@Test
	public void deveriaRemoverItemNoInventario() throws Exception {
		when(itemDao.getItemById(any())).thenReturn(itemMock());
		when(usuarioDao.getUsuarioById(any())).thenReturn(usuarioMock());
		when(inventarioDao.buscarNoInventario(any())).thenReturn(collectionMock());

		negocio.removerItemNoInventario(itemMock(), usuarioMock());

		verify(inventarioDao).removerDoInventario(any());
		Assert.assertEquals("Item removido com sucesso", negocio.removerItemNoInventario(itemMock(), usuarioMock()));
	}

	/**
	 * @throws Exception
	 */
	@Test
	public void naoDeveriaRemoverItemNoInventario() throws Exception {
		when(itemDao.getItemById(any())).thenReturn(itemMock());
		when(usuarioDao.getUsuarioById(any())).thenReturn(new Usuario());
		when(inventarioDao.buscarNoInventario(any())).thenReturn(collectionMock());

		negocio.removerItemNoInventario(itemMock(), usuarioMock());

		Assert.assertEquals("Ação inválida", negocio.removerItemNoInventario(itemMock(), usuarioMock()));
	}

	/**
	 * @throws Exception
	 */
	@Test
	public void naoDeveriaRemoverItemNoInventario2() throws Exception {
		when(itemDao.getItemById(any())).thenReturn(itemMock());
		when(usuarioDao.getUsuarioById(any())).thenReturn(usuarioMock());
		when(inventarioDao.buscarNoInventario(any())).thenReturn(new ArrayList<>());

		negocio.removerItemNoInventario(itemMock(), usuarioMock());

		Assert.assertEquals("Item não disponível no inventário",
				negocio.removerItemNoInventario(itemMock(), usuarioMock()));
	}

	/**
	 * @throws Exception
	 */
	@Test
	public void deveriaRecuperarPorId() throws Exception {
		negocio.recuperarPorId(inventarioMock());

		verify(inventarioDao).getInventarioById(any());
	}

	/**
	 * @throws Exception
	 */
	@Test
	public void deveriaRecuperarTodos() throws Exception {
		negocio.recuperarTodos();

		verify(inventarioDao).getAll();
	}

	/**
	 * @throws Exception
	 */
	@Test
	public void deveriaRealizarEscambo() throws Exception {
		when(itemDao.getItemById(any())).thenReturn(itemMock());
		when(usuarioDao.getUsuarioById(any())).thenReturn(usuarioMock());
		when(inventarioDao.buscarNoInventario(any())).thenReturn(collectionMock());

		Assert.assertEquals("Escambo realizado com sucesso", negocio.realizarEscambo(escamboMock(), escamboMock()));
	}

	// Mocks
	private Item itemMock() {
		return new Item(1L);
	}

	private Usuario usuarioMock() {
		return new Usuario(1L, "Teste", 15, 'M', "-16.6930378,-49.2476555");
	}

	private Inventario inventarioMock() {
		return new Inventario(itemMock(), usuarioMock());
	}

	private Collection<Inventario> collectionMock() {
		Collection<Inventario> inventarios = new ArrayList<>();
		inventarios.add(inventarioMock());
		return inventarios;
	}

	private Escambo escamboMock() {
		return new Escambo(usuarioMock(), itensMock());
	}

	private Collection<Item> itensMock() {
		Collection<Item> inventarios = new ArrayList<>();
		inventarios.add(itemMock());
		return inventarios;
	}

}
