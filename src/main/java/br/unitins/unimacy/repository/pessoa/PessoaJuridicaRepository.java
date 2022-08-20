package br.unitins.unimacy.repository.pessoa;

import java.util.List;


import br.unitins.unimacy.exception.RepositoryException;
import br.unitins.unimacy.model.pessoa.PessoaJuridica;
import br.unitins.unimacy.repository.Repository;
import jakarta.persistence.NoResultException;
import jakarta.persistence.Query;

public class PessoaJuridicaRepository extends Repository<PessoaJuridica> {

	@SuppressWarnings("unchecked")
	public List<PessoaJuridica> findAllByNome(String nome) throws RepositoryException {
		try {
			StringBuffer jpsql = new StringBuffer();
			jpsql.append("SELECT ");
			jpsql.append("p ");
			jpsql.append("FROM ");
			jpsql.append("PessoaJuridica p ");
			jpsql.append("WHERE ");
			jpsql.append("LOWER(p.nomeFantasia) ");
			jpsql.append("LIKE LOWER(:nome) ");

			Query query = getEntityManager().createQuery(jpsql.toString());
			query.setParameter("nome", "%" + nome + "%");

			return query.getResultList();
		} catch (Exception e) {
			System.out.println("Erro ao executar o método de find.");
			e.printStackTrace();
			throw new RepositoryException("Erro ao buscar nome");
		}
	}

	public PessoaJuridica findByCpnj(String cnpj) {
		PessoaJuridica pessoa = null;

		try {
			StringBuffer jpsql = new StringBuffer();
			jpsql.append("SELECT ");
			jpsql.append("p ");
			jpsql.append("FROM ");
			jpsql.append("PessoaJuridica p ");
			jpsql.append("WHERE ");
			jpsql.append("p.cnpj LIKE :cnpj ");

			Query query = getEntityManager().createQuery(jpsql.toString());
			query.setParameter("cnpj", cnpj);

			pessoa = (PessoaJuridica) query.getSingleResult();

		} catch (NoResultException e) {
			return null;
		} catch (Exception e) {
			System.out.println("Erro ao executar o método de find.");
			e.printStackTrace();
		}

		return pessoa;
	}

	@SuppressWarnings("unchecked")
	public List<PessoaJuridica> findByTelefone(String telefone) throws RepositoryException {
		try {
			StringBuffer jpsql = new StringBuffer();
			jpsql.append("SELECT ");
			jpsql.append("p ");
			jpsql.append("FROM ");
			jpsql.append("PessoaJuridica p ");
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

	@SuppressWarnings("unchecked")
	public List<PessoaJuridica> findByEmail(String email) throws RepositoryException {
		try {
			StringBuffer jpsql = new StringBuffer();
			jpsql.append("SELECT ");
			jpsql.append("p ");
			jpsql.append("FROM ");
			jpsql.append("PessoaJuridica p ");
			jpsql.append("WHERE ");
			jpsql.append("LOWER(p.email) ");
			jpsql.append("LIKE ");
			jpsql.append("LOWER(:email) ");

			Query query = getEntityManager().createQuery(jpsql.toString());
			query.setParameter("email", "%" + email + "%");

			return query.getResultList();

		} catch (Exception e) {
			throw new RepositoryException("Erro ao buscar email");
		}
	}

	@SuppressWarnings("unchecked")
	public List<PessoaJuridica> findAllByCnpj(String cnpj) throws RepositoryException {
		try {
			StringBuffer jpsql = new StringBuffer();
			jpsql.append("SELECT ");
			jpsql.append("p ");
			jpsql.append("FROM ");
			jpsql.append("PessoaJuridica p ");
			jpsql.append("WHERE ");
			jpsql.append("p.cnpj LIKE :cnpj ");

			Query query = getEntityManager().createQuery(jpsql.toString());
			query.setParameter("cnpj", "%" + cnpj + "%");

			return query.getResultList();
		} catch (NoResultException e) {
			return null;
		} catch (Exception e) {
			throw new RepositoryException("Erro ao buscar CPF");
		}
	}
}
