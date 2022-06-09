package br.unitins.unimacy.model.pessoa;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Entity
public class PessoaFisica extends Pessoa {

	private static final long serialVersionUID = 2489138959144990316L;

	private String nome;
	private String sobreNome;
	
	@Enumerated(EnumType.STRING)
	private Sexo sexo;

	// @CPF(message = "Informe um CPF válido")
	@Column(unique = true)
	private String cpf;
	private LocalDate dataNascimento;

	public PessoaFisica() {
		// TODO Auto-generated constructor stub
	}

	public PessoaFisica(String nome, String sobreNome, Sexo sexo, String cpf, LocalDate dataNascimento) {
		super();
		this.nome = nome;
		this.sobreNome = sobreNome;
		this.sexo = sexo;
		this.cpf = cpf;
		this.dataNascimento = dataNascimento;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSobreNome() {
		return sobreNome;
	}

	public void setSobreNome(String sobreNome) {
		this.sobreNome = sobreNome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public LocalDate getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public Sexo getSexo() {
		return sexo;
	}

	public void setSexo(Sexo sexo) {
		this.sexo = sexo;
	}

	@Override
	public String toString() {
		return "PessoaFisica [nome=" + nome + ", sobreNome=" + sobreNome + ", sexo=" + sexo + ", cpf=" + cpf
				+ ", dataNascimento=" + dataNascimento + "status " + isAtivo() + "]";
	}
}