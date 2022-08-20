package br.unitins.unimacy.controller.produto;

import java.io.Serial;
import java.util.List;

import javax.faces.view.ViewScoped;

import br.unitins.unimacy.controller.Controller;
import br.unitins.unimacy.exception.RepositoryException;
import br.unitins.unimacy.model.produto.Categoria;
import br.unitins.unimacy.repository.produto.CategoriaRepository;
import jakarta.inject.Named;

@Named
@ViewScoped
public class CategoriaController extends Controller<Categoria> {

    @Serial
    private static final long serialVersionUID = 1L;

    private List<Categoria> listaCategoria;

    public CategoriaController() {
        super(new CategoriaRepository());
    }

    @Override
    public void salvar() {
        super.salvar();
        setListaCategoria(null);
    }

    public List<Categoria> getListaCategoria() {
        if (listaCategoria == null) {
            try {
                listaCategoria = getRepository().findAll();
            } catch (RepositoryException e) {
                e.printStackTrace();
            }
        }

        return listaCategoria;
    }

    public void setListaCategoria(List<Categoria> listaCategoria) {
        this.listaCategoria = listaCategoria;
    }

    @Override
    public Categoria getEntity() {
        if (entity == null) {
            entity = new Categoria();
        }

        return entity;
    }

    public void selecionarItem(Categoria categoria) {
        this.entity = categoria;
    }
}