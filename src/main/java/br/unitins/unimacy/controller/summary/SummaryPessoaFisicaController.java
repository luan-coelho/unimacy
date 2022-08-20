package br.unitins.unimacy.controller.summary;

import br.unitins.unimacy.application.Session;
import br.unitins.unimacy.controller.Controller;
import br.unitins.unimacy.model.pessoa.PessoaFisica;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;

import java.io.Serial;

@Named
@RequestScoped
public class SummaryPessoaFisicaController extends Controller<PessoaFisica> {

    @Serial
    private static final long serialVersionUID = 1L;

    public SummaryPessoaFisicaController() {
        entity = (PessoaFisica) Session.getInstance().get("pessoafisica");
        Session.getInstance().set("pessoafisica", null);
    }

    @Override
    public PessoaFisica getEntity() {
        if (entity == null) entity = new PessoaFisica();
        return entity;
    }


}