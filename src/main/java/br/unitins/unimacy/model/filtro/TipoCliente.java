package br.unitins.unimacy.model.filtro;

public enum TipoCliente {
	PESSOA_FISICA(1, "Pessoa Física"), PESSOA_JURIDICA(2, "Pessoa Jurídica");

	private int id;
	private String label;

	TipoCliente(int id, String label) {
		this.id = id;
		this.label = label;
	}

	public int getId() {
		return id;
	}

	public String getLabel() {
		return label;
	}

	public static TipoCliente valueOf(int id) {
		for (TipoCliente tipo : TipoCliente.values()) {
			if (tipo.getId() == id)
				return tipo;
		}
		return null;
	}
}
