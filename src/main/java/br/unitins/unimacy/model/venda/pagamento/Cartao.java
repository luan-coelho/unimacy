package br.unitins.unimacy.model.venda.pagamento;

import javax.persistence.Entity;

@Entity
public class Cartao extends Pagamento {

	private static final long serialVersionUID = 1L;

	private String numero;

	public Cartao() {

	}

	public Cartao(String numero) {
		this.numero = numero;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	@Override
	public String toString() {
		return "Cartao [numero=" + numero + "]";
	}

}
