package br.unitins.unimacy.controller;

import java.io.Serializable;

import br.unitins.unimacy.application.Util;
import br.unitins.unimacy.exception.RepositoryException;
import br.unitins.unimacy.model.DefaultEntity;
import br.unitins.unimacy.repository.Repository;

public abstract class Controller <T extends DefaultEntity> implements Serializable  {

	private static final long serialVersionUID = 3495567407787733123L;
	
	private Repository<T> repository;
	protected T entity;
	
	public Controller(Repository<T> repository) {
		super();
		this.repository = repository;
	}

	public void incluir() {
		try {
			getRepository().save(getEntity());
			Util.addInfoMessage("Inclusão realizada com sucesso.");
			limpar();
		} catch (RepositoryException e) {
			e.printStackTrace();
			Util.addErrorMessage(e.getMessage());
		}
	}

	public void alterar() {
		try {
			getRepository().save(getEntity());
			Util.addInfoMessage("Alteração realizada com sucesso.");
			limpar();
		} catch (RepositoryException e) {
			e.printStackTrace();
			Util.addErrorMessage(e.getMessage());
		}
	}
	
	public void alterar(T obj) {
		this.entity = obj;
		alterar();
	}
	
	public void excluir() {
		try {
			getRepository().remove(getEntity());
			Util.addInfoMessage("Exclusão realizada com sucesso.");
			limpar();
		} catch (RepositoryException e) {
			e.printStackTrace();
			Util.addErrorMessage(e.getMessage());
		}
	}
	
	public void limpar() {
		entity = null;
	}
	
	public void selecionarItem(T obj) {
		this.entity = obj;
	}
	
	public void editarItem(T obj) {
	}
	
	public Repository<T> getRepository() {
		return repository;
	}
	
	public abstract T getEntity();
	
	@SuppressWarnings("unchecked")
	public void setEntity(Object obj) {
		this.entity = (T) obj;
	}

}