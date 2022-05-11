package br.unitins.unimacy.controller.venda;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.primefaces.model.DualListModel;

import br.unitins.unimacy.application.Util;
import br.unitins.unimacy.controller.Controller;
import br.unitins.unimacy.exception.RepositoryException;
import br.unitins.unimacy.model.filtro.FiltroProduto;
import br.unitins.unimacy.model.produto.Produto;
import br.unitins.unimacy.model.venda.ProdutoVenda;
import br.unitins.unimacy.model.venda.Venda;
import br.unitins.unimacy.repository.produto.ProdutoRepository;
import br.unitins.unimacy.repository.venda.VendaRepository;

@Named
@ViewScoped
public class VendaController extends Controller<Venda> {

	private static final long serialVersionUID = -2587172429280470098L;

	private List<ProdutoVenda> listaProdutoRepository;
	private List<ProdutoVenda> listaProdutoSelecionados;

	private DualListModel<ProdutoVenda> listaProdutoPickList;

	private BigDecimal valorTotal;

	private String pesquisa;
	private FiltroProduto filtro;

	public BigDecimal getValorTotal() {
		if (valorTotal == null)
			valorTotal = new BigDecimal(0);
		return valorTotal;
	}

	public void setValorTotal(BigDecimal valorTotal) {
		this.valorTotal = valorTotal;
	}

	public VendaController() {
		super(new VendaRepository());
	}

	@Override
	public Venda getEntity() {
		if (entity == null) {
			entity = new Venda();
		}
		return entity;
	}

	public List<ProdutoVenda> getListaProdutoRepository() {
		ProdutoRepository repo = new ProdutoRepository();

		if (listaProdutoRepository == null)
			try {
				listaProdutoRepository = new ArrayList<>();
				var listaAux = repo.findAll();
				listaAux.forEach(item -> listaProdutoRepository.add(new ProdutoVenda(item.getPreco(), 0, item)));
			} catch (RepositoryException e) {
				e.printStackTrace();
			}
		return listaProdutoRepository;
	}

	public void setListaProdutoRepository(List<ProdutoVenda> listaProdutoRepository) {
		this.listaProdutoRepository = listaProdutoRepository;
	}

	public List<ProdutoVenda> getListaProdutoSelecionados() {
		if (listaProdutoSelecionados == null)
			listaProdutoSelecionados = new ArrayList<>();
		return listaProdutoSelecionados;
	}

	public void setListaProdutoSelecionados(List<ProdutoVenda> listaProdutoSelecionados) {
		this.listaProdutoSelecionados = listaProdutoSelecionados;
	}

	public DualListModel<ProdutoVenda> getListaProdutoPickList() {
		if (listaProdutoPickList == null) {
			listaProdutoPickList = new DualListModel<>(getListaProdutoRepository(), getListaProdutoSelecionados());
		}
		return listaProdutoPickList;
	}

	public void setListaProdutoPickList(DualListModel<ProdutoVenda> listaProdutoPickList) {
		this.listaProdutoPickList = listaProdutoPickList;
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

	public void calcularValorTotal() {
		setValorTotal(new BigDecimal(0));

		List<ProdutoVenda> produtos = listaProdutoPickList.getTarget();
		
		for (Object obj : produtos) {
			Produto produto = (Produto) obj;
			valorTotal = produto.getPreco().add(valorTotal);
		}
	}

	@Override
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

		if (listaProdutoAux.isEmpty()) {
			Util.addWarnMessage("Nenhum produto encontrado");
			return;
		}

		List <ProdutoVenda> listaProdutoFiltro = new ArrayList<>();
		listaProdutoAux.forEach(item -> listaProdutoFiltro.add(new ProdutoVenda(item.getPreco(), 0, item)));
		listaProdutoPickList.setSource(listaProdutoFiltro);
	}

	@Override
	public void limpar() {
		getListaProdutoPickList().setSource(null);
	}

}