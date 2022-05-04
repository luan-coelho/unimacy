package br.unitins.unimacy.model;

import java.util.Objects;

import javax.persistence.Entity;

@Entity
public class Estado extends DefaultEntity {

	private static final long serialVersionUID = 5693436511646646283L;

	private String nome;
	private String uf;

	public Estado() {
		// TODO Auto-generated constructor stub
	}

	public Estado(String nome, String uf) {
		super();
		this.nome = nome;
		this.uf = uf;
	}
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(nome, uf);
		return result;
	}
	
	@Override
	public String toString() {
		return "Estado [id="+getId() + "nome=" + nome + ", uf=" + uf + "]";
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Estado other = (Estado) obj;
		return Objects.equals(getId(), other.getId());
	}
	

}