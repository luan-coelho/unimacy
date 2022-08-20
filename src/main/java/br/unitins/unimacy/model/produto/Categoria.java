package br.unitins.unimacy.model.produto;

import br.unitins.unimacy.model.DefaultEntity;
import jakarta.persistence.Entity;

import java.io.Serial;

@Entity
public class Categoria extends DefaultEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    private String nome;

    public Categoria() {
        // TODO Auto-generated constructor stub
    }

    public Categoria(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return getNome();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

}