package br.unitins.unimacy.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.unitins.unimacy.exception.RepositoryException;
import br.unitins.unimacy.model.produto.Produto;
import br.unitins.unimacy.repository.produto.ProdutoRepository;

@FacesConverter(forClass = Produto.class, value = "produtoConverter")
public class ProdutoConverter implements Converter <Produto>{

	@Override
	public Produto getAsObject(FacesContext context, UIComponent component, String value) {
		ProdutoRepository repo = new ProdutoRepository();
		try {
			return repo.findById(Integer.parseInt(value));
		} catch (RepositoryException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Produto produto) {
		return produto.getId().toString();
	}
}