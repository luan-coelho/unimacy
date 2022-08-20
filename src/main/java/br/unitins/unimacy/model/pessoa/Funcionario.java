package br.unitins.unimacy.model.pessoa;

import java.io.Serial;
import java.math.BigDecimal;


import br.unitins.unimacy.model.DefaultEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

@Entity
public class Funcionario extends DefaultEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    private Cargo cargo;
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

    public Cargo getCargo() {
        return cargo;
    }

    public void setCargo(Cargo cargo) {
        this.cargo = cargo;
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
