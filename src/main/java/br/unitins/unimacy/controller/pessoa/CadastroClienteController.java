package br.unitins.unimacy.controller.pessoa;

import java.util.ArrayList;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.primefaces.event.SelectEvent;

import com.gtbr.exception.ViaCepException;
import com.gtbr.exception.ViaCepFormatException;

import br.unitins.unimacy.application.ApiCep;
import br.unitins.unimacy.application.Util;
import br.unitins.unimacy.controller.Controller;
import br.unitins.unimacy.controller.listing.PessoaFisicaListing;
import br.unitins.unimacy.controller.listing.PessoaJuridicaListing;
import br.unitins.unimacy.exception.RepositoryException;
import br.unitins.unimacy.model.filtro.FiltroPessoaFisica;
import br.unitins.unimacy.model.filtro.FiltroPessoaJuridica;
import br.unitins.unimacy.model.pessoa.Cliente;
import br.unitins.unimacy.model.pessoa.PessoaFisica;
import br.unitins.unimacy.model.pessoa.PessoaJuridica;
import br.unitins.unimacy.model.pessoa.endereco.Cidade;
import br.unitins.unimacy.model.pessoa.endereco.Endereco;
import br.unitins.unimacy.model.pessoa.endereco.Estado;
import br.unitins.unimacy.repository.pessoa.ClienteRepository;
import br.unitins.unimacy.repository.pessoa.PessoaFisicaRepository;

@Named
@ViewScoped
public class CadastroClienteController extends Controller<Cliente> {

	private static final long serialVersionUID = 1L;

	private boolean mudarTipoCliente;

	private String pesquisaPf;
	private String pesquisaPj;
	private FiltroPessoaFisica filtroPf = FiltroPessoaFisica.NOME;
	private FiltroPessoaJuridica filtroPj = FiltroPessoaJuridica.NOME;

	private List<Cliente> listaClientePf;
	private List<Cliente> listaClientePj;

	public CadastroClienteController() {
		super(new ClienteRepository());
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

		PessoaFisica pessoa = null;
		try {
			pessoa = repo.findByCpf(cpf);
		} catch (RepositoryException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (pessoa != null) {
			Util.addErrorMessage("Já existe um registro cadastrado com esse CPF");
		}
	}

	public void abrirPessoaFisicaListing() {
		PessoaFisicaListing listing = new PessoaFisicaListing();
		listing.open(90, 90);
	}

	public void obterPessoaFisicaListing(SelectEvent<PessoaFisica> event) {

	}

	public void abrirPessoaJuridicaListing() {
		PessoaJuridicaListing listing = new PessoaJuridicaListing();
		listing.open(90, 90);
	}

	public void obterPessoaJuridicaListing(SelectEvent<PessoaJuridica> event) {

	}
	
	public FiltroPessoaFisica[] getFiltroPessoaFisica() {
		return FiltroPessoaFisica.values();
	}
	
	public FiltroPessoaJuridica[] getFiltroPessoaJuridica() {
		return FiltroPessoaJuridica.values();
	}

	public void pesquisaPfPorFiltro() {
		List<Cliente> lista = null;

		ClienteRepository repo = (ClienteRepository) getRepository();

		switch (filtroPf) {
		case NOME: {
			try {
				lista = repo.findAllClientePfByNome(pesquisaPf);
			} catch (RepositoryException e) {
				Util.addErrorMessage("Falha ao realizar consulta");
				e.printStackTrace();
			}
			break;
		}
		case CPF: {
			try {
				lista = (List<Cliente>) repo.findAllClientePfByCpf(pesquisaPf);
			} catch (RepositoryException e) {
				Util.addErrorMessage("Falha ao consultar CPF");
				e.printStackTrace();
			}
			break;
		}
		case EMAIL: {
			try {
				lista = repo.findAllClientePfByEmail(pesquisaPf);
			} catch (RepositoryException e) {
				Util.addErrorMessage("Falha ao consultar email");
				e.printStackTrace();
			}
			break;
		}
		case TELEFONE: {
			try {
				lista = repo.findAllClientePfByTelefone(pesquisaPf);
			} catch (RepositoryException e) {
				Util.addErrorMessage("Falha ao consultar telefone");
				e.printStackTrace();
			}
			break;
		}
		default:
			break;
		}

		if (lista.isEmpty()) {
			Util.addWarnMessage("Nenhum produto encontrado");
			return;
		}
		listaClientePf = lista;
	}

	public void pesquisaPJPorFiltro() {
		List<Cliente> lista = null;

		ClienteRepository repo = (ClienteRepository) getRepository();

		switch (filtroPj) {
		case NOME: {
			try {
				lista = repo.findAllClientePjByNome(pesquisaPj);
			} catch (RepositoryException e) {
				Util.addErrorMessage("Falha ao realizar consulta");
				e.printStackTrace();
			}
			break;
		}
		case CNPJ: {
			try {
				lista = (List<Cliente>) repo.findAllClientePjByCnpj(pesquisaPj);
			} catch (RepositoryException e) {
				Util.addErrorMessage("Falha ao consultar CNPJ");
				e.printStackTrace();
			}
			break;
		}
		case EMAIL: {
			try {
				lista = repo.findAllClienteByEmail(pesquisaPj);
			} catch (RepositoryException e) {
				Util.addErrorMessage("Falha ao consultar email");
				e.printStackTrace();
			}
			break;
		}
		case TELEFONE: {
			try {
				lista = repo.findAllClientePjByTelefone(pesquisaPj);
			} catch (RepositoryException e) {
				Util.addErrorMessage("Falha ao consultar telefone");
				e.printStackTrace();
			}
			break;
		}
		default:
			break;
		}

		if (lista.isEmpty()) {
			Util.addWarnMessage("Nenhum produto encontrado");
			return;
		}
		listaClientePj = lista;
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

	public boolean getMudarTipoCliente() {
		return mudarTipoCliente;
	}

	public void setMudarTipoCliente(boolean mudarTipoCliente) {
		this.mudarTipoCliente = mudarTipoCliente;
	}

	public String getPesquisaPf() {
		return pesquisaPf;
	}

	public void setPesquisaPf(String pesquisaPf) {
		this.pesquisaPf = pesquisaPf;
	}

	public String getPesquisaPj() {
		return pesquisaPj;
	}

	public void setPesquisaPj(String pesquisaPj) {
		this.pesquisaPj = pesquisaPj;
	}

	public FiltroPessoaFisica getFiltroPf() {
		return filtroPf;
	}

	public void setFiltroPf(FiltroPessoaFisica filtroPf) {
		this.filtroPf = filtroPf;
	}

	public FiltroPessoaJuridica getFiltroPj() {
		return filtroPj;
	}

	public void setFiltroPj(FiltroPessoaJuridica filtroPj) {
		this.filtroPj = filtroPj;
	}

	public List<Cliente> getListaClientePf() {
		if (listaClientePf == null)
			listaClientePf = new ArrayList<Cliente>();
		return listaClientePf;
	}

	public void setListaClientePf(List<Cliente> listaClientePf) {
		this.listaClientePf = listaClientePf;
	}

	public List<Cliente> getListaClientePj() {
		if (listaClientePj == null)
			listaClientePj = new ArrayList<Cliente>();
		return listaClientePj;
	}

	public void setListaClientePj(List<Cliente> listaClientePj) {
		this.listaClientePj = listaClientePj;
	}
}