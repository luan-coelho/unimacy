package br.unitins.unimacy.controller.listing;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.primefaces.PrimeFaces;

import br.unitins.unimacy.exception.RepositoryException;
import br.unitins.unimacy.model.DefaultEntity;
import br.unitins.unimacy.repository.Repository;

public abstract class ListingSql<T extends DefaultEntity> implements Serializable {

	private static final long serialVersionUID = 7641180780489288293L;
	private final String page;
	private final Repository<T> repository;
	private List<Object[]> list;

	public ListingSql(String page, Repository<T> repository) {
		super();
		this.page = page;
		this.repository = repository;
	}

	public void open() {
		Map<String, Object> options = new HashMap<>();
		options.put("modal", true);
		options.put("draggable", false);
		options.put("resizable", true);
		options.put("width", "30%");
		options.put("height", "60%");
		options.put("contentWidth", "100%");
		options.put("contentHeight", "100%");
		options.put("closeOnEscape", true);

		PrimeFaces.current().dialog().openDynamic(page, options, null);
	}

	public void open(int width, int height) {
		Map<String, Object> options = new HashMap<String, Object>();
		options.put("modal", true);
		options.put("draggable", false);
		options.put("resizable", true);
		options.put("width", width + "%");
		options.put("height", height + "%");
		options.put("contentWidth", "100%");
		options.put("contentHeight", "100%");
		options.put("closeOnEscape", true);

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

	public abstract void pesquisar();

	public List<Object[]> getList() {
		return list;
	}

	public void setList(List<Object[]> list) {
		this.list = list;
	}

	public Repository<T> getRepository() {
		return repository;
	}

}