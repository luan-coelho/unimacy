package br.unitins.unimacy.controller.forgot;

import java.io.Serial;
import java.io.Serializable;


import br.unitins.unimacy.application.Session;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;

@Named
@RequestScoped
public class ForgotPasswordSucessController implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

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
