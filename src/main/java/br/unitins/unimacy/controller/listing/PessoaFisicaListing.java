package br.unitins.unimacy.controller.listing;

import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.unitins.unimacy.application.Util;
import br.unitins.unimacy.exception.RepositoryException;
import br.unitins.unimacy.model.filtro.FiltroPessoaFisica;
import br.unitins.unimacy.model.pessoa.PessoaFisica;
import br.unitins.unimacy.repository.pessoa.PessoaFisicaRepository;

@Named
@ViewScoped
public class PessoaFisicaListing extends Listing<PessoaFisica> {

	private static final long serialVersionUID = 3158818791725149829L;

	private String pesquisa;
	private FiltroPessoaFisica filtro;

	public PessoaFisicaListing() {
		super("pessoafisicalisting", new PessoaFisicaRepository());
	}

	public String getPesquisa() {
		return pesquisa;
	}

	public void setPesquisa(String pesquisa) {
		this.pesquisa = pesquisa;
	}
	
	public FiltroPessoaFisica getFiltro() {
		return filtro;
	}

	public void setFiltro(FiltroPessoaFisica filtro) {
		this.filtro = filtro;
	}
	
	public FiltroPessoaFisica[] getFiltroPessoaFisica() {
		return FiltroPessoaFisica.values();
	}

	public void pesquisaPorFiltro() {
		List<PessoaFisica> listaPessoaAux = null;

		PessoaFisicaRepository repo = (PessoaFisicaRepository) getRepository();

		switch (filtro) {
		case NOME: {
			try {
				listaPessoaAux = repo.findByNome(pesquisa);
			} catch (RepositoryException e) {
				Util.addErrorMessage("Falha ao realizar consulta");
				e.printStackTrace();
			}
			break;
		}
		case CPF: {
			try {
				listaPessoaAux = (List<PessoaFisica>) repo.findAllByCpf(pesquisa);
			} catch (RepositoryException e) {
				Util.addErrorMessage("Falha ao consultar CPF");
				e.printStackTrace();
			}
			break;
		}
		case EMAIL: {
			try {
				listaPessoaAux = repo.findByEmail(pesquisa);
			} catch (RepositoryException e) {
				Util.addErrorMessage("Falha ao consultar email");
				e.printStackTrace();
			}
			break;
		}
		case TELEFONE: {
			try {
				listaPessoaAux = repo.findByTelefone(pesquisa);
			} catch (RepositoryException e) {
				Util.addErrorMessage("Falha ao consultar telefone");
				e.printStackTrace();
			}
			break;
		}
		default:
			break;
		}

		if (listaPessoaAux.isEmpty()) {
			Util.addWarnMessage("Nenhum produto encontrado");
			return;
		}
		
		setList(listaPessoaAux);
	}
}