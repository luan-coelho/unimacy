package br.unitins.unimacy.controller;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import br.unitins.unimacy.application.Session;
import br.unitins.unimacy.application.Util;

@Named
@RequestScoped
public class TemplateController {

	public void encerrarSessao() {
		Session.getInstance().invalidateSession();
		Util.redirect("login.xhtml");
	}
}
