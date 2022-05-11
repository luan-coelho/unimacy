package br.unitins.unimacy.model.pessoa;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

import br.unitins.unimacy.model.DefaultEntity;

@Entity
public class Cliente extends DefaultEntity{

	private static final long serialVersionUID = -7687261267452454591L;
	
	@OneToOne(cascade = CascadeType.ALL)
	private Pessoa pessoa;

	public Cliente() {
		// TODO Auto-generated constructor stub
	}

	public Cliente(Pessoa pessoa) {
		super();
		this.pessoa = pessoa;
	}
	
	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	@Override
	public String toString() {
		return "Cliente [pessoa=" + pessoa.toString() + "]";
	}
}