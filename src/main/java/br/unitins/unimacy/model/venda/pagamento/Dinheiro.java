package br.unitins.unimacy.model.venda.pagamento;

import jakarta.persistence.Entity;

import java.io.Serial;
import java.math.BigDecimal;


@Entity
public class Dinheiro extends Pagamento {

    @Serial
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

    public BigDecimal getValorTroco() {
        return valorTroco;
    }

    public void setValorTroco(BigDecimal valorTroco) {
        this.valorTroco = valorTroco;
    }

}
