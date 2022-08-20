package br.unitins.unimacy.model.pessoa;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.br.CNPJ;

@Entity
public class PessoaJuridica extends Pessoa {

    private static final long serialVersionUID = 5775140670357351776L;

    @CNPJ(message = "Informe um CNPJ válido")
    @Column(unique = true)
    private String cnpj;
    @NotBlank(message = "Informe a Razão Social")
    private String razaoSocial;
    @NotBlank(message = "Informe o nome fantasia")
    private String nomeFantasia;
    @NotBlank(message = "Informe a Inscrição Estadual")
    private String inscEstadual;

    public PessoaJuridica() {
        // TODO Auto-generated constructor stub
    }

    public PessoaJuridica(String cnpj, String razaoSocial, String nomeFantasia, String inscEstadual) {
        super();
        this.cnpj = cnpj;
        this.razaoSocial = razaoSocial;
        this.nomeFantasia = nomeFantasia;
        this.inscEstadual = inscEstadual;
    }

    public String getRazaoSocial() {
        return razaoSocial;
    }

    public void setRazaoSocial(String razaoSocial) {
        this.razaoSocial = razaoSocial;
    }

    public String getNomeFantasia() {
        return nomeFantasia;
    }

    public void setNomeFantasia(String nomeFantasia) {
        this.nomeFantasia = nomeFantasia;
    }

    public String getInscEstadual() {
        return inscEstadual;
    }

    public void setInscEstadual(String inscEstadual) {
        this.inscEstadual = inscEstadual;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    @Override
    public String toString() {
        return "PessoaJuridica [cnpj=" + cnpj + ", razaoSocial=" + razaoSocial + ", nomeFantasia=" + nomeFantasia + ", inscEstadual=" + inscEstadual + "]";
    }
}