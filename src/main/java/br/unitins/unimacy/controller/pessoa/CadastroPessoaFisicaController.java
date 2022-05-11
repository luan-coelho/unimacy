package br.unitins.unimacy.controller.pessoa;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import com.gtbr.exception.ViaCepException;
import com.gtbr.exception.ViaCepFormatException;

import br.unitins.unimacy.application.ApiCep;
import br.unitins.unimacy.application.Session;
import br.unitins.unimacy.application.Util;
import br.unitins.unimacy.controller.Controller;
import br.unitins.unimacy.exception.RepositoryException;
import br.unitins.unimacy.model.pessoa.PessoaFisica;
import br.unitins.unimacy.model.pessoa.Sexo;
import br.unitins.unimacy.model.pessoa.endereco.Cidade;
import br.unitins.unimacy.model.pessoa.endereco.Endereco;
import br.unitins.unimacy.model.pessoa.endereco.Estado;
import br.unitins.unimacy.repository.pessoa.PessoaFisicaRepository;

@Named
@ViewScoped
public class CadastroPessoaFisicaController extends Controller<PessoaFisica> {

	private static final long serialVersionUID = 783180342902897686L;

	public CadastroPessoaFisicaController() {
		super(new PessoaFisicaRepository());
		entity = (PessoaFisica) Session.getInstance().get("pessoafisica-crud");
		Session.getInstance().set("pessoafisica-crud", null);
	}

	@Override
	public PessoaFisica getEntity() {
		if (entity == null) {
			entity = new PessoaFisica();
			entity.setEndereco(new Endereco(new Cidade(new Estado())));
		}

		return entity;
	}

	public Sexo[] getListaSexo() {
		return Sexo.values();
	}


	public void excluir(PessoaFisica PessoaFisica) {
		entity = PessoaFisica;
		super.excluir();
	}

	public void buscarCep() {
		try {
			entity.setEndereco(ApiCep.findCep(entity.getEndereco().getCep()));
		} catch (ViaCepException e) {
			Util.addErrorMessage("Informe um CEP válido");
		} catch (ViaCepFormatException e) {
			Util.addErrorMessage("CEP com formato inválido");
		} catch (Exception e) {
			Util.addErrorMessage("Falha ao buscar CEP. Digite os dados");
		}

	}

	public void pesquisarPorNome(String nome) {
		try {
			getRepository().findByNome(nome);
		} catch (RepositoryException e) {
			e.printStackTrace();
		}
	}

	public void verificarCpf() {
		PessoaFisica pessoa = null;
		try {
			pessoa = ((PessoaFisicaRepository) getRepository()).findByCpf(entity.getCpf());
		} catch (RepositoryException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 

		if (pessoa != null) {
			Util.addErrorMessage("Já existe um registro cadastrado com esse CPF");
		}
	}
	
	public void telaGerenciaPessoaFisica() {
		Util.redirect("gerencia-pessoafisica.xhtml");
	}
	
	public void enviarObjetoParaSummary() {
		Session.getInstance().set("pessoafisica", entity);
	}
}