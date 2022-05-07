package br.unitins.unimacy.controller;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import com.gtbr.exception.ViaCepException;
import com.gtbr.exception.ViaCepFormatException;

import br.unitins.unimacy.application.ApiCep;
import br.unitins.unimacy.application.Util;
import br.unitins.unimacy.model.Cidade;
import br.unitins.unimacy.model.Cliente;
import br.unitins.unimacy.model.Endereco;
import br.unitins.unimacy.model.Estado;
import br.unitins.unimacy.model.PessoaFisica;
import br.unitins.unimacy.model.Sexo;
import br.unitins.unimacy.repository.ClienteRepository;
import br.unitins.unimacy.repository.PessoaFisicaRepository;

@Named
@ViewScoped
public class CadastroClienteController extends Controller<Cliente> {

	private static final long serialVersionUID = -2587172429280470098L;

	public CadastroClienteController() {
		super(new ClienteRepository());
	}

	@Override
	public Cliente getEntity() {
		if (entity == null) {
			entity = new Cliente();
			entity.setPessoa(new PessoaFisica());
			entity.getPessoa().setEndereco(new Endereco(new Cidade(new Estado())));
		}

		return entity;
	}

	public Sexo[] getListaSexo() {
		return Sexo.values();
	}

	public void buscarCep() {
		try {
			entity.getPessoa().setEndereco(ApiCep.findCep(entity.getPessoa().getEndereco().getCep()));
		} catch (ViaCepException e) {
			Util.addErrorMessage("Informe um CEP válido");
		} catch (ViaCepFormatException e) {
			Util.addErrorMessage("CEP com formato inválido");
		} catch (Exception e) {
			Util.addErrorMessage("Falha ao buscar CEP. Digite os dados");
		}

	}

	public void verificarCpf() {
		PessoaFisicaRepository repo = new PessoaFisicaRepository();

		String cpf = ((PessoaFisica) entity.getPessoa()).getCpf().trim();

		PessoaFisica pessoa = repo.findByCpf(cpf);

		if (pessoa != null) {
			Util.addErrorMessage("Já existe um registro cadastrado com esse CPF");
		}
	}
}