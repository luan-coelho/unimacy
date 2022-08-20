package br.unitins.unimacy.controller;

import java.io.Serial;
import java.io.Serializable;

import javax.inject.Named;

import br.unitins.unimacy.application.Session;
import br.unitins.unimacy.application.Util;
import br.unitins.unimacy.model.pessoa.Cargo;

@Named
@javax.faces.view.ViewScoped
public class TemplateController implements Serializable{

	@Serial
	private static final long serialVersionUID = 1L;
	
	private boolean isAdmin;
	
	public TemplateController() {
		if(Session.getInstance().get("cargoFuncionario").equals(Cargo.ADMINISTRADOR)) {
			this.isAdmin = true;
		}
	}

	public void encerrarSessao() {
		Session.getInstance().invalidateSession();
		Util.redirect("../login.xhtml");
	}

	public boolean isAdmin() {
		return isAdmin;
	}

	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}
}
