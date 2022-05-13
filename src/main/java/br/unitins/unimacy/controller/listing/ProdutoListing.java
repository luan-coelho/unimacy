package br.unitins.unimacy.controller.listing;

import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.unitins.unimacy.application.Util;
import br.unitins.unimacy.exception.RepositoryException;
import br.unitins.unimacy.model.filtro.FiltroProduto;
import br.unitins.unimacy.model.produto.Produto;
import br.unitins.unimacy.repository.produto.ProdutoRepository;

@Named
@ViewScoped
public class ProdutoListing extends Listing<Produto> {

	private static final long serialVersionUID = 6565560689435238952L;
	
	private String pesquisa;
	private FiltroProduto filtro;

	public ProdutoListing() {
		super("produtolisting", new ProdutoRepository());
	}

	@Override
	public void pesquisar() {
		ProdutoRepository repo = new ProdutoRepository();
		try {
			setList(repo.findAll());
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

	public FiltroProduto getFiltro() {
		if (filtro == null) {
			filtro = FiltroProduto.NOME;
		}
		return filtro;
	}

	public void setFiltro(FiltroProduto filtro) {
		this.filtro = filtro;
	}

	public FiltroProduto[] getFiltroProduto() {
		return FiltroProduto.values();
	}
	
	public void pesquisaPorFiltro() {
		List<Produto> listaProdutoAux = null;

		ProdutoRepository repo = new ProdutoRepository();

		switch (this.filtro) {
		case NOME: {
			try {
				listaProdutoAux = repo.findByNome(pesquisa);
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
		}
		case CATEGORIA: {
			try {
				listaProdutoAux = repo.findByCategoria(pesquisa);
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
		}
		case LOTE: {
			try {
				listaProdutoAux = repo.findByLote(pesquisa);
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
		}
		case FORNECEDOR: {
			try {
				listaProdutoAux = repo.findByFornecedor(pesquisa);
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
		}
		default:
			break;
		}

		setList(listaProdutoAux);
	}
	
}