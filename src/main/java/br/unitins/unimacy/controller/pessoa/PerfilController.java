package br.unitins.unimacy.controller.pessoa;

import java.io.IOException;
import java.io.InputStream;

import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.validation.constraints.NotBlank;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.file.UploadedFile;

import com.gtbr.exception.ViaCepException;
import com.gtbr.exception.ViaCepFormatException;

import br.unitins.unimacy.application.ApiCep;
import br.unitins.unimacy.application.Session;
import br.unitins.unimacy.application.Util;
import br.unitins.unimacy.controller.Controller;
import br.unitins.unimacy.exception.RepositoryException;
import br.unitins.unimacy.exception.VersionException;
import br.unitins.unimacy.model.pessoa.Funcionario;
import br.unitins.unimacy.repository.pessoa.FuncionarioRepository;

@Named
@ViewScoped
public class PerfilController extends Controller<Funcionario> {

	private static final long serialVersionUID = -8919718922968640074L;

	private boolean isEditable;
	
	private InputStream fotoInputStream = null;

	@NotBlank(message = "Informe a senha atual")
	private String senhaAtual;

	@NotBlank(message = "Informe uma nova senha")
	private String senha;
	@NotBlank(message = "Informe novamente uma nova senha")
	private String novaSenha;

	public PerfilController() {
		super(new FuncionarioRepository());
		setEntity((Funcionario) Session.getInstance().get("funcionarioLogado"));
	}

	public void upload(FileUploadEvent event) {
		UploadedFile uploadFile = event.getFile();
		System.out.println("nome arquivo: " + uploadFile.getFileName());
		System.out.println("tipo: " + uploadFile.getContentType());
		System.out.println("tamanho: " + uploadFile.getSize());

		if (uploadFile.getContentType().equals("image/png")) {
			try {
				fotoInputStream = uploadFile.getInputStream();
				System.out.println("inputStream: " + uploadFile.getInputStream().toString());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Util.addInfoMessage("Upload realizado com sucesso.");
		} else {
			Util.addErrorMessage("O tipo da image deve ser png.");
		}
		
		alterarFoto();
	}
	
	public void buscarCep() {
		try {
			entity.getPessoaFisica().setEndereco(ApiCep.findCep(entity.getPessoaFisica().getEndereco().getCep()));
		} catch (ViaCepException e) {
			Util.addErrorMessage("Informe um CEP válido");
		} catch (ViaCepFormatException e) {
			Util.addErrorMessage("CEP com formato inválido");
		} catch (Exception e) {
			Util.addErrorMessage("Falha ao buscar CEP. Digite os dados");
		}

	}

	
	public void editar() {
		this.isEditable = !this.isEditable;
	}
	
	public void salvarSemLimpar() {
		try {
			setEntity(getRepository().save(getEntity()));
			Util.addInfoMessage("Salvo com sucesso.");
		} catch (RepositoryException e) {
			e.printStackTrace();
			Util.addErrorMessage(e.getMessage());
		} catch (VersionException e) {
			e.printStackTrace();
			Util.addErrorMessage(e.getMessage());
		}
	}
	
	@Override
	public void salvar(Funcionario obj) {
		super.salvarSemLimpar();
		
		if (getFotoInputStream() != null) {
			if (! Util.saveImageUsuario(getFotoInputStream(), "png", getEntity().getId())) {
				Util.addErrorMessage("Erro ao salvar. Não foi possível salvar a foto.");
				return;
			}
		}
	}

	public void alterarFoto() {
		super.salvarSemLimpar();
			
		if (getFotoInputStream() != null) {
			if (! Util.saveImageUsuario(getFotoInputStream(), "png", getEntity().getId())) {
				Util.addErrorMessage("Erro ao salvar. Não foi possível salvar a foto.");
				return;
			}
		}
		limpar();
	}
	
	public void alterarSenha() {
		if (!senha.equals(novaSenha)) {
			Util.addErrorMessage("As senhas não coincidem");
			return;
		}

		if (Util.hash(getEntity().getPessoaFisica().getCpf()+senhaAtual).equals(getEntity().getSenha())) {
			getEntity().setSenha(Util.hash(getEntity().getPessoaFisica().getCpf()+novaSenha));
			salvar(getEntity());
			Session.getInstance().set("funcionarioLogado", getEntity());
			Util.addInfoMessage("Senha alterada com sucesso.");
		} else {
			Util.addErrorMessage("Senha atual incorreta");
			return;
		}
	}

	@Override
	public Funcionario getEntity() {
		if (entity == null) {
			entity = new Funcionario();
		}
		return entity;
	}

	public boolean isEditable() {
		return isEditable;
	}

	public void setEditable(boolean isEditable) {
		this.isEditable = isEditable;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getNovaSenha() {
		return novaSenha;
	}

	public void setNovaSenha(String novaSenha) {
		this.novaSenha = novaSenha;
	}

	public String getSenhaAtual() {
		return senhaAtual;
	}

	public void setSenhaAtual(String senhaAtual) {
		this.senhaAtual = senhaAtual;
	}
	
	public InputStream getFotoInputStream() {
		return fotoInputStream;
	}

	public void setFotoInputStream(InputStream fotoInputStream) {
		this.fotoInputStream = fotoInputStream;
	}
}