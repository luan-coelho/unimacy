package br.unitins.unimacy.controller.produto;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.primefaces.event.SelectEvent;

import br.unitins.unimacy.application.Session;
import br.unitins.unimacy.application.Util;
import br.unitins.unimacy.controller.Controller;
import br.unitins.unimacy.controller.listing.FornecedorListing;
import br.unitins.unimacy.model.pessoa.Fornecedor;
import br.unitins.unimacy.model.pessoa.PessoaJuridica;
import br.unitins.unimacy.model.produto.Produto;
import br.unitins.unimacy.model.produto.UnidadeMedida;
import br.unitins.unimacy.repository.produto.ProdutoRepository;

@Named
@ViewScoped
public class CadastroProdutoController extends Controller<Produto> {

	private static final long serialVersionUID = -7142430473023627800L;

	@Override
	public void setEntity(Object obj) {
		// TODO Auto-generated method stub
		super.setEntity(obj);
	}
	
	public CadastroProdutoController() {
		super(new ProdutoRepository());
		entity = (Produto) Session.getInstance().get("produto-crud");
		Session.getInstance().set("produto-crud", null);
	}

	@Override
	public Produto getEntity() {
		if (entity == null) {
			entity = new Produto();
			entity.setFornecedor(new Fornecedor(new PessoaJuridica()));
		}

		return entity;
	}

	@Override
	public void salvar() {
		if(entity.getFornecedor().getId() == null) {
			Util.addWarnMessage("Selecione um fornecedor para este produto");
			return;
		}
			
		super.salvar();
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
	
	public void telaGerenciaProduto() {
		Util.redirect("gerencia-produto.xhtml");
	}
	
	public void enviarObjetoParaSummary() {
		Session.getInstance().set("produto", entity);
	}
}