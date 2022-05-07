package br.unitins.unimacy.controller;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.unitins.unimacy.application.Util;
import br.unitins.unimacy.model.Fornecedor;
import br.unitins.unimacy.model.PessoaJuridica;
import br.unitins.unimacy.repository.FornecedorRepository;

@Named
@ViewScoped
public class CadastroFornecedorController extends Controller<Fornecedor> {

	private static final long serialVersionUID = -4319197976637132429L;

	public CadastroFornecedorController() {
		super(new FornecedorRepository());
	}

	@Override
	public Fornecedor getEntity() {
		if (entity == null) {
			entity = new Fornecedor();
			entity.setPessoaJuridica(new PessoaJuridica());
		}

		return entity;
	}
	
	public void telaGerenciaFornecedor() {
		Util.redirect("fornecedores.xhtml");
	}

}