package br.unitins.unimacy.controller.forgot;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.util.Random;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import org.hibernate.validator.constraints.br.CPF;

import br.unitins.unimacy.application.Email;
import br.unitins.unimacy.application.Session;
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
	
	@CPF
	private String cpf;
	private String codigo;
	
	private Funcionario funcionario;
	
	private boolean emailEnviado;

	private Funcionario buscarFuncionario(String email, String cpf) {
		FuncionarioRepository repo = new FuncionarioRepository();

		Funcionario funcionario = null;

		try {
			funcionario = (Funcionario) repo.findOneByEmailAndCpf(email, cpf);
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
		forgot.setFuncionario(getFuncionario());
		forgot.setCodigo(getCodigo());
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

		if (getFuncionario() == null) {
			Util.addErrorMessage("Email ou CPF não encontrado.");
			return;
		}

		forgotPassword();

		Email email = new Email(funcionario.getPessoaFisica().getEmail(), "Recuperar senha", getCodigo());
		
		setEmailEnviado(email.enviar());
		
		if (!isEmailEnviado()) {
			Util.addErrorMessage("Problema ao enviar o email.");
		} else {
			Session.getInstance().set("email-fp", getEmail());
			Util.redirect("forgotpassword-sucess.xhtml");
		}
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCodigo() {
		if (codigo == null)
			codigo = gerarCodigoDeRecuperacao();
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	
	public String getCpf() {
		return cpf;
	}
	
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	
	public Funcionario getFuncionario() {
		if(funcionario == null)
			funcionario = buscarFuncionario(email, cpf);
		return funcionario;
	}
	
	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}
	
	public boolean isEmailEnviado() {
		return emailEnviado;
	}
	
	public void setEmailEnviado(boolean emailEnviado) {
		this.emailEnviado = emailEnviado;
	}
}
