package br.unitins.unimacy.controller.venda;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

import javax.faces.view.ViewScoped;

import br.unitins.unimacy.application.Util;
import br.unitins.unimacy.exception.RepositoryException;
import br.unitins.unimacy.repository.venda.VendaRepository;
import jakarta.inject.Named;

@Named
@ViewScoped
public class RegistroVendaController implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private List<Object[]> listaVenda;

    public void gerarRelatorio() {
        Util.redirect("/Unimacy/relatoriovendas.xhtml");
    }

    public List<Object[]> getListaVenda() {
        if (listaVenda == null) {
            VendaRepository repo = new VendaRepository();
            try {
                listaVenda = repo.findAllNativeSql();
            } catch (RepositoryException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return listaVenda;
    }

    public void setListaVenda(List<Object[]> listaVenda) {
        this.listaVenda = listaVenda;
    }
}
