package br.unitins.unimacy.controller;

import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.unitins.unimacy.application.Session;
import br.unitins.unimacy.application.Util;
import br.unitins.unimacy.exception.RepositoryException;
import br.unitins.unimacy.model.Fornecedor;
import br.unitins.unimacy.model.PessoaJuridica;
import br.unitins.unimacy.model.Produto;
import br.unitins.unimacy.model.UnidadeMedida;
import br.unitins.unimacy.model.filtro.FiltroProduto;
import br.unitins.unimacy.repository.ProdutoRepository;

@Named
@ViewScoped
public class ProdutoController extends Controller<Produto> {

	private static final long serialVersionUID = -1330527356831135672L;

	private List<Produto> listaProduto;

	private String pesquisa;
	private FiltroProduto filtro = FiltroProduto.NOME;

	public ProdutoController() {
		super(new ProdutoRepository());
	}

	@Override
	public Produto getEntity() {
		if (entity == null) {
			entity = new Produto();
			entity.setFornecedor(new Fornecedor(new PessoaJuridica()));
		}

		return entity;
	}

	public List<Produto> getListaProduto() {
		if (listaProduto == null) {
			try {
				listaProduto = getRepository().findAll();
			} catch (RepositoryException e) {
				e.printStackTrace();
			}
		}
		return listaProduto;
	}

	public void setListaProduto(List<Produto> listaProduto) {
		this.listaProduto = listaProduto;
	}

	public String getPesquisa() {
		return pesquisa;
	}

	public void setPesquisa(String pesquisa) {
		this.pesquisa = pesquisa;
	}

	public FiltroProduto getFiltro() {
		return filtro;
	}

	public void setFiltro(FiltroProduto filtro) {
		this.filtro = filtro;
	}
	
	public UnidadeMedida[] getUnidadeMedida() {
		return UnidadeMedida.values();
	}

	
	@Override
	public void limpar() {
		super.limpar();
		listaProduto = null;
	}

	public void excluir(Produto produto) {
		entity = produto;
		super.excluir();
	}

	public void pesquisar() {
		List<Produto> listaProdutoAux = null;

		ProdutoRepository repo = (ProdutoRepository) getRepository();

		switch (this.filtro) {
		case NOME: {
			try {
				listaProdutoAux = repo.findByNome(this.pesquisa);
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
		}
		case CATEGORIA: {
			try {
				listaProdutoAux = repo.findByCategoria(this.pesquisa);
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
		}
		case LOTE: {
			try {
				listaProdutoAux = repo.findByLote(this.pesquisa);
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
		}
		case FORNECEDOR: {
			try {
				listaProdutoAux = repo.findByFornecedor(this.pesquisa);
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
		}
		default:
			break;
		}

		if (listaProdutoAux == null) {
			Util.addInfoMessage("Nenhum produto encontrado");
			return;
		}
		listaProduto = listaProdutoAux;
	}

	public FiltroProduto[] getFiltroProduto() {
		return FiltroProduto.values();
	}

	@Override
	public void editarItem(Produto obj) {
		Session.getInstance().set("produto-crud", obj);
		Util.redirect("produto.xhtml");
	}
}