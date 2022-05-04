package br.unitins.unimacy.model;

import javax.persistence.Entity;

@Entity
public class Categoria extends DefaultEntity{
	
	private static final long serialVersionUID = -3189112079821397727L;
	
	private String nome;
	
	public Categoria() {
		// TODO Auto-generated constructor stub
	}
	
	public Categoria(String nome) {
		this.nome = nome;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}

	@Override
	public String toString() {
		return getNome();
	}
	
	@Override
	public boolean equals(Object obj) {
		return super.equals(obj);
	}
	
}