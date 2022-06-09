package br.unitins.unimacy.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.unitins.unimacy.exception.RepositoryException;
import br.unitins.unimacy.model.produto.Categoria;
import br.unitins.unimacy.repository.produto.CategoriaRepository;

@FacesConverter(forClass = Categoria.class, value = "categoriaConverter")
public class CategoriaConverter implements Converter <Categoria>{

	@Override
	public Categoria getAsObject(FacesContext context, UIComponent component, String value) {
		CategoriaRepository repo = new CategoriaRepository();
		try {
			return (Categoria) repo.findById(Integer.parseInt(value));
		} catch (RepositoryException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Categoria categoria) {
		return categoria.getId().toString();
	}
}