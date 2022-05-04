package br.unitins.unimacy.model;

import javax.persistence.Entity;

@Entity
public class Unidade extends DefaultEntity {

	private static final long serialVersionUID = 8191059953577957199L;

	private Integer qtd;
	private UnidadeMedida unidadeMedida;

	public Unidade() {
		// TODO Auto-generated constructor stub
	}

	public Unidade(Integer qtd, UnidadeMedida unidadeMedida) {
		super();
		this.qtd = qtd;
		this.unidadeMedida = unidadeMedida;
	}

	public Integer getQtd() {
		return qtd;
	}

	public void setQtd(Integer qtd) {
		this.qtd = qtd;
	}

	public UnidadeMedida getUnidadeMedida() {
		return unidadeMedida;
	}

	public void setUnidadeMedida(UnidadeMedida unidadeMedida) {
		this.unidadeMedida = unidadeMedida;
	}

}