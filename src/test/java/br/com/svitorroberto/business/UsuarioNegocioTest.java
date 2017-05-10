package br.com.svitorroberto.business;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyLong;
import static org.powermock.api.mockito.PowerMockito.when;
import static org.powermock.api.mockito.PowerMockito.doCallRealMethod;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
	public void deveriaAtualizarLocalizacao() throws Exception {
		when(usuarioDao.getUsuarioById(anyLong())).thenReturn(usuarioMock());
		when(usuarioDao.salvarUsuario(any(Usuario.class))).thenReturn("Salvo com sucesso");

		Assert.assertEquals("Localizacao atualizada", negocio.atualizarLocalizacao(usuarioMock2()));
	}

	/**
	 * @throws Exception
	 */
	@Test
	public void naoDeveriaAtualizarLocalizacao() throws Exception {
		when(usuarioDao.getUsuarioById(anyLong())).thenReturn(new Usuario(1L, 'S'));
		when(usuarioDao.salvarUsuario(any(Usuario.class))).thenReturn("Salvo com sucesso");

		Assert.assertEquals("Usuario esta infectado. Operacao invalida", negocio.atualizarLocalizacao(new Usuario(1L)));
	}

	/**
	 * @throws Exception
	 */
	@Test
	public void naoDeveriaAtualizarLocalizacao2() throws Exception {
		when(usuarioDao.getUsuarioById(anyLong())).thenReturn(null);

		Assert.assertEquals("Usuario inexistente", negocio.atualizarLocalizacao(usuarioMock2()));
	}

	/**
	 * 
	 * Teste Unit�rio do m�todo ReportarUsuario
	 */
	@Test
	public void deveriaReportarUsuario() {
		when(usuarioDao.getUsuarioById(anyLong())).thenReturn(usuarioMock());
		when(reportDao.salvarReport(any(Report.class))).thenReturn("Usuario reportado");

		Assert.assertEquals("Usuario reportado", negocio.reportarUsuario(1L, 1L));
	}
	/**
	 * 
	 * Teste Unit�rio do m�todo ReportarUsuario
	 */
	@Test
	public void deveriaReportarUsuario2() {
		when(usuarioDao.getUsuarioById(anyLong())).thenReturn(usuarioMock());
		when(reportDao.salvarReport(any(Report.class))).thenReturn("Usuario reportado");
		when(reportDao.getQtdReports(any())).thenReturn(3);
		
		Assert.assertEquals("Usuario reportado e inativado", negocio.reportarUsuario(1L, 1L));
	}

	/**
	 * 
	 * Teste Unit�rio do m�todo ReportarUsuario
	 */
	@Test
	public void naoDeveriaReportarUsuario() {
		when(usuarioDao.getUsuarioById(anyLong())).thenReturn(null);

		Assert.assertEquals("Usuario inexistente", negocio.reportarUsuario(1L, 1L));
	}

	@Test
	public void deveriaGetUsuariosInfectados() {
		when(usuarioDao.getAll()).thenReturn(Arrays.asList(usuarioMock(), usuarioMock()));
		when(usuarioDao.getUsuariosInfectados()).thenReturn(Arrays.asList(usuarioMock()));

		Assert.assertEquals("50.0%", negocio.getUsuariosInfectados());
	}

	@Test
	public void deveriaGetUsuariosNaoInfectados() {
		when(usuarioDao.getAll()).thenReturn(Arrays.asList(usuarioMock(), usuarioMock(), usuarioMock(), usuarioMock()));
		when(usuarioDao.getUsuariosInfectados()).thenReturn(Arrays.asList(usuarioMock()));

		Assert.assertEquals("75.0%", negocio.getUsuariosNaoInfectados());
	}

	@Test
	public void deveriaMediaItemPorUsuario() {
		List<Double> quantidades = new ArrayList<>();
		quantidades.add(8.0);
		quantidades.add(2.0);
		quantidades.add(4.0);
		quantidades.add(8.0);
		when(usuarioDao.mediaItemPorUsuario()).thenReturn(quantidades);
		when(usuarioDao.getQtdUsuarioAtivos()).thenReturn(16.0);

		Assert.assertEquals("{Água:0.5, Comida:0.5, Remédio:0.5, Munição:0.5, }", negocio.mediaItemPorUsuario());
	}
	
	@Test
	public void deveriaTestarGetters() {
		when(negocio.getReportDao()).thenCallRealMethod();
		doCallRealMethod().when(negocio).setReportDao(any(ReportDao.class));
		
		when(negocio.getUsuarioDao()).thenCallRealMethod();
		doCallRealMethod().when(negocio).setUsuarioDao(any(UsuarioDao.class));
		
		assertEquals("foo", negocio.getReportDao());
		assertEquals("foo", negocio.getUsuarioDao());
		
	}

	// MOCKS
	private Usuario usuarioMock() {
		return new Usuario("Teste", 15, 'M', "-16.6930378,-49.2476555");
	}

	private Usuario usuarioMock2() {
		return new Usuario("Pablo", 24, 'F', "-35.8444444,-55.5555555");
	}

}
