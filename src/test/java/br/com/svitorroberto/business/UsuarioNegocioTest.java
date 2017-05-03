package br.com.svitorroberto.business;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyLong;
import static org.powermock.api.mockito.PowerMockito.when;

import java.util.Arrays;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.modules.junit4.PowerMockRunner;

import br.com.svitorroberto.dao.ReportDao;
import br.com.svitorroberto.dao.UsuarioDao;
import br.com.svitorroberto.modelo.Report;
import br.com.svitorroberto.modelo.Usuario;

/**
 * @author V�tor Roberto
 *
 */
@RunWith(PowerMockRunner.class)
public class UsuarioNegocioTest {

	@Mock
	UsuarioDao usuarioDao = PowerMockito.mock(UsuarioDao.class);
	
	@Mock
	ReportDao reportDao = PowerMockito.mock(ReportDao.class);
	
	@InjectMocks
	UsuarioNegocio negocio;

	/**
	 * @throws Exception
	 */
	@Test
	public void deveriaSalvar() throws Exception {
		when(usuarioDao.salvarUsuario(any(Usuario.class))).thenReturn("Salvo com sucesso");

		Assert.assertEquals("Salvo com sucesso", negocio.salvar(usuarioMock()));
	}

	/**
	 * @throws Exception
	 */
	@Test
	public void deveriaAtualizarLocalizacaoTest() throws Exception {
		when(usuarioDao.getUsuarioById(anyLong())).thenReturn(usuarioMock());
		when(usuarioDao.salvarUsuario(any(Usuario.class))).thenReturn("Salvo com sucesso");

		Assert.assertEquals("Localizacao atualizada", negocio.atualizarLocalizacao(usuarioMock2()));
	}
	

	/**
	 * @throws Exception
	 */
	@Test
	public void naoDeveriaAtualizarLocalizacaoTest() throws Exception {
		when(usuarioDao.getUsuarioById(anyLong())).thenReturn(null);

		Assert.assertEquals("Usuario inexistente", negocio.atualizarLocalizacao(usuarioMock2()));
	}

	/**
	 * 
	 * Teste Unit�rio do m�todo ReportarUsuario
	 */
	@Test
	public void deveriaReportarUsuarioTest() {
		when(usuarioDao.getUsuarioById(anyLong())).thenReturn(usuarioMock());
		when(reportDao.salvarReport(any(Report.class))).thenReturn("Usuario reportado");
		
		Assert.assertEquals("Usuario reportado", negocio.reportarUsuario(1L, 1L));
	}
	
	/**
	 * 
	 * Teste Unit�rio do m�todo ReportarUsuario
	 */
	@Test
	public void naoDeveriaReportarUsuarioTest() {
		when(usuarioDao.getUsuarioById(anyLong())).thenReturn(null);
		
		Assert.assertEquals("Usuario inexistente", negocio.reportarUsuario(1L, 1L));
	}
	
	//@Test
	public void deveriaGetUsuariosInfectados(){
		when(usuarioDao.getAll()).thenReturn(Arrays.asList(usuarioMock(),usuarioMock()));
		when(usuarioDao.getUsuariosInfectados()).thenReturn(Arrays.asList(usuarioMock()));
		
		Assert.assertEquals("50%", negocio.getUsuariosInfectados());
	}
	
	//@Test
	public void deveriaGetUsuariosNaoInfectados(){
		when(usuarioDao.getAll()).thenReturn(Arrays.asList(usuarioMock(),usuarioMock(),usuarioMock(),usuarioMock()));
		when(usuarioDao.getUsuariosInfectados()).thenReturn(Arrays.asList(usuarioMock()));
		
		Assert.assertEquals("25%", negocio.getUsuariosInfectados());
	}
		
	// MOCKS
	private Usuario usuarioMock() {
		return new Usuario("Teste", 15, 'M', "-16.6930378,-49.2476555");
	}

	private Usuario usuarioMock2() {
		return new Usuario("Pablo", 24, 'F', "-35.8444444,-55.5555555");
	}

}
