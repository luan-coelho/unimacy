package br.unitins.unimacy.model.pessoa;

import br.unitins.unimacy.model.DefaultEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

import java.io.Serial;

@Entity
public class Cliente extends DefaultEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    @OneToOne
    @JoinColumn(name = "pessoa_id")
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