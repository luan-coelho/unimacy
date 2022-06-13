package br.unitins.unimacy.model.venda.pagamento;

import javax.persistence.Entity;

@Entity
public class Pix extends Pagamento {

	private static final long serialVersionUID = 974617149413523553L;

	private String chave;

	public Pix() {
		// TODO Auto-generated constructor stub
	}

	public Pix(String chave) {
		this.chave = chave;
	}

	public String getChave() {
		return chave;
	}

	public void setChave(String chave) {
		this.chave = chave;
	}

	@Override
	public String toString() {
		return "Pix [chave=" + chave + "]";
	}
}
