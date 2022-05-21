package br.unitins.unimacy.model.email;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import br.unitins.unimacy.model.DefaultEntity;
import br.unitins.unimacy.model.pessoa.Funcionario;

@Entity
public class ForgotPassword extends DefaultEntity {

	private static final long serialVersionUID = -8615972044922024177L;
	
	private String codigo;
	@ManyToOne
	@JoinColumn(name = "id_funcionario")
	private Funcionario funcionario;
	private LocalDateTime dataHoraLimite;
	private Boolean utilizado;

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	public LocalDateTime getDataHoraLimite() {
		return dataHoraLimite;
	}

	public void setDataHoraLimite(LocalDateTime dataHoraLimite) {
		this.dataHoraLimite = dataHoraLimite;
	}

	public Boolean getUtilizado() {
		return utilizado;
	}

	public void setUtilizado(Boolean utilizado) {
		this.utilizado = utilizado;
	}

}
