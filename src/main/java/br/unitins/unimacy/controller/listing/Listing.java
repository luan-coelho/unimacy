package br.unitins.unimacy.controller.listing;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.primefaces.PrimeFaces;

import br.unitins.unimacy.exception.RepositoryException;
import br.unitins.unimacy.model.DefaultEntity;
import br.unitins.unimacy.repository.Repository;

public abstract class Listing<T extends DefaultEntity> implements Serializable {

	private static final long serialVersionUID = -2912482417877518288L;
	
	private String page;
	private Repository<T> repository;
	private List<T> list;

	public Listing(String page, Repository<T> repository) {
		super();
		this.page = page;
		this.repository = repository;
	}

	public void open() {
		Map<String, Object> options = new HashMap<String, Object>();
		options.put("modal", true);
		options.put("draggable", false);
		options.put("resizable", true);
		options.put("width", "30%");
		options.put("height", "60%");
		options.put("contentWidth", "100%");
		options.put("contentHeight", "100%");
	
		PrimeFaces.current().dialog().openDynamic(page, options, null);
	}
	
	public void open(String width, String height) {
		Map<String, Object> options = new HashMap<String, Object>();
		options.put("modal", true);
		options.put("draggable", false);
		options.put("resizable", true);
		options.put("width", width+"%");
		options.put("height", height+"%");
		options.put("contentWidth", "100%");
		options.put("contentHeight", "100%");
	
		PrimeFaces.current().dialog().openDynamic(page, options, null);
	}
	
	public void select(int id) {
		T obj = null;
		try {
			obj = repository.findById(id);
		} catch (RepositoryException e) {
			e.printStackTrace();
		}
		
		
		PrimeFaces.current().dialog().closeDynamic(obj);
	}
	
	public void pesquisar() {};
	
	public List<T> getList() {
		return list;
	}

	public void selecionarItem(T obj) {}
	
	public void setList(List<T> list) {
		this.list = list;
	}
	
	public Repository<T> getRepository() {
		return repository;
	}
}