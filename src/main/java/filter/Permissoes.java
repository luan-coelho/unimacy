package filter;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import br.unitins.unimacy.model.pessoa.Cargo;
import br.unitins.unimacy.model.pessoa.Funcionario;

public class Permissoes {
	private Funcionario funcionario;
	private HashMap<Cargo, List<String>> paginasNaoPermitidasPorCargo;

	private final static String URL = "/Unimacy/gestao/";

	public HashMap<Cargo, List<String>> getpaginasNaoPermitidasPorCargo() {
		if (paginasNaoPermitidasPorCargo == null) {
			this.paginasNaoPermitidasPorCargo = new LinkedHashMap<>();

			this.paginasNaoPermitidasPorCargo.put(Cargo.ADMINISTRADOR, null);

			this.paginasNaoPermitidasPorCargo.put(Cargo.FUNCIONARIO,
					Arrays.asList(URL + "gerencia-funcionario.xhtml", 
								  URL + "funcionario.xhtml"));
		}
		return paginasNaoPermitidasPorCargo;
	}

	public boolean permitirAcesso(Funcionario funcionario, String pagina) {
		if(funcionario.getCargo().equals(Cargo.ADMINISTRADOR))
			return true;
		else return !getpaginasNaoPermitidasPorCargo().get(funcionario.getCargo()).contains(pagina);
	}

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}
}
