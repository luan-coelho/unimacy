package br.unitins.unimacy.model.pessoa.endereco;


import br.unitins.unimacy.model.DefaultEntity;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;

import java.io.Serial;

@Entity
public class Endereco extends DefaultEntity {

	@Serial
	private static final long serialVersionUID = 1;

	private String rua;
	private String numero;
	private String bairro;
	private String complemento;
	private String cep;

	@ManyToOne(cascade = CascadeType.MERGE)
	private Cidade cidade;

	public Endereco() {
		// TODO Auto-generated constructor stub
	}

	public Endereco(String rua, String numero, String bairro, String complemento, String cep, Cidade cidade) {
		super();
		this.rua = rua;
		this.numero = numero;
		this.bairro = bairro;
		this.complemento = complemento;
		this.cep = cep;
		this.cidade = cidade;
	}

	public Endereco(Cidade cidade) {
		super();
		this.cidade = cidade;
	}

	public String getRua() {
		return rua;
	}

	public void setRua(String rua) {
		this.rua = rua;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public Cidade getCidade() {
		return cidade;
	}

	public void setCidade(Cidade cidade) {
		this.cidade = cidade;
	}

}