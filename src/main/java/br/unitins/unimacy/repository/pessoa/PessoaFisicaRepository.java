package br.unitins.unimacy.repository.pessoa;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import br.unitins.unimacy.exception.RepositoryException;
import br.unitins.unimacy.model.pessoa.PessoaFisica;
import br.unitins.unimacy.repository.Repository;

public class PessoaFisicaRepository extends Repository<PessoaFisica> {

	@SuppressWarnings("unchecked")
	public List<PessoaFisica> findAllByCpf(String cpf) throws RepositoryException {
		try {
			StringBuffer jpsql = new StringBuffer();
			jpsql.append("SELECT ");
			jpsql.append("p ");
			jpsql.append("FROM ");
			jpsql.append("PessoaFisica p ");
			jpsql.append("WHERE ");
			jpsql.append("p.cpf LIKE :cpf ");

			Query query = getEntityManager().createQuery(jpsql.toString());
			query.setParameter("cpf", "%" + cpf + "%");

			return query.getResultList();
		} catch (NoResultException e) {
			return null;
		} catch (Exception e) {
			throw new RepositoryException("Erro ao buscar CPF");
		}
	}

	public PessoaFisica findByCpf(String cpf) throws RepositoryException {
		try {
			StringBuffer jpsql = new StringBuffer();
			jpsql.append("SELECT ");
			jpsql.append("p ");
			jpsql.append("FROM ");
			jpsql.append("PessoaFisica p ");
			jpsql.append("WHERE ");
			jpsql.append("p.cpf LIKE :cpf ");

			Query query = getEntityManager().createQuery(jpsql.toString());
			query.setParameter("cpf", cpf);

			return (PessoaFisica) query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		} catch (Exception e) {
			throw new RepositoryException("Erro ao buscar CPF");
		}
	}

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

	@SuppressWarnings("unchecked")
	public List<PessoaFisica> findByTelefone(String telefone) throws RepositoryException {
		try {
			StringBuffer jpsql = new StringBuffer();
			jpsql.append("SELECT ");
			jpsql.append("p ");
			jpsql.append("FROM ");
			jpsql.append("PessoaFisica p ");
			jpsql.append("WHERE ");
			jpsql.append("LOWER(p.telefone) ");
			jpsql.append("LIKE LOWER(:telefone) ");

			Query query = getEntityManager().createQuery(jpsql.toString());
			query.setParameter("telefone", "%" + telefone + "%");

			return query.getResultList();

		} catch (Exception e) {
			throw new RepositoryException("Erro ao buscar telefone");
		}
	}
}
