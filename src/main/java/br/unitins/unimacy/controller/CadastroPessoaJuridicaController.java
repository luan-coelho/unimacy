package br.unitins.unimacy.controller;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import com.gtbr.exception.ViaCepException;
import com.gtbr.exception.ViaCepFormatException;

import br.unitins.unimacy.application.ApiCep;
import br.unitins.unimacy.application.Util;
import br.unitins.unimacy.model.Cidade;
import br.unitins.unimacy.model.Endereco;
import br.unitins.unimacy.model.Estado;
import br.unitins.unimacy.model.PessoaJuridica;
import br.unitins.unimacy.repository.PessoaJuridicaRepository;

@Named
@ViewScoped
public class CadastroPessoaJuridicaController extends Controller<PessoaJuridica> {

	private static final long serialVersionUID = -3377591025104822813L;

	public CadastroPessoaJuridicaController() {
		super(new PessoaJuridicaRepository());
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
}