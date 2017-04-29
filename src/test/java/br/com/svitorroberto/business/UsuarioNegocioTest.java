package br.com.svitorroberto.business;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import br.com.svitorroberto.dao.UsuarioDao;
import br.com.svitorroberto.modelo.Usuario;

public class UsuarioNegocioTest {
	
	@Mock
	UsuarioDao usuarioDao;
	
	 	@Test
	    public void deveriaAtualizarLocalizacaoTest() {
		 Mockito.when(usuarioDao.atualizarLocalizacao(Mockito.any(Usuario.class))).thenReturn(usuarioMock());
		 UsuarioNegocio negocio = new UsuarioNegocio();
		 Usuario usuario = usuarioMock();
		 String result = negocio.atualizarLocalizacao(usuario);
		 
		 Assert.assertEquals(result, "Localizacao atualizada");
	    }
	 
	 	@Test
	    public void naoDeveriaAtualizarLocalizacaoTest() {
		 Mockito.when(usuarioDao.atualizarLocalizacao(Mockito.any(Usuario.class))).thenReturn(usuarioMock2());
		 UsuarioNegocio negocio = new UsuarioNegocio();
		 Usuario usuario = usuarioMock();
		 String result = negocio.atualizarLocalizacao(usuario);
		 
		 Assert.assertEquals(result, "Usuario esta infectado. Operacao invalida");
	    }
	 	
	 	@Test
	    public void deveriaReportarUsuarioTest() {
	 	 Mockito.when(usuarioDao.getUsuarioById(Mockito.any(Long.class))).thenReturn(usuarioMock());
		 UsuarioNegocio negocio = new UsuarioNegocio();
		 String result = negocio.reportarUsuario(1L);
		 
		 Assert.assertEquals(result, "Usuario reportado ");
	    }
	 	
	 	@Test
	 	public void deveriaReportarEInativarUsuarioTest() {
	 		Mockito.when(usuarioDao.getUsuarioById(Mockito.any(Long.class))).thenReturn(usuarioMock2());
	 		UsuarioNegocio negocio = new UsuarioNegocio();
	 		String result = negocio.reportarUsuario(1L);
	 		
	 		Assert.assertEquals(result, "Usuario reportado e inativado");
	 	}
	 	
	 	
	 	
	 	
	 	
	 	
	 	//MOCKS
	 	private Usuario usuarioMock(){
		 return new Usuario(1L, "Vitor", 21, 'M', "-16.6930378,-49.2476555", 'N', 0);
	 }
	 private Usuario usuarioMock2(){
		 return new Usuario(1L, "Pablo", 24, 'F', "-35.8444444,-55.5555555", 'S', 2);
	 }

}
