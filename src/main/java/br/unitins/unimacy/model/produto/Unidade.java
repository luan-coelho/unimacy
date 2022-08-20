package br.unitins.unimacy.model.produto;

import br.unitins.unimacy.model.DefaultEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

import java.io.Serial;

@Entity
public class Unidade extends DefaultEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    private Integer qtd;
    @Enumerated(EnumType.STRING)
    private UnidadeMedida unidadeMedida;

    public Unidade() {
        // TODO Auto-generated constructor stub
    }

    public Unidade(Integer qtd, UnidadeMedida unidadeMedida) {
        super();
        this.qtd = qtd;
        this.unidadeMedida = unidadeMedida;
    }

    public Integer getQtd() {
        return qtd;
    }

    public void setQtd(Integer qtd) {
        this.qtd = qtd;
    }

    public UnidadeMedida getUnidadeMedida() {
        return unidadeMedida;
    }

    public void setUnidadeMedida(UnidadeMedida unidadeMedida) {
        this.unidadeMedida = unidadeMedida;
    }

}