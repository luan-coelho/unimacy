package br.unitins.unimacy.controller.forgot;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.validation.constraints.NotBlank;

import br.unitins.unimacy.application.Util;
import br.unitins.unimacy.controller.Controller;
import br.unitins.unimacy.exception.RepositoryException;
import br.unitins.unimacy.exception.VersionException;
import br.unitins.unimacy.model.email.ForgotPassword;
import br.unitins.unimacy.model.pessoa.Funcionario;
import br.unitins.unimacy.model.pessoa.PessoaFisica;
import br.unitins.unimacy.repository.email.ForgotPasswordRepository;
import br.unitins.unimacy.repository.pessoa.FuncionarioRepository;

@Named
@ViewScoped
public class RedefinePasswordController extends Controller<Funcionario> implements Serializable {

	private static final long serialVersionUID = 2951252099683624290L;

	private String codigo;

	@NotBlank(message = "Informe uma nova senha")
	private String senha;
	@NotBlank(message = "Informe novamente uma nova senha")
	private String novaSenha;

	private ForgotPassword forgotPassword;

	private FuncionarioRepository funcionarioRepository;
	private ForgotPasswordRepository forgotPasswordRepository;

	public void alterarSenha() {
		if (!senha.equals(novaSenha)) {
			Util.addErrorMessage("As senhas não coincidem");
			return;
		}

		if (validarCodigo()) {
			getForgotPassword().getFuncionario()
					.setSenha(Util.hash(getForgotPassword().getFuncionario().getPessoaFisica().getEmail(), novaSenha));
			getForgotPassword().setUtilizado(true);

			try {
				getForgotPasswordRepository().save(forgotPassword);
			} catch (RepositoryException e) {
				e.printStackTrace();
			} catch (VersionException e) {
				e.printStackTrace();
			}
			Util.addInfoMessage("Sucesso!", "Senha alterada com sucesso");
		}
	}

	public boolean validarCodigo() {
		try {
			this.forgotPassword = getForgotPasswordRepository().findByCodigo(codigo);
			if (forgotPassword != null) {
				if (forgotPassword.getDataHoraLimite().compareTo(LocalDateTime.now()) < 0) {
					Util.addWarnMessage("Código expirado", "Realize uma nova solicitação");
					forgotPassword.setUtilizado(true);
					return false;
				} else if (forgotPassword.getUtilizado()) {
					Util.addWarnMessage("Código já utilizado", "Realize uma nova solicitação");
					return false;
				}
				return true;
			} else {
				Util.addErrorMessage("Código inválido");
				return false;
			}
		} catch (RepositoryException e) {
			e.printStackTrace();
		}

		return false;
	}

	@Override
	public Funcionario getEntity() {
		if (entity == null)
			entity = new Funcionario(new PessoaFisica());
		return entity;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getNovaSenha() {
		return novaSenha;
	}

	public void setNovaSenha(String novaSenha) {
		this.novaSenha = novaSenha;
	}

	public ForgotPassword getForgotPassword() {
		return forgotPassword;
	}

	public void setForgotPassword(ForgotPassword forgotPassword) {
		this.forgotPassword = forgotPassword;
	}

	public ForgotPasswordRepository getForgotPasswordRepository() {
		if (forgotPasswordRepository == null)
			forgotPasswordRepository = new ForgotPasswordRepository();
		return forgotPasswordRepository;
	}

	public void setForgotPasswordRepository(ForgotPasswordRepository forgotPasswordRepository) {
		this.forgotPasswordRepository = forgotPasswordRepository;
	}

	public FuncionarioRepository getFuncionarioRepository() {
		if (funcionarioRepository == null)
			funcionarioRepository = new FuncionarioRepository();
		return funcionarioRepository;
	}

	public void setFuncionarioRepository(FuncionarioRepository funcionarioRepository) {
		this.funcionarioRepository = funcionarioRepository;
	}
}
