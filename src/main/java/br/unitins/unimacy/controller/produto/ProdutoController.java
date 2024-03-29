package br.unitins.unimacy.controller.produto;

import java.io.Serial;
import java.util.List;

import javax.faces.view.ViewScoped;

import br.unitins.unimacy.application.Session;
import br.unitins.unimacy.application.Util;
import br.unitins.unimacy.controller.Controller;
import br.unitins.unimacy.exception.RepositoryException;
import br.unitins.unimacy.model.filtro.FiltroProduto;
import br.unitins.unimacy.model.pessoa.Fornecedor;
import br.unitins.unimacy.model.pessoa.PessoaJuridica;
import br.unitins.unimacy.model.produto.Produto;
import br.unitins.unimacy.model.produto.UnidadeMedida;
import br.unitins.unimacy.repository.produto.ProdutoRepository;
import jakarta.inject.Named;

@Named
@ViewScoped
public class ProdutoController extends Controller<Produto> {

    @Serial
    private static final long serialVersionUID = 1L;

    private List<Produto> listaProduto;

    private String pesquisa;
    private FiltroProduto filtro;

    public ProdutoController() {
        super(new ProdutoRepository());
    }

    @Override
    public Produto getEntity() {
        if (entity == null) {
            entity = new Produto();
            entity.setFornecedor(new Fornecedor(new PessoaJuridica()));
        }

        return entity;
    }

    public List<Produto> getListaProduto() {
        if (listaProduto == null) {
            try {
                listaProduto = getRepository().findAll();
            } catch (RepositoryException e) {
                e.printStackTrace();
            }
        }
        return listaProduto;
    }

    public void setListaProduto(List<Produto> listaProduto) {
        this.listaProduto = listaProduto;
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

    public UnidadeMedida[] getUnidadeMedida() {
        return UnidadeMedida.values();
    }


    @Override
    public void limpar() {
        super.limpar();
        listaProduto = null;
    }

    public void excluir(Produto produto) {
        entity = produto;
        super.excluir();
    }

    @Override
    public void pesquisaPorFiltro() {
        List<Produto> listaProdutoAux = null;

        ProdutoRepository repo = (ProdutoRepository) getRepository();

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
        listaProduto = listaProdutoAux;
    }

    public FiltroProduto[] getFiltroProduto() {
        return FiltroProduto.values();
    }

    @Override
    public void editarItem(Produto obj) {
        Session.getInstance().set("produto-crud", obj);
        Util.redirect("produto.xhtml");
    }

    @Override
    public void selecionarItem(Produto obj) {
        Session.getInstance().set("produto", obj);
        super.selecionarItem(obj);
    }
}