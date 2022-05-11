package br.unitins.unimacy.model.venda;

import java.util.List;

import javax.persistence.OneToMany;

import br.unitins.unimacy.model.DefaultEntity;
import br.unitins.unimacy.model.produto.Produto;

public class ProdutoVenda extends DefaultEntity{

	private static final long serialVersionUID = -8367286864500225366L;

	@OneToMany
	private List <Produto> produtos;
}
