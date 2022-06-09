package br.unitins.unimacy.controller.listing;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.unitins.unimacy.application.Util;
import br.unitins.unimacy.exception.RepositoryException;
import br.unitins.unimacy.model.produto.Categoria;
import br.unitins.unimacy.repository.produto.CategoriaRepository;

@Named
@ViewScoped
public class CategoriaListing extends ListingSql<Categoria> {

	private static final long serialVersionUID = 1L;

	private String pesquisa;

	public CategoriaListing() {
		super("categorialisting", new CategoriaRepository());
	}

	@Override
	public void pesquisar() {
		CategoriaRepository repo = new CategoriaRepository();
		try {
			setList(repo.findByNomeNativeSql(pesquisa));
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