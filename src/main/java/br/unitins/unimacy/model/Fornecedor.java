package br.unitins.unimacy.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Entity
public class Fornecedor extends DefaultEntity {

	private static final long serialVersionUID = 5302778775042634051L;

	@OneToOne(cascade = CascadeType.ALL)
	private PessoaJuridica pessoaJuridica;

	public Fornecedor() {
		// TODO Auto-generated constructor stub
	}
	
	public Fornecedor(PessoaJuridica pessoaJuridica) {
		super();
		this.pessoaJuridica = pessoaJuridica;
	}

	public PessoaJuridica getPessoaJuridica() {
		return pessoaJuridica;
	}

	public void setPessoaJuridica(PessoaJuridica pessoaJuridica) {
		this.pessoaJuridica = pessoaJuridica;
	}
	
	
	@Override
	public String toString() {
		return getPessoaJuridica().getNomeFantasia();
	}
}