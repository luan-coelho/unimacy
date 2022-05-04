package br.unitins.unimacy.application;

import java.io.BufferedReader;
import java.io.IOException;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;

public class Util {

	private static Flash flash;

	public static Flash getFlashScope() {
		if (flash == null) {
			flash = FacesContext.getCurrentInstance().getExternalContext().getFlash();
		}

		return flash;
	}

	public static void redirect(String page) {
		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect(page);
		} catch (IOException e) {
			System.out.println("Não foi possível realizar o redirecionamento.");
			e.printStackTrace();
		}
	}

	private static void addMessage(String msg, Severity severity) {
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(severity, msg, null));
	}

	public static void addErrorMessage(String msg) {
		addMessage(msg, FacesMessage.SEVERITY_ERROR);
	}

	public static void addWarnMessage(String msg) {
		addMessage(msg, FacesMessage.SEVERITY_WARN);
	}

	public static void addInfoMessage(String msg) {
		addMessage(msg, FacesMessage.SEVERITY_INFO);
	}

	public static String converteJsonEmString(BufferedReader buffereReader) throws IOException {
		String resposta, jsonEmString = "";
		while ((resposta = buffereReader.readLine()) != null) {
			jsonEmString += resposta;
		}
		return jsonEmString;
	}
}