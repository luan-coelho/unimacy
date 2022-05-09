package br.unitins.unimacy.controller;

import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.unitins.unimacy.application.Session;
import br.unitins.unimacy.application.Util;
import br.unitins.unimacy.exception.RepositoryException;
import br.unitins.unimacy.model.Cidade;
import br.unitins.unimacy.model.Endereco;
import br.unitins.unimacy.model.Estado;
import br.unitins.unimacy.model.PessoaFisica;
import br.unitins.unimacy.model.Sexo;
import br.unitins.unimacy.model.filtro.FiltroPessoaFisica;
import br.unitins.unimacy.repository.PessoaFisicaRepository;

@Named
@ViewScoped
public class PessoaFisicaController extends Controller<PessoaFisica> {

	private static final long serialVersionUID = 962841755986708363L;

	private List<PessoaFisica> listaPessoaFisica;

	private String pesquisa;
	private FiltroPessoaFisica filtro = FiltroPessoaFisica.NOME;

	public PessoaFisicaController() {
		super(new PessoaFisicaRepository());
	}

	@Override
	public PessoaFisica getEntity() {
		if (entity == null) {
			entity = new PessoaFisica();
			entity.setEndereco(new Endereco(new Cidade(new Estado())));
		}

		return entity;
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

	public List<PessoaFisica> getListaPessoaFisica() {
		if (listaPessoaFisica == null) {
			try {
				listaPessoaFisica = getRepository().findAll();
			} catch (RepositoryException e) {
				Util.addErrorMessage("Falha ao buscar os dados no banco");
				e.printStackTrace();
			}
		}
		return listaPessoaFisica;
	}

	public void setListaPessoaFisica(List<PessoaFisica> listaPessoaFisica) {
		this.listaPessoaFisica = listaPessoaFisica;
	}

	@Override
	public void limpar() {
		super.limpar();
		listaPessoaFisica = null;
	}

	public void onItemSelect() {
		String nomeEstado = entity.getEndereco().getCidade().getEstado().getNome();
		Session.getInstance().set("nome-estado", nomeEstado);
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
		}case CPF: {
			try {
				listaPessoaAux = (List<PessoaFisica>) repo.findAllByCpf(pesquisa);
			} catch (RepositoryException e) {
				Util.addErrorMessage("Falha ao consultar CPF");
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
		listaPessoaFisica = listaPessoaAux;
	}
	
	@Override
	public void editarItem(PessoaFisica obj) {
		Session.getInstance().set("pessoafisica-crud", obj);
		Util.redirect("pessoafisica.xhtml");
	}
	
	@Override
	public void selecionarItem(PessoaFisica obj) {
		Session.getInstance().set("pessoafisica", obj);
		super.selecionarItem(obj);
	}
}