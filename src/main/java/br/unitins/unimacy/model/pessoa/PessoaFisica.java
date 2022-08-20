package br.unitins.unimacy.model.pessoa;

import java.io.Serial;
import java.time.LocalDate;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import org.hibernate.validator.constraints.br.CPF;

@Entity
public class PessoaFisica extends Pessoa {

    @Serial
    private static final long serialVersionUID = 1L;

    @NotBlank(message = "Informe o nome")
    private String nome;

    @NotBlank(message = "Informe o sobrenome")
    private String sobreNome;

    @NotNull(message = "Informe o sexo")
    private Sexo sexo;

    @CPF(message = "Informe um CPF válido")
    @Column(unique = true)
    private String cpf;

    @Past(message = "Informe uma data de nascimento anterior ao dia de hoje")
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
        return "PessoaFisica [nome=" + nome + ", sobreNome=" + sobreNome + ", sexo=" + sexo + ", cpf=" + cpf + ", dataNascimento=" + dataNascimento + "status " + isAtivo() + "]";
    }
}