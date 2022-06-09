package br.unitins.unimacy.controller.pessoa;

import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.primefaces.event.SelectEvent;

import br.unitins.unimacy.application.Session;
import br.unitins.unimacy.application.Util;
import br.unitins.unimacy.controller.Controller;
import br.unitins.unimacy.controller.listing.PessoaJuridicaListing;
import br.unitins.unimacy.exception.RepositoryException;
import br.unitins.unimacy.model.filtro.FiltroPessoaJuridica;
import br.unitins.unimacy.model.pessoa.Fornecedor;
import br.unitins.unimacy.model.pessoa.PessoaJuridica;
import br.unitins.unimacy.repository.pessoa.FornecedorRepository;

@Named
@ViewScoped
public class FornecedorController extends Controller<Fornecedor> {

	private static final long serialVersionUID = -6638031078064686562L;

	private List<Fornecedor> listaFornecedor;

	private String pesquisa;
	private FiltroPessoaJuridica filtro = FiltroPessoaJuridica.NOME;
	
	private boolean ocultarDesativados;

	public FornecedorController() {
		super(new FornecedorRepository());
	}

	@Override
	public Fornecedor getEntity() {
		if (entity == null) {
			entity = new Fornecedor(new PessoaJuridica());
		}

		return entity;
	}

	public void abrirPessoaJuridicaListing() {
		PessoaJuridicaListing listing = new PessoaJuridicaListing();
		listing.open(90, 90);
	}

	public void obterPessoaJuridicaListing(SelectEvent<PessoaJuridica> event) {
		PessoaJuridica pj = event.getObject();
		FornecedorRepository repo = new FornecedorRepository();
		Fornecedor funcionario = null;
		try {
			funcionario = repo.findByIdPessoaJuridica(pj.getId());
		} catch (RepositoryException e) {
			e.printStackTrace();
		}
		if (funcionario != null)
			setEntity(funcionario);
		else {
			getEntity().setPessoaJuridica(pj);
			salvar();
		}
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

	public List<Fornecedor> getListaFornecedor() {
		if (listaFornecedor == null) {
			try {
				listaFornecedor = getRepository().findAll();
			} catch (RepositoryException e) {
				Util.addErrorMessage("Falha ao buscar os dados no banco");
				e.printStackTrace();
			}
		}
		return listaFornecedor;
	}

	public void setListaFornecedor(List<Fornecedor> listaFornecedor) {
		this.listaFornecedor = listaFornecedor;
	}

	public boolean isOcultarDesativados() {
		return ocultarDesativados;
	}

	public void setOcultarDesativados(boolean ocultarDesativados) {
		this.ocultarDesativados = ocultarDesativados;
	}
	
	@Override
	public void limpar() {
		super.limpar();
		listaFornecedor = null;
	}

	public void pesquisaPorFiltro() {
		List<Fornecedor> listaPessoaAux = null;

		FornecedorRepository repo = (FornecedorRepository) getRepository();

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
		case CNPJ: {
			try {
				listaPessoaAux = (List<Fornecedor>) repo.findAllByCnpj(pesquisa);
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
		listaFornecedor = listaPessoaAux;
	}

	@Override
	public void editarItem(Fornecedor obj) {
		Session.getInstance().set("fornecedor-crud", obj);
		Util.redirect("fornecedor.xhtml");
	}

	public void selecionarItem(PessoaJuridica obj) {
		Session.getInstance().set("fornecedor", obj);
	}
	
	public void ocultarDesativados() {
		if (this.ocultarDesativados) {
			try {
				setListaFornecedor(getRepository().findAll().stream()
						.filter(fornecedor -> fornecedor.getPessoaJuridica().isAtivo()).toList());
			} catch (RepositoryException e) {
				e.printStackTrace();
			}
		} else {
			limpar();
		}
	}
}