package br.com.svitorroberto.rest;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;

import org.jboss.resteasy.mock.MockHttpRequest;
import org.jboss.resteasy.mock.MockHttpResponse;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import br.com.svitorroberto.business.UsuarioNegocio;
import br.com.svitorroberto.modelo.Usuario;

/**
 * @author V�tor Roberto
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class UsuarioRestTest {

	private UsuarioRest resource;
	@Mock
	private UsuarioNegocio repository;

	@Before
	public void setupResource() {
		MockitoAnnotations.initMocks(getClass());
		resource = new UsuarioRest();
		resource.setUsuarioNegocio(repository);
		//dispatcher.getRegistry().addSingletonResource(resource);
	}

	@After
	public void removeResource() {
		//dispatcher.getRegistry().removeRegistrations(UsuarioRest.class);
	}

	/**
	 * 
	 * @throws Exception
	 */
	@Test
	public void shouldSaveUsuario() throws Exception {
		MockHttpRequest request = MockHttpRequest.post("/usuarios/cadastrar");
		request.addFormHeader("nome", "Maarten");
		request.addFormHeader("idade", "12");
		request.addFormHeader("sexo", "M");
		request.addFormHeader("ultimaLocalizacao", "-16.6930378,-49.2476555");

		ArgumentCaptor<Usuario> usuarioCaptor = ArgumentCaptor.forClass(Usuario.class);
//		when(repository.salvar(usuarioCaptor.capture())).thenAnswer(new Answer<Usuario>());

		MockHttpResponse response = new MockHttpResponse();
		//dispatcher.invoke(request, response);

		verify(repository).salvar(any(Usuario.class));
		Usuario usuario = usuarioCaptor.getValue();
		assertEquals("Maarten", usuario.getNome());
		assertEquals(12, usuario.getIdade());
		assertEquals("M", usuario.getSexo());
		assertEquals("-16.6930378,-49.2476555", usuario.getUltimaLocalizacao());

		assertEquals(200, response.getStatus());
	}

	private Usuario usuarioMock() {
		return new Usuario("Maarten", 12, 'M', "-16.6930378,-49.2476555");
	}
}
