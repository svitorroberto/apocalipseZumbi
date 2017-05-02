package br.com.svitorroberto.business;

import javax.persistence.EntityManager;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.modules.junit4.PowerMockRunner;

import br.com.svitorroberto.dao.UsuarioDao;
import br.com.svitorroberto.modelo.Usuario;

@RunWith(PowerMockRunner.class)
public class UsuarioNegocioTest {

	UsuarioNegocio un = Mockito.spy(new UsuarioNegocio());

	@Mock
	UsuarioDao usuarioDao = Mockito.mock(UsuarioDao.class);

	EntityManager entityManager = Mockito.mock(EntityManager.class);

	@Test
	public void deveriaSalvar() throws Exception{
		Mockito.when(usuarioDao.salvarUsuario(Mockito.any(Usuario.class))).thenReturn("OK");
		PowerMockito.whenNew(UsuarioDao.class).withNoArguments().thenReturn(usuarioDao);
		UsuarioNegocio negocio = new UsuarioNegocio();
		
		Assert.assertEquals("OK", negocio.salvar(usuarioMock()));
	}
	
	// @Test
	public void deveriaAtualizarLocalizacaoTest() throws Exception {
		Mockito.when(usuarioDao.atualizarLocalizacao(Mockito.any(Usuario.class))).thenReturn(usuarioMock());
		Mockito.when(usuarioDao.getUsuarioById(Mockito.anyLong())).thenReturn(usuarioMock());
		PowerMockito.whenNew(UsuarioDao.class).withNoArguments().thenReturn(usuarioDao);
		UsuarioNegocio negocio = new UsuarioNegocio();
		Usuario usuario = usuarioMock();
		String result = negocio.atualizarLocalizacao(usuario);

		Assert.assertEquals(result, "Localizacao atualizada");
	}

	//@Test
	public void naoDeveriaAtualizarLocalizacaoTest() throws Exception {
		PowerMockito.when(usuarioDao.atualizarLocalizacao(Mockito.any(Usuario.class))).thenReturn(usuarioMock2());
		PowerMockito.when(usuarioDao.getUsuarioById(Mockito.anyLong())).thenReturn(usuarioMock2());
		PowerMockito.whenNew(UsuarioDao.class).withNoArguments().thenReturn(usuarioDao);

		UsuarioNegocio negocio = new UsuarioNegocio();
		Usuario usuario = usuarioMock();
		String result = negocio.atualizarLocalizacao(usuario);

		Assert.assertEquals(result, "Usuario esta infectado. Operacao invalida");
	}

	// @Test
	public void deveriaReportarUsuarioTest() {
		Mockito.when(usuarioDao.getUsuarioById(Mockito.any(Long.class))).thenReturn(usuarioMock());
		UsuarioNegocio negocio = new UsuarioNegocio();
		String result = negocio.reportarUsuario(1L,1L);

		Assert.assertEquals(result, "Usuario reportado ");
	}

	// @Test
	public void deveriaReportarEInativarUsuarioTest() {
		Mockito.when(usuarioDao.getUsuarioById(Mockito.any(Long.class))).thenReturn(usuarioMock2());
		UsuarioNegocio negocio = new UsuarioNegocio();
		String result = negocio.reportarUsuario(1L, 1L);

		Assert.assertEquals(result, "Usuario reportado e inativado");
	}

	// MOCKS
	private Usuario usuarioMock() {
		return new Usuario("Teste", 15, 'M', "-16.6930378,-49.2476555");
	}

	private Usuario usuarioMock2() {
		return new Usuario("Pablo", 24, 'F', "-35.8444444,-55.5555555");
	}

}
