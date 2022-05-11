package br.unitins.unimacy.controller.summary;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import br.unitins.unimacy.application.Session;
import br.unitins.unimacy.controller.Controller;
import br.unitins.unimacy.model.pessoa.PessoaJuridica;

@Named
@RequestScoped
public class SummaryPessoaJuridicaController extends Controller<PessoaJuridica> {

	private static final long serialVersionUID = -1830368911887420966L;

	public SummaryPessoaJuridicaController() {
		entity = (PessoaJuridica) Session.getInstance().get("pessoajuridica");
		Session.getInstance().set("pessoajuridica", null);
	}
	
	@Override
	public PessoaJuridica getEntity() {
		if(entity == null)
			entity = new PessoaJuridica();
		return entity;
	}


}