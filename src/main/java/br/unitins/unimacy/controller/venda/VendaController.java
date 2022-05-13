package br.unitins.unimacy.controller.venda;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.primefaces.event.SelectEvent;

import br.unitins.unimacy.controller.Controller;
import br.unitins.unimacy.controller.listing.ProdutoListing;
import br.unitins.unimacy.exception.RepositoryException;
import br.unitins.unimacy.model.produto.Produto;
import br.unitins.unimacy.model.venda.Venda;
import br.unitins.unimacy.repository.produto.ProdutoRepository;
import br.unitins.unimacy.repository.venda.VendaRepository;

@Named
@ViewScoped
public class VendaController extends Controller<Venda> {

	private static final long serialVersionUID = -2587172429280470098L;

	private List<Produto> listaProdutoRepository;
	private List<Produto> listaProdutoSelecionados;

	private BigDecimal valorTotal;

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

	public List<Produto> getListaProdutoRepository() {
		ProdutoRepository repo = new ProdutoRepository();

		if (listaProdutoRepository == null)
			try {
				listaProdutoRepository = repo.findAll();
			} catch (RepositoryException e) {
				e.printStackTrace();
			}
		return listaProdutoRepository;
	}

	public void setListaProdutoRepository(List<Produto> listaProdutoRepository) {
		this.listaProdutoRepository = listaProdutoRepository;
	}

	public List<Produto> getListaProdutoSelecionados() {
		if (listaProdutoSelecionados == null)
			listaProdutoSelecionados = new ArrayList<>();
		return listaProdutoSelecionados;
	}

	public void setListaProdutoSelecionados(List<Produto> listaProdutoSelecionados) {
		this.listaProdutoSelecionados = listaProdutoSelecionados;
	}

	public void calcularValorTotal() {
		setValorTotal(new BigDecimal(0));

		listaProdutoSelecionados.forEach(item -> valorTotal = item.getPreco().add(valorTotal));
	}
	
	public void abrirProdutoListing() {
		ProdutoListing listing = new ProdutoListing();
		listing.open("70", "70");
	}

	public void obterProdutoListing(SelectEvent<Produto> event) {
		getListaProdutoSelecionados().add(event.getObject());
		calcularValorTotal();
	}
}