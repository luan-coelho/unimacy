package br.unitins.unimacy.model.venda.pagamento;

import jakarta.persistence.Entity;

import java.io.Serial;

@Entity
public class Pix extends Pagamento {

    @Serial
    private static final long serialVersionUID = 1L;

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
