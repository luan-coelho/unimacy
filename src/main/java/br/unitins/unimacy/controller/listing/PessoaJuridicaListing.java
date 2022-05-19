package br.unitins.unimacy.controller.listing;

import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.unitins.unimacy.application.Util;
import br.unitins.unimacy.exception.RepositoryException;
import br.unitins.unimacy.model.filtro.FiltroPessoaJuridica;
import br.unitins.unimacy.model.pessoa.PessoaJuridica;
import br.unitins.unimacy.repository.pessoa.PessoaJuridicaRepository;

@Named
@ViewScoped
public class PessoaJuridicaListing extends Listing<PessoaJuridica> {

	private static final long serialVersionUID = 3158818791725149829L;

	private String pesquisa;
	private FiltroPessoaJuridica filtro;

	public PessoaJuridicaListing() {
		super("pessoajuridicalisting", new PessoaJuridicaRepository());
	}

	public String getPesquisa() {
		return pesquisa;
	}

	public void setPesquisa(String pesquisa) {
		this.pesquisa = pesquisa;
	}
	
	public FiltroPessoaJuridica getFiltro() {
		return filtro;
	}

	public void setFiltro(FiltroPessoaJuridica filtro) {
		this.filtro = filtro;
	}
	
	public FiltroPessoaJuridica[] getFiltroPessoaJuridica() {
		return FiltroPessoaJuridica.values();
	}

	public void pesquisaPorFiltro() {
		List<PessoaJuridica> listaPessoaAux = null;

		PessoaJuridicaRepository repo = (PessoaJuridicaRepository) getRepository();
		
		switch (filtro) {
		case NOME: {
			try {
				listaPessoaAux = repo.findAllByNome(pesquisa);
			} catch (RepositoryException e) {
				Util.addErrorMessage("Falha ao realizar consulta");
				e.printStackTrace();
			}
			break;
		}case CNPJ: {
			try {
				listaPessoaAux = (List<PessoaJuridica>) repo.findAllByCnpj(pesquisa);
			} catch (RepositoryException e) {
				Util.addErrorMessage("Falha ao consultar CNPJ");
				e.printStackTrace();
			}
			break;
		}case EMAIL: {
			try {
				listaPessoaAux = repo.findByEmail(pesquisa);
			} catch (RepositoryException e) {
				Util.addErrorMessage("Falha ao consultar email");
				e.printStackTrace();
			}
			break;
		}case TELEFONE: {
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