package br.unitins.unimacy.model.pessoa;


import br.unitins.unimacy.model.DefaultEntity;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;

import java.io.Serial;

@Entity
public class Fornecedor extends DefaultEntity {

    @Serial
    private static final long serialVersionUID = 1L;

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