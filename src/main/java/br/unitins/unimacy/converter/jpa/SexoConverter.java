package br.unitins.unimacy.converter.jpa;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import br.unitins.unimacy.model.pessoa.Sexo;

@Converter(autoApply = true)
public class SexoConverter implements AttributeConverter<Sexo,Integer>{

	@Override
	public Integer convertToDatabaseColumn(Sexo sexo) {
		return sexo == null ? null : sexo.getId();
	}

	@Override
	public Sexo convertToEntityAttribute(Integer id) {
		return Sexo.valueOf(id);
	}

}
