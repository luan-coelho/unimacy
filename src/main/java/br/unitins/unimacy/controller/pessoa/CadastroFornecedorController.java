package br.unitins.unimacy.controller.pessoa;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import com.gtbr.exception.ViaCepException;
import com.gtbr.exception.ViaCepFormatException;

import br.unitins.unimacy.application.ApiCep;
import br.unitins.unimacy.application.Session;
import br.unitins.unimacy.application.Util;
import br.unitins.unimacy.controller.Controller;
import br.unitins.unimacy.model.pessoa.Fornecedor;
import br.unitins.unimacy.model.pessoa.endereco.Cidade;
import br.unitins.unimacy.model.pessoa.endereco.Endereco;
import br.unitins.unimacy.model.pessoa.endereco.Estado;
import br.unitins.unimacy.repository.pessoa.FornecedorRepository;

@Named
@ViewScoped
public class CadastroFornecedorController extends Controller<Fornecedor> {

	private static final long serialVersionUID = 3742874752519474535L;

	public CadastroFornecedorController() {
		super(new FornecedorRepository());
		entity = (Fornecedor) Session.getInstance().get("fornecedor-crud");
		Session.getInstance().set("fornecedor-crud", null);
	}

	@Override
	public Fornecedor getEntity() {
		if (entity == null) {
			entity = new Fornecedor();
			entity.getPessoaJuridica().setEndereco(new Endereco(new Cidade(new Estado())));
		}

		return entity;

	}

	public void excluir(Fornecedor Fornecedor) {
		entity = Fornecedor;
		super.excluir();
	}

	public void buscarCep() {
		try {
			entity.getPessoaJuridica().setEndereco(ApiCep.findCep(entity.getPessoaJuridica().getEndereco().getCep()));
		} catch (ViaCepException e) {
			Util.addErrorMessage("Informe um CEP válido");
		} catch (ViaCepFormatException e) {
			Util.addErrorMessage("CEP com formato inválido");
		} catch (Exception e) {
			Util.addErrorMessage("Falha ao buscar CEP. Digite os dados");
		}
	}

	public void verificarC() {
		Fornecedor fornecedor = ((FornecedorRepository) getRepository())
				.findByCpnj(entity.getPessoaJuridica().getCnpj());

		if (fornecedor != null) {
			Util.addErrorMessage("Já existe um registro cadastrado com esse CNPJ");
		}
	}

	public void telaGerenciaFornecedor() {
		Util.redirect("gerencia-fornecedor.xhtml");
	}

	public void enviarObjetoParaSummary() {
		Session.getInstance().set("fornecedor", entity);
	}
}