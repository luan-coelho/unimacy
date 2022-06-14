package br.unitins.unimacy.controller.listing;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.unitins.unimacy.exception.RepositoryException;
import br.unitins.unimacy.model.filtro.TipoCliente;
import br.unitins.unimacy.model.pessoa.Cliente;
import br.unitins.unimacy.repository.pessoa.ClienteRepository;

@Named
@ViewScoped
public class ClienteListing extends ListingSql<Cliente> {

	private static final long serialVersionUID = 1L;

	private String pesquisa;
	private TipoCliente tipo;

	public ClienteListing() {
		super("clientelisting", new ClienteRepository());
	}

	public TipoCliente[] getTipoCliente() {
		return TipoCliente.values();
	}
	
	@Override
	public void pesquisar() {
		ClienteRepository repo = new ClienteRepository();
		
		if(getTipo().equals(TipoCliente.PESSOA_FISICA)) {
			try {
				setList(repo.findAllClientePfByNomeNativeQuery(pesquisa));
			} catch (RepositoryException e) {
				e.printStackTrace();
			}
		}else {
			try {
				setList(repo.findAllClientePJByNomeNativeQuery(pesquisa));
			} catch (RepositoryException e) {
				e.printStackTrace();
			}
		}

	}
	
	public String getPesquisa() {
		return pesquisa;
	}

	public void setPesquisa(String pesquisa) {
		this.pesquisa = pesquisa;
	}

	public TipoCliente getTipo() {
		if(tipo == null)
			tipo = TipoCliente.PESSOA_FISICA;
		return tipo;
	}

	public void setTipo(TipoCliente tipo) {
		this.tipo = tipo;
	}

	

}