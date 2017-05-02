package br.com.svitorroberto.business;

import br.com.svitorroberto.modelo.Usuario;

public class Teste {
	
	public static void main(String[] args) {
		
		UsuarioNegocio negocio = new UsuarioNegocio();
		System.out.println(negocio.salvar(usuarioMock()));
		
	}

	// MOCKS
		private static Usuario usuarioMock() {
			return new Usuario("Teste", 15, 'M', "-16.6930378,-49.2476555");
		}
}
