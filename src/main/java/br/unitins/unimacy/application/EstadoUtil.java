package br.unitins.unimacy.application;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.faces.view.ViewScoped;

import br.unitins.unimacy.model.pessoa.endereco.Estado;
import jakarta.inject.Named;

@Named
@ViewScoped
public class EstadoUtil implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private final List<Estado> listaEstadoApi = ApiCep.pegarEstados();

    public List<String> completeText(String query) {
        List<String> listaEstadoString = new ArrayList<>();

        for (Estado estado : listaEstadoApi) {
            listaEstadoString.add(estado.getNome());
        }

        return listaEstadoString.stream().filter(t -> t.toLowerCase().startsWith(query.toLowerCase())).collect(Collectors.toList());
    }

    public List<Estado> completeEstado(String query) {
        return listaEstadoApi.stream().filter(t -> t.getNome().toLowerCase().contains(query.toLowerCase())).collect(Collectors.toList());
    }
}