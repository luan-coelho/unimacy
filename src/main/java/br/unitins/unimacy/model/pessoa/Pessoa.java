package br.unitins.unimacy.model.pessoa;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToOne;
import javax.validation.constraints.Email;

import br.unitins.unimacy.model.DefaultEntity;
import br.unitins.unimacy.model.pessoa.endereco.Endereco;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Pessoa extends DefaultEntity {

	private static final long serialVersionUID = 2886589069101743676L;

	@Email(message = "Informe um email válido")
	private String email;
	private String telefone;

	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Endereco endereco;

	public Pessoa() {
		// TODO Auto-generated constructor stub
	}

	public Pessoa(Endereco endereco) {
		this.endereco = endereco;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	@Override
	public String toString() {
		return "Pessoa [email=" + email + ", telefone=" + telefone + ", endereco=" + endereco + "]";
	}
}