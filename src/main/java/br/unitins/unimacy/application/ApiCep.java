package br.unitins.unimacy.application;

import br.unitins.unimacy.model.cep.CidadeAux;
import br.unitins.unimacy.model.cep.EnderecoAux;
import br.unitins.unimacy.model.cep.EstadoAux;
import br.unitins.unimacy.model.pessoa.endereco.Cidade;
import br.unitins.unimacy.model.pessoa.endereco.Endereco;
import br.unitins.unimacy.model.pessoa.endereco.Estado;
import br.unitins.unimacy.repository.pessoa.endereco.CidadeRepository;
import br.unitins.unimacy.repository.pessoa.endereco.EstadoRepository;
import com.google.gson.Gson;
import com.gtbr.exception.ViaCepException;
import com.gtbr.exception.ViaCepFormatException;
import jakarta.persistence.NoResultException;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ApiCep {

    private static final String viaCepUrl = "https://viacep.com.br/ws/";
    private static final Gson gson = new Gson();

    public static Endereco findCep(String cepString) {
        cepString = removeMascaraCep(cepString);
        validaCep(cepString);

        try {
            HttpClient httpClient = HttpClient.newBuilder().connectTimeout(Duration.ofMinutes(1L)).build();

            HttpRequest httpRequest = HttpRequest.newBuilder().GET().uri(URI.create(viaCepUrl + cepString + "/json")).build();

            HttpResponse<String> httpResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());

            EnderecoAux enderecoAux = gson.fromJson(httpResponse.body(), EnderecoAux.class);

            return montarObjetoEndereco(enderecoAux, pegarEstadoPorUf(enderecoAux.getUf()));

        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    private static Endereco montarObjetoEndereco(EnderecoAux enderecoAux, EstadoAux estadoAux) {
        CidadeRepository cidadeRepository = new CidadeRepository();
        EstadoRepository estadoRepository = new EstadoRepository();

        Endereco endereco = new Endereco(new Cidade(new Estado()));

        endereco.setCep(enderecoAux.getCep());
        endereco.setRua(enderecoAux.getLogradouro());
        endereco.setBairro(enderecoAux.getBairro());

        Cidade cidade;

        try {
            cidade = cidadeRepository.findOneResultByNome(enderecoAux.getLocalidade(), estadoAux.getNome());
            endereco.setCidade(cidade);

            return endereco;
        } catch (NoResultException e) {
            endereco.getCidade().setNome(enderecoAux.getLocalidade());
            endereco.getCidade().getEstado().setNome(estadoAux.getNome());
            endereco.getCidade().getEstado().setUf(enderecoAux.getUf());
        }

        try {
            Estado estado = estadoRepository.findOneResultByNome(estadoAux.getNome());
            endereco.getCidade().setEstado(estado);
        } catch (Exception e) {
            return endereco;
        }

        return endereco;
    }

    public static void validaCep(String cep) {
        if (Objects.isNull(cep) || cep.isEmpty() || cep.isBlank()) throw new ViaCepException(null);
        if (cep.length() > 8) throw new ViaCepFormatException(null);
        if (cep.length() < 8) throw new ViaCepException(null);
    }

    public static String removeMascaraCep(String cep) {
        try {
            validaCep(cep);
            return cep;
        } catch (ViaCepFormatException e) {
            return cep.replace("-", "");
        }
    }

    private static EstadoAux pegarEstadoPorUf(String uf) {
        try {
            HttpClient httpClient = HttpClient.newBuilder().connectTimeout(Duration.ofMinutes(1L)).build();

            final String SERVICE_IBGE = "https://servicodados.ibge.gov.br/api/v1/localidades/estados/";

            HttpRequest httpRequest = HttpRequest.newBuilder().GET().uri(URI.create(SERVICE_IBGE + uf)).build();

            HttpResponse<String> httpResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());

            return gson.fromJson(httpResponse.body(), EstadoAux.class);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
    }

    public static List<Estado> pegarEstados() {
        try {
            HttpClient httpClient = HttpClient.newBuilder().connectTimeout(Duration.ofMinutes(1L)).build();

            final String SERVICE_IBGE = "https://servicodados.ibge.gov.br/api/v1/localidades/estados";

            HttpRequest httpRequest = HttpRequest.newBuilder().GET().uri(URI.create(SERVICE_IBGE)).build();

            HttpResponse<String> httpResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());

            EstadoAux[] estados = gson.fromJson(httpResponse.body(), EstadoAux[].class);

            return converterAuxParaEstado(estados);

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
    }

    public static List<Cidade> pegarCidadePorUf(String uf) {
        try {
            HttpClient httpClient = HttpClient.newBuilder().connectTimeout(Duration.ofMinutes(1L)).build();

            final String SERVICE_IBGE = "https://servicodados.ibge.gov.br/api/v1/localidades/estados/" + uf + "/distritos";

            HttpRequest httpRequest = HttpRequest.newBuilder().GET().uri(URI.create(SERVICE_IBGE)).build();

            HttpResponse<String> httpResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());

            CidadeAux[] cidades = gson.fromJson(httpResponse.body(), CidadeAux[].class);

            return converterAuxParaCidade(cidades);

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
    }

    public static List<Estado> converterAuxParaEstado(EstadoAux[] estados) {

        List<Estado> listaEstados = new ArrayList<>();

        for (EstadoAux estadoAux : estados) {
            Estado estado = new Estado();

            estado.setId(estadoAux.getId());
            estado.setNome(estadoAux.getNome());
            estado.setUf(estadoAux.getSigla());

            listaEstados.add(estado);
        }

        return listaEstados;
    }

    public static String pegarUfEstadoporNome(String nome) {
        List<Estado> listaEstados = pegarEstados();

        List<Estado> estado = listaEstados.stream().filter(e -> e.getNome().equals(nome)).toList();

        if (!estado.isEmpty()) {
            return estado.get(0).getUf();
        }

        return null;
    }

    public static List<Cidade> converterAuxParaCidade(CidadeAux[] cidades) {

        List<Cidade> listaCidades = new ArrayList<>();

        for (CidadeAux cidade : cidades) {
            listaCidades.add(new Cidade(cidade.getNome()));
        }

        return listaCidades;
    }
}