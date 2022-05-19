package br.unitins.unimacy.controller.pessoa;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.primefaces.event.SelectEvent;

import br.unitins.unimacy.controller.Controller;
import br.unitins.unimacy.controller.listing.PessoaFisicaListing;
import br.unitins.unimacy.model.pessoa.Funcionario;
import br.unitins.unimacy.model.pessoa.PessoaFisica;
import br.unitins.unimacy.repository.pessoa.FuncionarioRepository;

@Named
@ViewScoped
public class CadastroFuncionarioController extends Controller<Funcionario> {

	private static final long serialVersionUID = 8482905701722840612L;

	public CadastroFuncionarioController() {
		super(new FuncionarioRepository());
	}

	@Override
	public Funcionario getEntity() {
		if (entity == null) {
			entity = new Funcionario();
		}

		return entity;
	}

	public void abrirPessoaFisicaListing() {
		PessoaFisicaListing listing = new PessoaFisicaListing();
		listing.open("70", "70");
	}

	public void obterPessoaFisicaListing(SelectEvent<PessoaFisica> event) {

	}

}