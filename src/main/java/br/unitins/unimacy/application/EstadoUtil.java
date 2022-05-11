package br.unitins.unimacy.application;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.unitins.unimacy.model.pessoa.endereco.Estado;

@Named
@ViewScoped
public class EstadoUtil implements Serializable {

	private static final long serialVersionUID = -1251069270232023122L;

	private List<Estado> listaEstadoApi = ApiCep.pegarEstados();

	public List<String> completeText(String query) {
		List<String> listaEstadoString = new ArrayList<>();

		for (Estado estado : listaEstadoApi) {
			listaEstadoString.add(estado.getNome());
		}

		return listaEstadoString.stream().filter(t -> t.toLowerCase().startsWith(query.toLowerCase()))
				.collect(Collectors.toList());
	}

	public List<Estado> completeEstado(String query) {
		return listaEstadoApi.stream().filter(t -> t.getNome().toLowerCase().contains(query.toLowerCase()))
				.collect(Collectors.toList());
	}
}