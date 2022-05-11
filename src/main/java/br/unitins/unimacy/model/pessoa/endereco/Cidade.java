package br.unitins.unimacy.model.pessoa.endereco;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import br.unitins.unimacy.model.DefaultEntity;

@Entity
public class Cidade extends DefaultEntity {

	private static final long serialVersionUID = -7088102744416075469L;

	private String nome;
	
	@ManyToOne(cascade = CascadeType.MERGE)
	private Estado estado;

	public Cidade() {
		// TODO Auto-generated constructor stub
	}

	public Cidade(String nome) {
		super();
		this.nome = nome;
	}
	
	public Cidade(String nome, Estado estado) {
		super();
		this.nome = nome;
		this.estado = estado;
	}

	public Cidade(Estado estado) {
		super();
		this.estado = estado;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

}