package br.unitins.unimacy.controller.venda;

import java.io.Serializable;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.unitins.unimacy.application.Util;
import br.unitins.unimacy.exception.RepositoryException;
import br.unitins.unimacy.repository.venda.VendaRepository;

@Named
@ViewScoped
public class RegistroVendaController implements Serializable {

	private static final long serialVersionUID = 1L;

	private List<Object[]> listaVenda;

	public void gerarRelatorio() {
		Util.redirect("/Unimacy/relatoriovendas.xhtml");
	}

	public List<Object[]> getListaVenda() {
		if(listaVenda == null) {
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
