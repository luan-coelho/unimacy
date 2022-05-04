package br.unitins.unimacy.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.unitins.unimacy.model.Cidade;

@FacesConverter(forClass = Cidade.class, value = "cidadeConverter")
public class CidadeConverter implements Converter<Cidade>  {

		@Override
		public Cidade getAsObject(FacesContext context, UIComponent component, String value) {
			Cidade cidade = new Cidade();
			cidade.setNome(value);
			
			return cidade;
		}

		@Override
		public String getAsString(FacesContext context, UIComponent component, Cidade cidade) {
			return cidade.getNome();
		}
}