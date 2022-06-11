package br.unitins.unimacy.model.pessoa;

public enum Cargo {
	ADMINISTRADOR(1, "Administrador"), FUNCIONARIO(2, "Funcionário");

	private int id;
	private String label;

	Cargo(int id, String label) {
		this.id = id;
		this.label = label;
	}

	public int getId() {
		return id;
	}

	public String getLabel() {
		return label;
	}

	public static Cargo valueOf(int id) {
		for (Cargo cargo : Cargo.values()) {
			if (cargo.getId() == id)
				return cargo;
		}
		return null;
	}
}
