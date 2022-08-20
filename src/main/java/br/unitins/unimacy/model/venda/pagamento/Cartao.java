package br.unitins.unimacy.model.venda.pagamento;


import jakarta.persistence.Entity;

import java.io.Serial;

@Entity
public class Cartao extends Pagamento {

    @Serial
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
