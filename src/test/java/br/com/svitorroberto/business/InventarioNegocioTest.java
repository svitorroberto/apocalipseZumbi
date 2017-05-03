package br.com.svitorroberto.business;

import static org.mockito.Matchers.any;
import static org.powermock.api.mockito.PowerMockito.when;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.modules.junit4.PowerMockRunner;

import br.com.svitorroberto.dao.ItemDao;
import br.com.svitorroberto.dao.ReportDao;
import br.com.svitorroberto.dao.UsuarioDao;
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
	
	@InjectMocks
	InventarioNegocio negocio;

	/**
	 * @throws Exception
	 */
	//@Test
	public void deveriaAdicinarItemNoInventario() throws Exception {
		when(usuarioDao.salvarUsuario(any(Usuario.class))).thenReturn("Salvo com sucesso");

		Assert.assertEquals("Salvo com sucesso", negocio.adicinarItemNoInventario(itemMock(), usuarioMock()));
	}
	
	//Mocks
	private Item itemMock(){
		return new Item(1L);
	}
private Usuario usuarioMock(){
		return new Usuario("Teste", 15, 'M', "-16.6930378,-49.2476555");
	}

}
