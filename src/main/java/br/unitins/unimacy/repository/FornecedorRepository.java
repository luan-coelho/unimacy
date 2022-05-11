package br.unitins.unimacy.repository;

import java.util.List;

import javax.persistence.Query;

import br.unitins.unimacy.exception.RepositoryException;
import br.unitins.unimacy.model.pessoa.Fornecedor;

public class FornecedorRepository extends Repository<Fornecedor> {

	@SuppressWarnings("unchecked")
	@Override
	public List <Fornecedor> findByNome(String nome) throws RepositoryException{
		List <Fornecedor> lista = null;
		
		try {
			Query query = getEntityManager().createQuery("select f from fornecedor f, pessoajuridica p where LOWER(p.nomefantasia) LIKE LOWER(:nome)");
			query.setParameter("nome", "%" + nome + "%");

			lista = query.getResultList();

		} catch (Exception e) {
			System.out.println("Erro ao executar o método de find.");
			e.printStackTrace();
			return null;
		}

		return lista;
	}
}
