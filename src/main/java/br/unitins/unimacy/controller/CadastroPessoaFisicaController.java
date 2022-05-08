package br.unitins.unimacy.controller;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import com.gtbr.exception.ViaCepException;
import com.gtbr.exception.ViaCepFormatException;

import br.unitins.unimacy.application.ApiCep;
import br.unitins.unimacy.application.Util;
import br.unitins.unimacy.exception.RepositoryException;
import br.unitins.unimacy.model.Cidade;
import br.unitins.unimacy.model.Endereco;
import br.unitins.unimacy.model.Estado;
import br.unitins.unimacy.model.PessoaFisica;
import br.unitins.unimacy.model.Sexo;
import br.unitins.unimacy.repository.PessoaFisicaRepository;

@Named
@ViewScoped
public class CadastroPessoaFisicaController extends Controller<PessoaFisica> {

	private static final long serialVersionUID = 783180342902897686L;

	public CadastroPessoaFisicaController() {
		super(new PessoaFisicaRepository());
	}

	@Override
	public PessoaFisica getEntity() {
		if (entity == null) {
			entity = new PessoaFisica();
			entity.setEndereco(new Endereco(new Cidade(new Estado())));
		}

		return entity;
	}

	public Sexo[] getListaSexo() {
		return Sexo.values();
	}


	public void excluir(PessoaFisica PessoaFisica) {
		entity = PessoaFisica;
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

	public void pesquisarPorNome(String nome) {
		try {
			getRepository().findByNome(nome);
		} catch (RepositoryException e) {
			e.printStackTrace();
		}
	}

	public void verificarCpf() {
		PessoaFisica pessoa = ((PessoaFisicaRepository) getRepository()).findByCpf(entity.getCpf()); 

		if (pessoa != null) {
			Util.addErrorMessage("Já existe um registro cadastrado com esse CPF");
		}
	}
	
	public void telaGerenciaPessoaFisica() {
		Util.redirect("gerencia-pessoafisica.xhtml");
	}
}