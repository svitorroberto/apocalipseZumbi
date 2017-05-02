package br.com.svitorroberto.modelo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * @author V�tor Roberto
 *
 */
@Entity
@Table(name = "report")
public class Report {

	@Id
	@GeneratedValue
	@Column(name = "id")
	private Long id;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_usuario_infectado")
	private Usuario infectado;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_usuario_reporter")
	private Usuario reporter;

	public Report() {
		// Construtor vazio
	}

	/**
	 * 
	 * @param id
	 */
	public Report(Long id) {
		this.id = id;
	}

	/**
	 * 
	 * @param infectado
	 * @param reporter
	 */
	public Report(Usuario infectado, Usuario reporter) {
		this.infectado = infectado;
		this.reporter = reporter;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Usuario getInfectado() {
		return infectado;
	}

	public void setInfectado(Usuario infectado) {
		this.infectado = infectado;
	}

	public Usuario getReporter() {
		return reporter;
	}

	public void setReporter(Usuario reporter) {
		this.reporter = reporter;
	}

}
