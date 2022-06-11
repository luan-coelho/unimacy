package br.unitins.unimacy.controller.login;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import br.unitins.unimacy.application.Session;
import br.unitins.unimacy.application.Util;
import br.unitins.unimacy.controller.Controller;
import br.unitins.unimacy.exception.RepositoryException;
import br.unitins.unimacy.model.pessoa.Funcionario;
import br.unitins.unimacy.model.pessoa.PessoaFisica;
import br.unitins.unimacy.repository.pessoa.FuncionarioRepository;

@Named
@RequestScoped
public class LoginController extends Controller<Funcionario> {

	private static final long serialVersionUID = 8117607496625416166L;

	private Funcionario funcionario;

	public void entrar() {
		FuncionarioRepository repo = new FuncionarioRepository();
		Funcionario funcionarioLogado = null;

		try {
			funcionarioLogado = repo.validarLogin(getFuncionario().getPessoaFisica().getCpf().trim(),
					Util.hash(getFuncionario().getPessoaFisica().getCpf().trim(), getFuncionario().getSenha()));
		} catch (RepositoryException e) {
			e.printStackTrace();
		}
		if (funcionarioLogado != null) {
			if (funcionarioLogado.getPessoaFisica().isAtivo()) {
				Session.getInstance().set("funcionarioLogado", funcionarioLogado);
				Session.getInstance().set("cargoFuncionario", funcionarioLogado.getCargo());
				Util.redirect("gestao/venda.xhtml");
				return;
			}
			Util.addWarnMessage("Seu acesso está desativado.");
			return;
		}

		Util.addErrorMessage("Login ou senha inválido.");
	}

	public void limpar() {
		funcionario = null;
	}

	public Funcionario getFuncionario() {
		if (funcionario == null)
			funcionario = new Funcionario(new PessoaFisica());
		return funcionario;
	}

	public void setFuncionario(Funcionario Funcionario) {
		this.funcionario = Funcionario;
	}

	@Override
	public Funcionario getEntity() {
		if (entity == null) {
			entity = new Funcionario(new PessoaFisica());
		}
		return entity;
	}
}
