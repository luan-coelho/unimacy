package br.unitins.unimacy.controller.pessoa;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.validation.constraints.NotBlank;

import com.gtbr.exception.ViaCepException;
import com.gtbr.exception.ViaCepFormatException;

import br.unitins.unimacy.application.ApiCep;
import br.unitins.unimacy.application.Session;
import br.unitins.unimacy.application.Util;
import br.unitins.unimacy.controller.Controller;
import br.unitins.unimacy.exception.RepositoryException;
import br.unitins.unimacy.exception.VersionException;
import br.unitins.unimacy.model.pessoa.Funcionario;
import br.unitins.unimacy.repository.pessoa.FuncionarioRepository;
import br.unitins.unimacy.repository.pessoa.PessoaFisicaRepository;

@Named
@SessionScoped
public class PerfilController extends Controller<Funcionario> {

	private static final long serialVersionUID = -8919718922968640074L;

	private boolean isEditable;

	@NotBlank(message = "Informe a senha atual")
	private String senhaAtual;

	@NotBlank(message = "Informe uma nova senha")
	private String senha;
	@NotBlank(message = "Informe novamente uma nova senha")
	private String novaSenha;

	public PerfilController() {
		super(new FuncionarioRepository());
		setEntity((Funcionario) Session.getInstance().get("funcionarioLogado"));
	}

	public void buscarCep() {
		try {
			entity.getPessoaFisica().setEndereco(ApiCep.findCep(entity.getPessoaFisica().getEndereco().getCep()));
		} catch (ViaCepException e) {
			Util.addErrorMessage("Informe um CEP válido");
		} catch (ViaCepFormatException e) {
			Util.addErrorMessage("CEP com formato inválido");
		} catch (Exception e) {
			Util.addErrorMessage("Falha ao buscar CEP. Digite os dados");
		}

	}

	public void editar() {
		this.isEditable = !this.isEditable;
	}

	@Override
	public void salvar() {
		try {
			PessoaFisicaRepository repo = new PessoaFisicaRepository();
			repo.save(getEntity().getPessoaFisica());
			
			setEntity(getRepository().findById(getEntity().getId()));
			Session.getInstance().set("funcionarioLogado", getEntity());
			
			Util.addInfoMessage("Alterações realizadas com sucesso.");
			this.isEditable = false;
		} catch (RepositoryException e) {
			e.printStackTrace();
			Util.addErrorMessage(e.getMessage());
		} catch (VersionException e) {
			e.printStackTrace();
			Util.addErrorMessage(e.getMessage());
		}
	}
	
	@Override
	public void salvar(Funcionario obj) {
		try {
			getRepository().save(obj);
			
			setEntity(getRepository().findById(getEntity().getId()));
			Session.getInstance().set("funcionarioLogado", getEntity());
			
			Util.addInfoMessage("Senha alterada com sucesso.");
		} catch (RepositoryException e) {
			e.printStackTrace();
			Util.addErrorMessage(e.getMessage());
		} catch (VersionException e) {
			e.printStackTrace();
			Util.addErrorMessage(e.getMessage());
		}
	}

	public void alterarSenha() {
		if (!senha.equals(novaSenha)) {
			Util.addErrorMessage("As senhas não coincidem");
			return;
		}

		if (Util.hash(getEntity().getPessoaFisica().getCpf()+senhaAtual).equals(getEntity().getSenha())) {
			getEntity().setSenha(Util.hash(getEntity().getPessoaFisica().getCpf()+novaSenha));
			salvar(getEntity());
		} else {
			Util.addErrorMessage("Senha atual incorreta");
			return;
		}
	}

	@Override
	public Funcionario getEntity() {
		if (entity == null) {
			entity = new Funcionario();
		}
		return entity;
	}

	public boolean isEditable() {
		return isEditable;
	}

	public void setEditable(boolean isEditable) {
		this.isEditable = isEditable;
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

	public String getSenhaAtual() {
		return senhaAtual;
	}

	public void setSenhaAtual(String senhaAtual) {
		this.senhaAtual = senhaAtual;
	}
}