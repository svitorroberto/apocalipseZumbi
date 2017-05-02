package br.com.svitorroberto.modelo;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2017-05-01T19:04:11.242-0300")
@StaticMetamodel(Usuario.class)
public class Usuario_ {
	public static volatile SingularAttribute<Usuario, Long> id;
	public static volatile SingularAttribute<Usuario, String> nome;
	public static volatile SingularAttribute<Usuario, Integer> idade;
	public static volatile SingularAttribute<Usuario, Character> sexo;
	public static volatile SingularAttribute<Usuario, String> ultimaLocalizacao;
	public static volatile SingularAttribute<Usuario, Character> isInfectado;
}
