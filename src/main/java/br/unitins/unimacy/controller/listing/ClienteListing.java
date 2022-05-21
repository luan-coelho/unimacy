package br.unitins.unimacy.controller.listing;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.unitins.unimacy.model.pessoa.Cliente;
import br.unitins.unimacy.repository.Repository;

@Named
@ViewScoped
public class ClienteListing extends Listing<Cliente> {

	public ClienteListing(String page, Repository<Cliente> repository) {
		super(page, repository);
		// TODO Auto-generated constructor stub
	}

	private static final long serialVersionUID = 4450230216974274166L;


}