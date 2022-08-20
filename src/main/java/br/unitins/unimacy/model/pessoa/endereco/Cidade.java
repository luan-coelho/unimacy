package br.unitins.unimacy.model.pessoa.endereco;


import br.unitins.unimacy.model.DefaultEntity;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;

import java.io.Serial;

@Entity
public class Cidade extends DefaultEntity {

	@Serial
	private static final long serialVersionUID = 1L;

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