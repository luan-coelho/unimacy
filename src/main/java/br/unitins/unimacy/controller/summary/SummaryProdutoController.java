package br.unitins.unimacy.controller.summary;


import br.unitins.unimacy.application.Session;
import br.unitins.unimacy.controller.Controller;
import br.unitins.unimacy.model.produto.Produto;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;

import java.io.Serial;

@Named
@RequestScoped
public class SummaryProdutoController extends Controller<Produto> {

    @Serial
    private static final long serialVersionUID = 1L;

    public SummaryProdutoController() {
        entity = (Produto) Session.getInstance().get("produto");
        Session.getInstance().set("produto", null);
    }

    @Override
    public Produto getEntity() {
        if (entity == null) entity = new Produto();
        return entity;
    }


}