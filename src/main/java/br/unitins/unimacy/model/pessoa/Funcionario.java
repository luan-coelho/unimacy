package br.unitins.unimacy.model.pessoa;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import br.unitins.unimacy.model.DefaultEntity;

@Entity
public class Funcionario extends DefaultEntity {

	private static final long serialVersionUID = 6772757150528225911L;

	private String senha;

	private BigDecimal salario;

	@OneToOne
	@JoinColumn(unique = true)
	private PessoaFisica pessoaFisica;

	public Funcionario() {
		// TODO Auto-generated constructor stub
	}

	public Funcionario(BigDecimal salario, PessoaFisica pessoaFisica) {
		super();
		this.salario = salario;
		this.pessoaFisica = pessoaFisica;
	}

	public Funcionario(PessoaFisica pessoaFisica) {
		super();
		this.pessoaFisica = pessoaFisica;
	}

	public BigDecimal getSalario() {
		return salario;
	}

	public void setSalario(BigDecimal salario) {
		this.salario = salario;
	}

	public PessoaFisica getPessoaFisica() {
		return pessoaFisica;
	}

	public void setPessoaFisica(PessoaFisica pessoaFisica) {
		this.pessoaFisica = pessoaFisica;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

}
