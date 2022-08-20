package br.unitins.unimacy.converter.jsf;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.unitins.unimacy.model.pessoa.endereco.Estado;

@FacesConverter(forClass = Estado.class, value = "estadoConverter")
public class EstadoConverter implements Converter<Estado> {

    @Override
    public Estado getAsObject(FacesContext context, UIComponent component, String value) {
        Estado estado = new Estado();
        estado.setNome(value);

        return estado;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Estado estado) {
        return estado.getNome();
    }
}