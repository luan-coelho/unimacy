package br.unitins.unimacy.controller.summary;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import br.unitins.unimacy.application.Session;
import br.unitins.unimacy.controller.Controller;
import br.unitins.unimacy.model.produto.Produto;

@Named
@RequestScoped
public class SummaryProdutoController extends Controller<Produto> {

	private static final long serialVersionUID = -1830368911887420966L;

	public SummaryProdutoController() {
		entity = (Produto) Session.getInstance().get("produto");
		Session.getInstance().set("produto", null);
	}
	
	@Override
	public Produto getEntity() {
		if(entity == null)
			entity = new Produto();
		return entity;
	}


}