package br.unitins.unimacy.controller.pessoa;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import com.gtbr.exception.ViaCepException;
import com.gtbr.exception.ViaCepFormatException;

import br.unitins.unimacy.application.ApiCep;
import br.unitins.unimacy.application.Session;
import br.unitins.unimacy.application.Util;
import br.unitins.unimacy.controller.Controller;
import br.unitins.unimacy.model.pessoa.PessoaJuridica;
import br.unitins.unimacy.model.pessoa.endereco.Cidade;
import br.unitins.unimacy.model.pessoa.endereco.Endereco;
import br.unitins.unimacy.model.pessoa.endereco.Estado;
import br.unitins.unimacy.repository.pessoa.PessoaJuridicaRepository;

@Named
@ViewScoped
public class CadastroPessoaJuridicaController extends Controller<PessoaJuridica> {

	private static final long serialVersionUID = -3377591025104822813L;

	public CadastroPessoaJuridicaController() {
		super(new PessoaJuridicaRepository());
		entity = (PessoaJuridica) Session.getInstance().get("pessoajuridica-crud");
		Session.getInstance().set("pessoajuridica-crud", null);
	}

	@Override
	public PessoaJuridica getEntity() {
		if (entity == null) {
			entity = new PessoaJuridica();
			entity.setEndereco(new Endereco(new Cidade(new Estado())));
		}

		return entity;

	}

	public void excluir(PessoaJuridica PessoaJuridica) {
		entity = PessoaJuridica;
		super.excluir();
	}

	public void buscarCep() {
		try {
			entity.setEndereco(ApiCep.findCep(entity.getEndereco().getCep()));
		} catch (ViaCepException e) {
			Util.addErrorMessage("Informe um CEP válido");
		} catch (ViaCepFormatException e) {
			Util.addErrorMessage("CEP com formato inválido");
		} catch (Exception e) {
			Util.addErrorMessage("Falha ao buscar CEP. Digite os dados");
		}

	}

	public void verificarC() {
		PessoaJuridica pessoa = ((PessoaJuridicaRepository) getRepository()).findByCpnj(entity.getCnpj());

		if (pessoa != null) {
			Util.addErrorMessage("Já existe um registro cadastrado com esse CNPJ");
		}
	}
	
	public void telaGerenciaPessoaJuridica() {
		Util.redirect("gerencia-pessoajuridica.xhtml");
	}
	
	public void enviarObjetoParaSummary() {
		Session.getInstance().set("pessoajuridica", entity);
	}
}