package br.unitins.unimacy.model.venda.pagamento;

import javax.persistence.Entity;

@Entity
public class Cartao extends Pagamento {

	private static final long serialVersionUID = 1L;

	private String titular;
	private String numero;

	public Cartao() {

	}

	public Cartao(String titular, String numero) {
		this.titular = titular;
		this.numero = numero;
	}

	public String getTitular() {
		return titular;
	}

	public void setTitular(String titular) {
		this.titular = titular;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	@Override
	public String toString() {
		return "Cartao [titular=" + titular + ", numero=" + numero + "]";
	}
}
