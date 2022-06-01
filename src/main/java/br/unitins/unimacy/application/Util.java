package br.unitins.unimacy.application;

import java.io.BufferedReader;
import java.io.IOException;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.FacesContext;

import org.apache.commons.codec.digest.DigestUtils;

import br.unitins.unimacy.model.pessoa.Funcionario;

public class Util {

	// Método que define como será a composição da senha
	public static String hash(Funcionario funcionario) {
		return hash(funcionario.getPessoaFisica().getCpf() + funcionario.getSenha());
	}

	public static String hash(String cpf, String senha) {
		return hash(cpf + senha);
	}

	// Criptografa a senha
	public static String hash(String valor) {
		return DigestUtils.sha256Hex(valor);
	}

	public static void redirect(String page) {
		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect(page);
		} catch (IOException e) {
			System.out.println("Não foi possível realizar o redirecionamento.");
			e.printStackTrace();
		}
	}

	private static void addMessage(String title, String msg, Severity severity) {
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(severity, title, msg));
	}

	private static void addMessage(String title, Severity severity) {
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(severity, title, null));
	}

	public static void addInfoMessage(String title, String msg) {
		addMessage(title, msg, FacesMessage.SEVERITY_INFO);
	}

	public static void addErrorMessage(String title, String msg) {
		addMessage(title, msg, FacesMessage.SEVERITY_ERROR);
	}

	public static void addWarnMessage(String title, String msg) {
		addMessage(title, msg, FacesMessage.SEVERITY_WARN);
	}

	public static void addInfoMessage(String title) {
		addMessage(title, FacesMessage.SEVERITY_INFO);
	}

	public static void addErrorMessage(String title) {
		addMessage(title, FacesMessage.SEVERITY_ERROR);
	}

	public static void addWarnMessage(String title) {
		addMessage(title, FacesMessage.SEVERITY_WARN);
	}

	public static String converteJsonEmString(BufferedReader buffereReader) throws IOException {
		String resposta, jsonEmString = "";
		while ((resposta = buffereReader.readLine()) != null) {
			jsonEmString += resposta;
		}
		return jsonEmString;
	}
}