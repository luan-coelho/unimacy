package br.unitins.unimacy.model.venda.pagamento;

import java.math.BigDecimal;

import javax.persistence.Entity;

@Entity
public class Dinheiro extends Pagamento {

	private static final long serialVersionUID = 1L;

	private BigDecimal valorPago;
	private BigDecimal valorTroco;

	public Dinheiro() {
		// TODO Auto-generated constructor stub
	}

	public Dinheiro(BigDecimal valorPago) {
		this.valorPago = valorPago;
	}

	public BigDecimal getValorPago() {
		return valorPago;
	}

	public void setValorPago(BigDecimal valorPago) {
		this.valorPago = valorPago;
	}

	@Override
	public String toString() {
		return "Dinheiro [valorPago=" + valorPago + "]";
	}

}
