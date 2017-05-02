package br.com.svitorroberto.modelo;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2017-05-02T18:47:46.837-0300")
@StaticMetamodel(Inventario.class)
public class Inventario_ {
	public static volatile SingularAttribute<Inventario, Long> id;
	public static volatile SingularAttribute<Inventario, Usuario> usuario;
	public static volatile SingularAttribute<Inventario, Item> item;
}
