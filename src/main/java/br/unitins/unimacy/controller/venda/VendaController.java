package br.unitins.unimacy.controller.venda;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.primefaces.event.SelectEvent;

import br.unitins.unimacy.application.Session;
import br.unitins.unimacy.application.Util;
import br.unitins.unimacy.controller.Controller;
import br.unitins.unimacy.controller.listing.FuncionarioListing;
import br.unitins.unimacy.controller.listing.ProdutoListingSql;
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

	private String pesquisa;

	private Integer etapaVenda;

	/* Tela de Pagamento */

	private BigDecimal valorPago;
	private BigDecimal valorTroco;

	public VendaController() {
		super(new VendaRepository());
	}

	public void calcularValorTotal() {
		setValorTotal(new BigDecimal(0));

		setListaProdutoVenda(getListaProdutoVenda().stream().filter(item -> item.getQuantidade() > 0).map(item -> {
			setValorTotal(item.getProduto().getPreco().multiply(BigDecimal.valueOf(item.getQuantidade()))
					.add(getValorTotal()));
			return item;
		}).collect(Collectors.toList()));
	}

	public void abrirProdutoListing() {
		ProdutoListingSql listing = new ProdutoListingSql();
		listing.open(70, 70);
		Session.getInstance().set("listaProduto", this.listaProdutoVenda);
		getListaProdutoRepository();
	}

	public void obterProdutoListing(SelectEvent<Produto> event) {
		Produto produto = event.getObject();

		getListaProdutoVenda().add(new ProdutoVenda(produto.getPreco(), 1, produto));

		calcularValorTotal();
	}

	public void abrirFuncionarioListing() {
		FuncionarioListing listing = new FuncionarioListing();
		listing.open(40, 70);
	}

	public void obterFuncionarioListing(SelectEvent<Funcionario> event) {
		setFuncionario(event.getObject());
	}

	public void pesquisar() {
		Flash flash = FacesContext.getCurrentInstance().getExternalContext().getFlash();
		if (this.pesquisa != null) {
			flash.put("pesquisaProduto", this.pesquisa);
			flash.keep("pesquisaProduto");

		}

		abrirProdutoListing();
	}
	
	public void calcularValorTroco() {
		if(getValorPago().compareTo(getValorTotal()) < 0)
			Util.addWarnMessage("Valor insuficiente");
		else {
			setValorTroco(getValorTotal().subtract(getValorPago()).abs());
		}
	}
	
	public void proximaEtapa() {
		if(etapaVenda == 0){
			etapaVenda = 1;
		}
	}
	
	public void voltarEtapa() {
		if(etapaVenda == 1){
			etapaVenda = 0;
		}
	}

	public void limpar() {
		funcionario = null;
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

	public BigDecimal getValorTotal() {
		if (valorTotal == null)
			valorTotal = new BigDecimal(0);
		return valorTotal;
	}

	public void setValorTotal(BigDecimal valorTotal) {
		this.valorTotal = valorTotal;
	}

	public String getPesquisa() {
		return pesquisa;
	}

	public void setPesquisa(String pesquisa) {
		this.pesquisa = pesquisa;
	}

	public Integer getEtapaVenda() {
		if (etapaVenda == null)
			etapaVenda = 0;
		return etapaVenda;
	}

	public void setEtapaVenda(Integer etapaVenda) {
		this.etapaVenda = etapaVenda;
	}

	public BigDecimal getValorPago() {
		if (valorPago == null)
			valorPago = new BigDecimal(0);
		return valorPago;
	}

	public void setValorPago(BigDecimal valorPago) {
		this.valorPago = valorPago;
	}

	public BigDecimal getValorTroco() {
		if (valorTroco == null)
			valorTroco = new BigDecimal(0);
		return valorTroco;
	}

	public void setValorTroco(BigDecimal valorTroco) {
		this.valorTroco = valorTroco;
	}

}