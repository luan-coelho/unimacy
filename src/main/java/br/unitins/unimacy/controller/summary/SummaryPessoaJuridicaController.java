package br.unitins.unimacy.controller.summary;


import br.unitins.unimacy.application.Session;
import br.unitins.unimacy.controller.Controller;
import br.unitins.unimacy.model.pessoa.PessoaJuridica;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;

import java.io.Serial;

@Named
@RequestScoped
public class SummaryPessoaJuridicaController extends Controller<PessoaJuridica> {

    @Serial
    private static final long serialVersionUID = 1L;

    public SummaryPessoaJuridicaController() {
        entity = (PessoaJuridica) Session.getInstance().get("pessoajuridica");
        Session.getInstance().set("pessoajuridica", null);
    }

    @Override
    public PessoaJuridica getEntity() {
        if (entity == null) entity = new PessoaJuridica();
        return entity;
    }


}