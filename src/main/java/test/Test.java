package test;

import br.unitins.unimacy.exception.RepositoryException;
import br.unitins.unimacy.model.pessoa.Cliente;
import br.unitins.unimacy.repository.pessoa.ClienteRepository;

public class Test {
	public static void main(String[] args) {

		ClienteRepository repo = new ClienteRepository();

		try {
			Cliente cliente = repo.findById(1);
			System.out.println(cliente);
		} catch (RepositoryException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
