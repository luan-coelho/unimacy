package br.unitins.unimacy.controller;

import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.unitins.unimacy.application.Session;
import br.unitins.unimacy.application.Util;
import br.unitins.unimacy.exception.RepositoryException;
import br.unitins.unimacy.model.Cidade;
import br.unitins.unimacy.model.Endereco;
import br.unitins.unimacy.model.Estado;
import br.unitins.unimacy.model.PessoaJuridica;
import br.unitins.unimacy.model.Sexo;
import br.unitins.unimacy.repository.PessoaJuridicaRepository;

@Named
@ViewScoped
public class PessoaJuridicaController extends Controller<PessoaJuridica> {

	private static final long serialVersionUID = 4178893876749538318L;
	
	private List<PessoaJuridica> listaPessoaJuridica;

	public PessoaJuridicaController() {
		super(new PessoaJuridicaRepository());
	}

	@Override
	public PessoaJuridica getEntity() {
		if (entity == null) {
			entity = new PessoaJuridica();
			entity.setEndereco(new Endereco(new Cidade(new Estado())));
		}

		return entity;
	}

	public Sexo[] getListaSexo() {
		return Sexo.values();
	}

	public List<PessoaJuridica> getListaPessoaJuridica() {
		if (listaPessoaJuridica == null) {
			try {
				listaPessoaJuridica = getRepository().findAll();
			} catch (RepositoryException e) {
				Util.addErrorMessage("Falha ao buscar os dados no banco");
				e.printStackTrace();
			}
		}
		return listaPessoaJuridica;
	}

	public void setListaPessoaJuridica(List<PessoaJuridica> listaPessoaJuridica) {
		this.listaPessoaJuridica = listaPessoaJuridica;
	}

	@Override
	public void limpar() {
		super.limpar();
		listaPessoaJuridica = null;
	}

	public void excluir(PessoaJuridica PessoaJuridica) {
		entity = PessoaJuridica;
		super.excluir();
	}

	public void onItemSelect() {
		String nomeEstado = entity.getEndereco().getCidade().getEstado().getNome();
		Session.getInstance().set("nome-estado", nomeEstado);
	}

	public void pesquisarPorNome(String nome) {
		try {
			getRepository().findByNome(nome);
		} catch (RepositoryException e) {
			e.printStackTrace();
		}
	}

}