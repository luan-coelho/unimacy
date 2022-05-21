package br.unitins.unimacy.controller.pessoa;

import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.primefaces.event.SelectEvent;

import br.unitins.unimacy.application.Session;
import br.unitins.unimacy.application.Util;
import br.unitins.unimacy.controller.Controller;
import br.unitins.unimacy.controller.listing.PessoaFisicaListing;
import br.unitins.unimacy.exception.RepositoryException;
import br.unitins.unimacy.model.filtro.FiltroPessoaFisica;
import br.unitins.unimacy.model.pessoa.Funcionario;
import br.unitins.unimacy.model.pessoa.PessoaFisica;
import br.unitins.unimacy.model.pessoa.Sexo;
import br.unitins.unimacy.repository.pessoa.FuncionarioRepository;

@Named
@ViewScoped
public class FuncionarioController extends Controller<Funcionario> {

	private static final long serialVersionUID = 6941009744458527364L;

	private List<Funcionario> listaFuncionario;

	private String pesquisa;
	private FiltroPessoaFisica filtro = FiltroPessoaFisica.NOME;

	public FuncionarioController() {
		super(new FuncionarioRepository());
	}

	@Override
	public Funcionario getEntity() {
		if (entity == null) {
			entity = new Funcionario();
		}

		return entity;
	}

	public void abrirPessoaFisicaListing() {
		PessoaFisicaListing listing = new PessoaFisicaListing();
		listing.open("90", "90");
	}

	public void obterPessoaFisicaListing(SelectEvent<PessoaFisica> event) {
		PessoaFisica pf = event.getObject();
		FuncionarioRepository repo = new FuncionarioRepository();
		Funcionario funcionario = null;
		try {
			funcionario = repo.findByIdPessoaFisica(pf.getId());
		} catch (RepositoryException e) {
			e.printStackTrace();
		}
		if (funcionario != null)
			setEntity(funcionario);
		else {
			getEntity().setPessoaFisica(pf);
			salvar();
		}
	}

	public Sexo[] getListaSexo() {
		return Sexo.values();
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

	public List<Funcionario> getListaFuncionario() {
		if (listaFuncionario == null) {
			try {
				listaFuncionario = getRepository().findAll();
			} catch (RepositoryException e) {
				Util.addErrorMessage("Falha ao buscar os dados no banco");
				e.printStackTrace();
			}
		}
		return listaFuncionario;
	}

	public void setListaFuncionario(List<Funcionario> listaFuncionario) {
		this.listaFuncionario = listaFuncionario;
	}

	@Override
	public void limpar() {
		super.limpar();
		listaFuncionario = null;
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
		listaFuncionario = listaPessoaAux;
	}

	@Override
	public void editarItem(Funcionario obj) {
		Session.getInstance().set("funcionario-crud", obj);
		Util.redirect("funcionario.xhtml");
	}

	public void selecionarItem(PessoaFisica obj) {
		Session.getInstance().set("pessoafisica", obj);
	}
}