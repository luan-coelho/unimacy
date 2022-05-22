package br.unitins.unimacy.controller.listing;

import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.unitins.unimacy.application.Session;
import br.unitins.unimacy.application.Util;
import br.unitins.unimacy.exception.RepositoryException;
import br.unitins.unimacy.model.filtro.FiltroFuncionario;
import br.unitins.unimacy.model.pessoa.Funcionario;
import br.unitins.unimacy.repository.pessoa.FuncionarioRepository;

@Named
@ViewScoped
public class FuncionarioListing extends Listing<Funcionario> {

	private static final long serialVersionUID = 3158818791725149829L;

	private String pesquisa;
	private FiltroFuncionario filtro;

	public FuncionarioListing() {
		super("funcionariolisting", new FuncionarioRepository());
	}

	public String getPesquisa() {
		return pesquisa;
	}

	public void setPesquisa(String pesquisa) {
		this.pesquisa = pesquisa;
	}
	
	public FiltroFuncionario getFiltro() {
		return filtro;
	}

	public void setFiltro(FiltroFuncionario filtro) {
		this.filtro = filtro;
	}
	
	public FiltroFuncionario[] getFiltroFuncionario() {
		FiltroFuncionario[] filtro = {FiltroFuncionario.NOME, FiltroFuncionario.CPF};
		return filtro;
	}

	public void pesquisaPorFiltro() {
		List<Funcionario> listaPessoaAux = null;

		FuncionarioRepository repo = (FuncionarioRepository) getRepository();

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
				listaPessoaAux = (List<Funcionario>) repo.findAllByCpf(pesquisa);
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
		default:
			break;
		}

		if (listaPessoaAux.isEmpty()) {
			Util.addWarnMessage("Nenhum produto encontrado");
			return;
		}
		
		setList(listaPessoaAux);
	}
	
	public void selecionarItem(Funcionario obj) {
		Session.getInstance().set("funcionario", obj);
	}
}