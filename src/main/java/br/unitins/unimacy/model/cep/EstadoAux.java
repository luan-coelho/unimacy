package br.unitins.unimacy.model.cep;

import java.util.Objects;

public class EstadoAux {
	private Integer id;
	private String nome;
	private String sigla;

	public EstadoAux() {
		// TODO Auto-generated constructor stub
	}

	public EstadoAux(Integer id, String nome, String sigla) {
		super();
		this.id = id;
		this.nome = nome;
		this.sigla = sigla;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSigla() {
		return sigla;
	}

	public void setSigla(String sigla) {
		this.sigla = sigla;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	
	@Override
	public String toString() {
		return "EstadoAux [id=" + id + ", nome=" + nome + ", sigla=" + sigla + "]";
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EstadoAux other = (EstadoAux) obj;
		return Objects.equals(id, other.id);
	}

}