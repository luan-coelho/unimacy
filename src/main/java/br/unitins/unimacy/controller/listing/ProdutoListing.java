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
public class ProdutoListing extends Listing<Produto> {

    @Serial
    private static final long serialVersionUID = 1L;

    private String pesquisa;
    private FiltroProduto filtro;

    public ProdutoListing() {
        super("produtolisting", new ProdutoRepository());

        this.pesquisa = (String) FacesContext.getCurrentInstance().getExternalContext().getFlash().get("pesquisaProduto");

        pesquisar();
    }

    @Override
    public void pesquisar() {
        ProdutoRepository repo = new ProdutoRepository();
        try {
            setList(filtrarListaProduto(repo.findByNome(getPesquisa() == null ? "" : getPesquisa())));
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
            case NOME -> {
                try {
                    listaProdutoAux = filtrarListaProduto(repo.findByNome(pesquisa));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            case CATEGORIA -> {
                try {
                    listaProdutoAux = filtrarListaProduto(repo.findByCategoria(pesquisa));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            case LOTE -> {
                try {
                    listaProdutoAux = filtrarListaProduto(repo.findByLote(pesquisa));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            case FORNECEDOR -> {
                try {
                    listaProdutoAux = filtrarListaProduto(repo.findByFornecedor(pesquisa));
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
    public List<Produto> filtrarListaProduto(List<Produto> lista) {
        List<ProdutoVenda> listaProdutoVenda = (List<ProdutoVenda>) Session.getInstance().get("listaProduto");

        if (listaProdutoVenda != null) {
            return lista.stream().filter(produto -> {
                for (ProdutoVenda produtoVenda : listaProdutoVenda) {
                    if (produtoVenda.getProduto().equals(produto)) {
                        return false;
                    }
                }
                return true;
            }).toList();
        }

        return lista;
    }
}