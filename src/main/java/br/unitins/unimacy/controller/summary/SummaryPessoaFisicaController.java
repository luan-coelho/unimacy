package br.unitins.unimacy.controller.summary;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import br.unitins.unimacy.application.Session;
import br.unitins.unimacy.controller.Controller;
import br.unitins.unimacy.model.pessoa.PessoaFisica;

@Named
@RequestScoped
public class SummaryPessoaFisicaController extends Controller<PessoaFisica> {

	private static final long serialVersionUID = -1830368911887420966L;

	public SummaryPessoaFisicaController() {
		entity = (PessoaFisica) Session.getInstance().get("pessoafisica");
		Session.getInstance().set("pessoafisica", null);
	}
	
	@Override
	public PessoaFisica getEntity() {
		if(entity == null)
			entity = new PessoaFisica();
		return entity;
	}


}