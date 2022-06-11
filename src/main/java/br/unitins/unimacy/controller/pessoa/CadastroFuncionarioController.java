package br.unitins.unimacy.controller.pessoa;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import com.gtbr.exception.ViaCepException;
import com.gtbr.exception.ViaCepFormatException;

import br.unitins.unimacy.application.ApiCep;
import br.unitins.unimacy.application.Session;
import br.unitins.unimacy.application.Util;
import br.unitins.unimacy.controller.Controller;
import br.unitins.unimacy.exception.RepositoryException;
import br.unitins.unimacy.exception.VersionException;
import br.unitins.unimacy.model.pessoa.Cargo;
import br.unitins.unimacy.model.pessoa.Funcionario;
import br.unitins.unimacy.model.pessoa.PessoaFisica;
import br.unitins.unimacy.model.pessoa.Sexo;
import br.unitins.unimacy.model.pessoa.endereco.Cidade;
import br.unitins.unimacy.model.pessoa.endereco.Endereco;
import br.unitins.unimacy.model.pessoa.endereco.Estado;
import br.unitins.unimacy.repository.pessoa.FuncionarioRepository;
import br.unitins.unimacy.repository.pessoa.PessoaFisicaRepository;

@Named
@ViewScoped
public class CadastroFuncionarioController extends Controller<Funcionario> {

	private static final long serialVersionUID = -6882533417112961452L;

	public CadastroFuncionarioController() {
		super(new FuncionarioRepository());
		entity = (Funcionario) Session.getInstance().get("funcionario-crud");
		Session.getInstance().set("funcionario-crud", null);
	}

	@Override
	public Funcionario getEntity() {
		if (entity == null) {
			entity = new Funcionario(new PessoaFisica());
			entity.getPessoaFisica().setEndereco(new Endereco(new Cidade(new Estado())));
		}

		return entity;
	}

	public Sexo[] getListaSexo() {
		return Sexo.values();
	}
	
	public Cargo[] getListaCargo() {
		return Cargo.values();
	}

	public void excluir(Funcionario Funcionario) {
		entity = Funcionario;
		super.excluir();
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

	public void pesquisarPorNome(String nome) {
		try {
			getRepository().findByNome(nome);
		} catch (RepositoryException e) {
			e.printStackTrace();
		}
	}

	public void verificarCpf() {
		Funcionario pessoa = null;
		try {
			pessoa = ((FuncionarioRepository) getRepository()).findByCpf(entity.getPessoaFisica().getCpf());
		} catch (RepositoryException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (pessoa != null) {
			Util.addErrorMessage("Já existe um registro cadastrado com esse CPF");
		}
	}

	public void telaGerenciaFuncionario() {
		Util.redirect("gerencia-funcionario.xhtml");
	}

	public void enviarObjetoParaSummary() {
		Session.getInstance().set("funcionario", entity);
	}
	
	@Override
	public void salvar() {
		PessoaFisicaRepository repo = new PessoaFisicaRepository();
		
		try {
			repo.save(entity.getPessoaFisica());
		} catch (RepositoryException | VersionException e) {
			e.printStackTrace();
		}
		
		super.salvar();
	}
}