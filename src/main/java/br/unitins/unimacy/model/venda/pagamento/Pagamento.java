package br.unitins.unimacy.model.venda.pagamento;


import br.unitins.unimacy.model.DefaultEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;

import java.io.Serial;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Pagamento extends DefaultEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    public Pagamento() {

    }

}
