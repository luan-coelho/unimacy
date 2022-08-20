package br.unitins.unimacy.controller.produto;

import br.unitins.unimacy.application.Session;
import br.unitins.unimacy.application.Util;
import br.unitins.unimacy.controller.Controller;
import br.unitins.unimacy.controller.listing.CategoriaListing;
import br.unitins.unimacy.controller.listing.FornecedorListing;
import br.unitins.unimacy.model.pessoa.Fornecedor;
import br.unitins.unimacy.model.pessoa.PessoaJuridica;
import br.unitins.unimacy.model.produto.Categoria;
import br.unitins.unimacy.model.produto.Produto;
import br.unitins.unimacy.model.produto.UnidadeMedida;
import br.unitins.unimacy.repository.produto.ProdutoRepository;
import jakarta.inject.Named;
import org.primefaces.event.SelectEvent;

import javax.faces.view.ViewScoped;
import java.io.Serial;
import java.util.ArrayList;

@Named
@ViewScoped
public class CadastroProdutoController extends Controller<Produto> {

    @Serial
    private static final long serialVersionUID = 1L;

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
            entity = new Produto(new ArrayList<>());
            entity.setFornecedor(new Fornecedor(new PessoaJuridica()));
        }

        return entity;
    }

    @Override
    public void salvar() {
        if (entity.getFornecedor().getId() == null) {
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

    public void abrirCategoriaListing() {
        CategoriaListing listing = new CategoriaListing();
        listing.open(70, 90);
    }

    public void obterCategoriaListing(SelectEvent<Categoria> event) {
        getEntity().getCategoria().add(event.getObject());
    }

    public void telaGerenciaProduto() {
        Util.redirect("gerencia-produto.xhtml");
    }

    public void enviarObjetoParaSummary() {
        Session.getInstance().set("produto", entity);
    }

    public void removerCategoria(Categoria categoria) {
        getEntity().getCategoria().remove(categoria);
    }
}