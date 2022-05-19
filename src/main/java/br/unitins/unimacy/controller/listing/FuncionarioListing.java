package br.unitins.unimacy.controller.listing;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.unitins.unimacy.application.Util;
import br.unitins.unimacy.exception.RepositoryException;
import br.unitins.unimacy.model.pessoa.Funcionario;
import br.unitins.unimacy.repository.pessoa.FuncionarioRepository;

@Named
@ViewScoped
public class FuncionarioListing extends Listing<Funcionario> {

	private static final long serialVersionUID = 7320538932717041448L;
	
	private String pesquisa;

	public FuncionarioListing() {
		super("funcionariolisting", new FuncionarioRepository());
	}

	@Override
	public void pesquisar() {
		FuncionarioRepository repo = new FuncionarioRepository();
		try {
			setList(repo.findAll());
		} catch (RepositoryException e) {
			e.printStackTrace();
			Util.addErrorMessage("Problema ao realizar a consulta.");
		}
	}

	public String getPesquisa() {
		return pesquisa;
	}

	public void setPesquisa(String pesquisa) {
		this.pesquisa = pesquisa;
	}
}