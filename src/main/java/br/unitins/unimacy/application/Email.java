package br.unitins.unimacy.application;

import java.io.InputStream;
import java.util.Properties;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Email {
	private String usuario;
	private String senha;
	private String assunto;
	private String emailDestino;
	private String codigo;

	public Email(String emailDestino, String assunto, String codigo) {
		this.emailDestino = emailDestino;
		this.assunto = assunto;
		this.codigo = codigo;

		Properties prop = new Properties();
		try (InputStream is = this.getClass().getResourceAsStream("/config/email.properties")) {
			prop.load(is);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("\n\n\n\n NÃO FOI ENCONTRADO O ARQUIVO email.properties na pasta config. \n\n\n\n");
		}

		setUsuario(prop.getProperty("usuario"));
		setSenha(prop.getProperty("senha"));
	}

	public boolean enviar() {

		Properties prop = new Properties();
		prop.put("mail.smtp.host", "smtp.gmail.com");
		prop.put("mail.smtp.port", "587");
		prop.put("mail.smtp.auth", "true");
		prop.put("mail.smtp.starttls.enable", "true"); // TLS

		Session session = Session.getInstance(prop, new jakarta.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(getUsuario(), getSenha());
			}
		});
		try {

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(getUsuario()));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(getEmailDestino()));
			message.setSubject(getAssunto());

			Path path = Paths.get(
					"C:\\Users\\Luan Coêlho\\Development\\Workspace\\unimacy\\src\\main\\webapp\\BodyForgotPassword.html");

			try {
				String content = Files.readString(path);
				content = content.replace("T-000000", getCodigo());
				content = content.replace("CODIGO", getCodigo());
				// [T]{1}+[-]{1}+[0-9]{6}
				message.setContent(content, "text/html");
			} catch (IOException e) {
				e.printStackTrace();
			}

			// enviando o email
			Transport.send(message);
			return true;
		} catch (MessagingException e) {
			e.printStackTrace();
			return false;
		}
	}

	private String getUsuario() {
		return usuario;
	}

	private void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	private String getSenha() {
		return senha;
	}

	private void setSenha(String senha) {
		this.senha = senha;
	}

	public String getAssunto() {
		return assunto;
	}

	public void setAssunto(String assunto) {
		this.assunto = assunto;
	}

	public String getEmailDestino() {
		return emailDestino;
	}

	public void setEmailDestino(String emailDestino) {
		this.emailDestino = emailDestino;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

}
