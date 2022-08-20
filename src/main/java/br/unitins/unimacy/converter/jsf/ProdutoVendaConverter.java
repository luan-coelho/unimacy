package br.unitins.unimacy.converter.jsf;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.unitins.unimacy.exception.RepositoryException;
import br.unitins.unimacy.model.produto.Produto;
import br.unitins.unimacy.model.venda.ProdutoVenda;
import br.unitins.unimacy.repository.produto.ProdutoRepository;

@FacesConverter(forClass = ProdutoVenda.class, value = "produtoVendaConverter")
public class ProdutoVendaConverter implements Converter<ProdutoVenda> {

    @Override
    public ProdutoVenda getAsObject(FacesContext context, UIComponent component, String value) {
        if (!value.isBlank()) {
            try {
                Produto produto = new ProdutoRepository().findById(Integer.parseInt(value));
                return new ProdutoVenda(produto.getPreco(), 0, produto);
            } catch (RepositoryException e) {
                e.printStackTrace();
                return null;
            }
        }

        return null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, ProdutoVenda produtoVenda) {
        return produtoVenda.getProduto().getId().toString();
    }
}