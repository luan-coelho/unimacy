package br.unitins.unimacy.controller.listing;

import java.io.Serial;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;

import br.unitins.unimacy.application.Session;
import br.unitins.unimacy.application.Util;
import br.unitins.unimacy.exception.RepositoryException;
import br.unitins.unimacy.model.filtro.FiltroProduto;
import br.unitins.unimacy.model.produto.Produto;
import br.unitins.unimacy.model.venda.ProdutoVenda;
import br.unitins.unimacy.repository.produto.ProdutoRepository;
import jakarta.inject.Named;

@Named
@ViewScoped
public class ProdutoListingSql extends ListingSql<Produto> {

    @Serial
    private static final long serialVersionUID = 1L;

    private String pesquisa;
    private FiltroProduto filtro;

    public ProdutoListingSql() {
        super("produtolistingsql", new ProdutoRepository());
        this.pesquisa = (String) FacesContext.getCurrentInstance().getExternalContext().getFlash().get("pesquisaProduto");

        if (this.pesquisa != null) pesquisar();

        if (getList() == null) {
            ProdutoRepository repo = new ProdutoRepository();
            try {
                setList(repo.findByNomeNativeSql("").stream().filter(categoria -> (boolean) categoria[5]).toList());
            } catch (RepositoryException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    @Override
    public void pesquisar() {
        ProdutoRepository repo = new ProdutoRepository();
        try {
            setList(repo.findByNomeNativeSql(pesquisa).stream().filter(categoria -> (boolean) categoria[5]).toList());
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
        List<Object[]> listaProdutoAux = null;

        ProdutoRepository repo = new ProdutoRepository();

        if (this.pesquisa == null) this.pesquisa = "";

        switch (this.filtro) {
            case NOME -> {
                try {
                    listaProdutoAux = filtrarListaProduto(repo.findByNomeNativeSql(pesquisa));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            case CATEGORIA -> {
                try {
                    listaProdutoAux = filtrarListaProduto(repo.findByCategoriaNativeSql(pesquisa));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            case LOTE -> {
                try {
                    listaProdutoAux = filtrarListaProduto(repo.findByLoteNativeSql(pesquisa));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            case FORNECEDOR -> {
                try {
                    listaProdutoAux = filtrarListaProduto(repo.findByFornecedorNativeSql(pesquisa));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            default -> {
            }
        }

        setList(listaProdutoAux);
    }

    @SuppressWarnings("unchecked")
    public List<Object[]> filtrarListaProduto(List<Object[]> list) {
        List<ProdutoVenda> listaProdutoVenda = (List<ProdutoVenda>) Session.getInstance().get("listaProduto");
        Session.getInstance().set("listaProduto", null);

        if (listaProdutoVenda != null) {
            return list.stream().filter(produto -> {
                for (ProdutoVenda produtoVenda : listaProdutoVenda) {
                    if (produtoVenda.getProduto().getId() == produto[0]) {
                        return false;
                    }
                }
                return true;
            }).toList();
        }

        return list;
    }
}