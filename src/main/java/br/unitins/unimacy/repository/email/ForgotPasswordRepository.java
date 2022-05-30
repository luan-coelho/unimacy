package br.unitins.unimacy.repository.email;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import br.unitins.unimacy.exception.RepositoryException;
import br.unitins.unimacy.model.email.ForgotPassword;
import br.unitins.unimacy.repository.Repository;

public class ForgotPasswordRepository extends Repository<ForgotPassword> {

	public ForgotPassword findByCodigo(String codigo) throws RepositoryException {
		try {
			StringBuffer jpsql = new StringBuffer();
			jpsql.append("SELECT ");
			jpsql.append("f ");
			jpsql.append("FROM ");
			jpsql.append("ForgotPassword f ");
			jpsql.append("WHERE ");
			jpsql.append("f.codigo LIKE :codigo ");

			Query query = getEntityManager().createQuery(jpsql.toString());
			query.setParameter("codigo", codigo);

			return (ForgotPassword) query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		} catch (Exception e) {
			throw new RepositoryException("Erro ao buscar código");
		}
	}

}
