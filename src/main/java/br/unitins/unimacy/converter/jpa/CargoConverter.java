package br.unitins.unimacy.converter.jpa;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import br.unitins.unimacy.model.pessoa.Cargo;

@Converter(autoApply = true)
public class CargoConverter implements AttributeConverter<Cargo,Integer>{

	@Override
	public Integer convertToDatabaseColumn(Cargo cargo) {
		return cargo == null ? null : cargo.getId();
	}

	@Override
	public Cargo convertToEntityAttribute(Integer id) {
		return Cargo.valueOf(id);
	}

}