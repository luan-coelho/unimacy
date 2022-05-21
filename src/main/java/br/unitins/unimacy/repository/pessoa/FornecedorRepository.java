package br.unitins.unimacy.repository.pessoa;

import java.util.List;

import javax.persistence.Query;

import br.unitins.unimacy.exception.RepositoryException;
import br.unitins.unimacy.model.pessoa.Fornecedor;
import br.unitins.unimacy.model.pessoa.PessoaFisica;
import br.unitins.unimacy.repository.Repository;

public class FornecedorRepository extends Repository<Fornecedor> {

	@SuppressWarnings("unchecked")
	public List<PessoaFisica> findByEmail(String email) throws RepositoryException {
		try {
			StringBuffer jpsql = new StringBuffer();
			jpsql.append("SELECT ");
			jpsql.append("p ");
			jpsql.append("FROM ");
			jpsql.append("PessoaFisica p ");
			jpsql.append("WHERE ");
			jpsql.append("LOWER(p.email) ");
			jpsql.append("LIKE LOWER(:email) ");

			Query query = getEntityManager().createQuery(jpsql.toString());
			query.setParameter("email", "%" + email + "%");

			return query.getResultList();

		} catch (Exception e) {
			throw new RepositoryException("Erro ao buscar email");
		}
	}
}
