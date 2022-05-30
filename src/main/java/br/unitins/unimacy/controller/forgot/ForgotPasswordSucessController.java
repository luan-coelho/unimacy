package br.unitins.unimacy.controller.forgot;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import br.unitins.unimacy.application.Session;

@Named
@RequestScoped
public class ForgotPasswordSucessController implements Serializable {

	private static final long serialVersionUID = -4750261338101378803L;
	
	private String email;
	
	public ForgotPasswordSucessController() {
		this.email = (String) Session.getInstance().get("email-fp");
		Session.getInstance().set("email-fp", null);
	}


	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
