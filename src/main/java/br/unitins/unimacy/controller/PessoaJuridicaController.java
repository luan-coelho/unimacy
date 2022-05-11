package br.unitins.unimacy.controller;

import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.unitins.unimacy.application.Session;
import br.unitins.unimacy.application.Util;
import br.unitins.unimacy.exception.RepositoryException;
import br.unitins.unimacy.model.filtro.FiltroPessoaJuridica;
import br.unitins.unimacy.model.pessoa.PessoaJuridica;
import br.unitins.unimacy.model.pessoa.Sexo;
import br.unitins.unimacy.model.pessoa.endereco.Cidade;
import br.unitins.unimacy.model.pessoa.endereco.Endereco;
import br.unitins.unimacy.model.pessoa.endereco.Estado;
import br.unitins.unimacy.repository.PessoaJuridicaRepository;

@Named
@ViewScoped
public class PessoaJuridicaController extends Controller<PessoaJuridica> {

	private static final long serialVersionUID = 4178893876749538318L;

	private List<PessoaJuridica> listaPessoaJuridica;

	private String pesquisa;
	private FiltroPessoaJuridica filtro = FiltroPessoaJuridica.NOME;

	public PessoaJuridicaController() {
		super(new PessoaJuridicaRepository());
	}

	@Override
	public PessoaJuridica getEntity() {
		if (entity == null) {
			entity = new PessoaJuridica();
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

	public FiltroPessoaJuridica getFiltro() {
		return filtro;
	}

	public void setFiltro(FiltroPessoaJuridica filtro) {
		this.filtro = filtro;
	}
	
	public FiltroPessoaJuridica[] getFiltroPessoaJuridica() {
		return FiltroPessoaJuridica.values();
	}

	public List<PessoaJuridica> getListaPessoaJuridica() {
		if (listaPessoaJuridica == null) {
			try {
				listaPessoaJuridica = getRepository().findAll();
			} catch (RepositoryException e) {
				Util.addErrorMessage("Falha ao buscar os dados no banco");
				e.printStackTrace();
			}
		}
		return listaPessoaJuridica;
	}

	public void setListaPessoaJuridica(List<PessoaJuridica> listaPessoaJuridica) {
		this.listaPessoaJuridica = listaPessoaJuridica;
	}

	@Override
	public void limpar() {
		super.limpar();
		listaPessoaJuridica = null;
	}

	public void onItemSelect() {
		String nomeEstado = entity.getEndereco().getCidade().getEstado().getNome();
		Session.getInstance().set("nome-estado", nomeEstado);
	}

	public void pesquisarPorNome(String nome) {
		try {
			getRepository().findByNome(nome);
		} catch (RepositoryException e) {
			e.printStackTrace();
		}
	}
	
	@Override
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
		listaPessoaJuridica = listaPessoaAux;
	}
	
	@Override
	public void editarItem(PessoaJuridica obj) {
		Session.getInstance().set("pessoajuridica-crud", obj);
		Util.redirect("pessoajuridica.xhtml");
	}
	
	@Override
	public void selecionarItem(PessoaJuridica obj) {
		Session.getInstance().set("pessoajuridica", obj);
		super.selecionarItem(obj);
	}

}