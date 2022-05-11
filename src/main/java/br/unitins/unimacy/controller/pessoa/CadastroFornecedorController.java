package br.unitins.unimacy.controller.pessoa;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.unitins.unimacy.application.Util;
import br.unitins.unimacy.controller.Controller;
import br.unitins.unimacy.model.pessoa.Fornecedor;
import br.unitins.unimacy.model.pessoa.PessoaJuridica;
import br.unitins.unimacy.repository.pessoa.FornecedorRepository;

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