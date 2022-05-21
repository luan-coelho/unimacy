package br.unitins.unimacy.controller;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.util.Random;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import br.unitins.unimacy.application.Email;
import br.unitins.unimacy.application.Util;
import br.unitins.unimacy.exception.RepositoryException;
import br.unitins.unimacy.exception.VersionException;
import br.unitins.unimacy.model.email.ForgotPassword;
import br.unitins.unimacy.model.pessoa.Funcionario;
import br.unitins.unimacy.repository.email.ForgotPasswordRepository;
import br.unitins.unimacy.repository.pessoa.FuncionarioRepository;

@Named
@RequestScoped
public class ForgotPasswordController implements Serializable {

	private static final long serialVersionUID = -4750261338101378803L;

	@javax.validation.constraints.Email(message = "Informe um email válido")
	private String email;
	private String codigo;

	Funcionario buscarFuncionario(String email) {
		FuncionarioRepository repo = new FuncionarioRepository();

		Funcionario funcionario = null;

		try {
			funcionario = (Funcionario) repo.findOneByEmail(email);
			if (funcionario == null) {
				return null;
			}
		} catch (RepositoryException e) {
			e.printStackTrace();
			return null;
		}

		return funcionario;
	}

	private String gerarCodigoDeRecuperacao() {
		return new DecimalFormat("T-000000").format(new Random().nextInt(1000000));
	}

	private void forgotPassword() {
		ForgotPassword forgot = new ForgotPassword();
		forgot.setFuncionario(buscarFuncionario(getEmail()));
		forgot.setCodigo(gerarCodigoDeRecuperacao());
		forgot.setDataHoraLimite(LocalDateTime.now().plusDays(1));
		forgot.setUtilizado(false);
		ForgotPasswordRepository repoforgotu = new ForgotPasswordRepository();
		try {
			repoforgotu.save(forgot);
		} catch (RepositoryException e) {
			Util.addErrorMessage("Erro ao gerar o código, tente novamente.");
			e.printStackTrace();
		} catch (VersionException e) {
			Util.addErrorMessage("Erro ao gerar o código, tente novamente.");
			e.printStackTrace();
		}
	}

	public void enviarEmail() {

		Funcionario funcionario = buscarFuncionario(getEmail());
		
		if (funcionario == null) {
			Util.addErrorMessage("Email não encontrado.");
			return;
		}
		
		forgotPassword();

		Email email = new Email(funcionario.getPessoaFisica().getEmail(), "Redefinir senha",
				"Segue o código de recuperar a senha: " + getCodigo());
		if (!email.enviar()) {
			Util.addErrorMessage("Problema ao enviar o email.");
		} else
			Util.addInfoMessage("Código enviado para seu email.");
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getCodigo() {
		if(codigo == null)
			codigo = gerarCodigoDeRecuperacao();
		return codigo;
	}
	
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
}
