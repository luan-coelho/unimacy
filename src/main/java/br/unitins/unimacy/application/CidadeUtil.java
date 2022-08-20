package br.unitins.unimacy.application;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.faces.view.ViewScoped;

import br.unitins.unimacy.model.pessoa.endereco.Cidade;
import jakarta.inject.Named;

@Named
@ViewScoped
public class CidadeUtil implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private String nomeEstado;

    private List<Cidade> listaCidade;

    public CidadeUtil() {

    }

    public String getNomeEstado() {
        if (nomeEstado == null) {
            nomeEstado = (String) Session.getInstance().get("nome-estado");
        }
        return nomeEstado;
    }

    public void setNomeEstado(String nomeEstado) {
        this.nomeEstado = nomeEstado;
    }

    public List<Cidade> getListaCidade() {
        if (listaCidade == null) {
            String siglaEstado = ApiCep.pegarUfEstadoporNome(getNomeEstado());

            this.listaCidade = ApiCep.pegarCidadePorUf(siglaEstado);

            setNomeEstado(null);
        }
        return listaCidade;
    }

    public void setListaCidade(List<Cidade> listaCidade) {
        this.listaCidade = listaCidade;
    }

    public List<String> completeText(String query) {
        List<String> listaCidadeString = new ArrayList<>();

        setListaCidade(null);

        for (Cidade Cidade : getListaCidade()) {
            listaCidadeString.add(Cidade.getNome());
        }

        return listaCidadeString.stream().filter(t -> t.toLowerCase().startsWith(query.toLowerCase())).collect(Collectors.toList());
    }
}