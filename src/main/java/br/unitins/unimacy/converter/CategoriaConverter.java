package br.unitins.unimacy.converter;

import java.util.Map;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.unitins.unimacy.model.produto.Categoria;

@FacesConverter(forClass = Categoria.class, value = "categoriaConverter")
public class CategoriaConverter implements Converter <Object>{

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		if (value != null) {
			return getMapObjects(component).get(value);
		}
		return null;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value != null) {
			Categoria categoria = (Categoria) value;
			addAttribute(component, categoria);
			String chave = String.valueOf(categoria.getId());
			return chave;
		}

		return (String) value;
	}

	protected Map<String, Object> getMapObjects(UIComponent component) {
		return component.getAttributes();
	}

	protected void addAttribute(UIComponent component, Categoria categoria) {
		String chave = String.valueOf(categoria.getId());
		getMapObjects(component).put(chave, categoria);
	}

}