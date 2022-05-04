package br.unitins.unimacy.controller;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.primefaces.event.SelectEvent;

import br.unitins.unimacy.application.Session;
import br.unitins.unimacy.application.Util;
import br.unitins.unimacy.controller.listing.FornecedorListing;
import br.unitins.unimacy.model.Fornecedor;
import br.unitins.unimacy.model.PessoaJuridica;
import br.unitins.unimacy.model.Produto;
import br.unitins.unimacy.model.UnidadeMedida;
import br.unitins.unimacy.repository.ProdutoRepository;

@Named
@ViewScoped
public class CadastroProdutoController extends Controller<Produto> {

	private static final long serialVersionUID = -2042049091495562859L;

	public CadastroProdutoController() {
		super(new ProdutoRepository());
		entity = (Produto) Session.getInstance().get("produto-crud");
		Session.getInstance().invalidateSession();
	}

	@Override
	public Produto getEntity() {
		if (entity == null) {
			entity = new Produto();
			entity.setFornecedor(new Fornecedor(new PessoaJuridica()));
		}

		return entity;
	}

	public UnidadeMedida[] getUnidadeMedida() {
		return UnidadeMedida.values();
	}

	public void abrirFornecedorListing() {
		FornecedorListing listing = new FornecedorListing();
		listing.open();
	}

	public void obterFornecedorListing(SelectEvent<Fornecedor> event) {
		getEntity().setFornecedor(event.getObject());
	}
	
	public void telaGerenciaProdutos() {
		Util.redirect("produtos.xhtml");
		System.out.println("Entrou na carniça");
	}
}