package br.unitins.unimacy.controller.venda;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.primefaces.event.SelectEvent;

import br.unitins.unimacy.application.Session;
import br.unitins.unimacy.controller.Controller;
import br.unitins.unimacy.controller.listing.FuncionarioListing;
import br.unitins.unimacy.controller.listing.ProdutoListing;
import br.unitins.unimacy.exception.RepositoryException;
import br.unitins.unimacy.model.pessoa.Cliente;
import br.unitins.unimacy.model.pessoa.Funcionario;
import br.unitins.unimacy.model.produto.Produto;
import br.unitins.unimacy.model.venda.ProdutoVenda;
import br.unitins.unimacy.model.venda.Venda;
import br.unitins.unimacy.repository.produto.ProdutoRepository;
import br.unitins.unimacy.repository.venda.VendaRepository;

@Named
@ViewScoped
public class VendaController extends Controller<Venda> {

	private static final long serialVersionUID = -2587172429280470098L;

	private List<Produto> listaProdutoRepository;
	private List<ProdutoVenda> listaProdutoVenda;

	private Funcionario funcionario;
	private Cliente cliente;

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

	public List<ProdutoVenda> getListaProdutoVenda() {
		if (listaProdutoVenda == null)
			listaProdutoVenda = new ArrayList<>();
		return listaProdutoVenda;
	}

	public void setListaProdutoVenda(List<ProdutoVenda> listaProdutoVenda) {
		this.listaProdutoVenda = listaProdutoVenda;
	}

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	ProdutoVenda produtoVenda = null;

	public void calcularValorTotal() {
		setValorTotal(new BigDecimal(0));

		listaProdutoVenda.forEach(item -> {
			BigDecimal x = BigDecimal.valueOf(item.getQuantidade());
			valorTotal = item.getProduto().getPreco().multiply(x).add(valorTotal);

			if (item.getQuantidade() == 0) {
				produtoVenda = item;
			}
		});

		if (produtoVenda != null) {
			listaProdutoVenda.remove(produtoVenda);
			produtoVenda = null;
		}
	}

	public void abrirProdutoListing() {
		ProdutoListing listing = new ProdutoListing();
		listing.open("70", "70");
		Session.getInstance().set("listaProduto", this.listaProdutoVenda);
	}

	public void obterProdutoListing(SelectEvent<Produto> event) {
		Produto produto = event.getObject();

		getListaProdutoVenda().add(new ProdutoVenda(produto.getPreco(), 1, produto));

		calcularValorTotal();
	}

	public void abrirFuncionarioListing() {
		FuncionarioListing listing = new FuncionarioListing();
		listing.open("40", "70");
	}

	public void obterFuncionarioListing(SelectEvent<Funcionario> event) {
		setFuncionario(event.getObject());
	}
	
	
	public void limpar() {
		funcionario = null;
	}
}