package br.unitins.unimacy.controller;

import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.unitins.unimacy.exception.RepositoryException;
import br.unitins.unimacy.model.Categoria;
import br.unitins.unimacy.repository.CategoriaRepository;

@Named
@ViewScoped
public class CategoriaController extends Controller<Categoria> {

	private static final long serialVersionUID = -2587172429280470098L;

	public CategoriaController() {
		super(new CategoriaRepository());
	}

	public List<Categoria> getListaCategoria() {
		List<Categoria> listaCategoria = null;

		try {
			listaCategoria = getRepository().findAll();
		} catch (RepositoryException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return listaCategoria;
	}

	@Override
	public Categoria getEntity() {
		if (entity == null) {
			entity = new Categoria();
		}

		return entity;
	}

	public void excluir(Categoria categoria) {
		entity = categoria;
		super.excluir();
	}

	public void selecionarItem(Categoria categoria) {
		this.entity = categoria;
	}

}