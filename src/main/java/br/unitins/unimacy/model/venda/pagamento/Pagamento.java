package br.unitins.unimacy.model.venda.pagamento;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import br.unitins.unimacy.model.DefaultEntity;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Pagamento extends DefaultEntity {

	private static final long serialVersionUID = -6668849142105912700L;
	
	public Pagamento() {

	}
	
}
