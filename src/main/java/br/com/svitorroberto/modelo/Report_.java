package br.com.svitorroberto.modelo;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2017-05-02T00:18:57.716-0300")
@StaticMetamodel(Report.class)
public class Report_ {
	public static volatile SingularAttribute<Report, Long> id;
	public static volatile SingularAttribute<Report, Usuario> infectado;
	public static volatile SingularAttribute<Report, Usuario> reporter;
}
