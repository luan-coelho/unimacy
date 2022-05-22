package br.unitins.unimacy.repository.pessoa;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import br.unitins.unimacy.exception.RepositoryException;
import br.unitins.unimacy.model.pessoa.Fornecedor;
import br.unitins.unimacy.repository.Repository;

public class FornecedorRepository extends Repository<Fornecedor> {

	@SuppressWarnings("unchecked")
	public List<Fornecedor> findByEmail(String email) throws RepositoryException {
		try {
			StringBuffer jpsql = new StringBuffer();
			jpsql.append("SELECT ");
			jpsql.append("f ");
			jpsql.append("FROM ");
			jpsql.append("Fornecedor f ");
			jpsql.append("WHERE ");
			jpsql.append("LOWER(f.pessoaJuridica.email) ");
			jpsql.append("LIKE LOWER(:email) ");

			Query query = getEntityManager().createQuery(jpsql.toString());
			query.setParameter("email", "%" + email + "%");

			return query.getResultList();

		} catch (Exception e) {
			throw new RepositoryException("Erro ao buscar email");
		}
	}

	@SuppressWarnings("unchecked")
	public List<Fornecedor> findAllByCnpj(String cnpj) throws RepositoryException {
		try {
			StringBuffer jpsql = new StringBuffer();
			jpsql.append("SELECT ");
			jpsql.append("f ");
			jpsql.append("FROM ");
			jpsql.append("Fornecedor f ");
			jpsql.append("WHERE ");
			jpsql.append("f.pessoaJuridica.cnpj LIKE :cnpj ");

			Query query = getEntityManager().createQuery(jpsql.toString());
			query.setParameter("cnpj", "%" + cnpj + "%");

			return query.getResultList();
		} catch (NoResultException e) {
			return null;
		} catch (Exception e) {
			throw new RepositoryException("Erro ao buscar CPF");
		}
	}

	@SuppressWarnings("unchecked")
	public List<Fornecedor> findByTelefone(String telefone) throws RepositoryException {
		try {
			StringBuffer jpsql = new StringBuffer();
			jpsql.append("SELECT ");
			jpsql.append("f ");
			jpsql.append("FROM ");
			jpsql.append("Fornecedor f ");
			jpsql.append("WHERE ");
			jpsql.append("LOWER(f.pessoaJuridica.telefone) ");
			jpsql.append("LIKE LOWER(:telefone) ");

			Query query = getEntityManager().createQuery(jpsql.toString());
			query.setParameter("telefone", "%" + telefone + "%");

			return query.getResultList();

		} catch (Exception e) {
			throw new RepositoryException("Erro ao buscar telefone");
		}
	}

	public Fornecedor findByIdPessoaJuridica(int id) throws RepositoryException {
		try {
			StringBuffer jpql = new StringBuffer();
			jpql.append("SELECT ");
			jpql.append("f ");
			jpql.append("FROM ");
			jpql.append("Fornecedor f ");
			jpql.append("WHERE ");
			jpql.append("f.pessoaJuridica.id = :id ");

			Query query = getEntityManager().createQuery(jpql.toString());
			query.setParameter("id", id);

			return (Fornecedor) query.getSingleResult();
		} catch (NoResultException e) {
			System.out.println(e.getMessage());
			return null;

		} catch (Exception e) {
			e.printStackTrace();
			throw new RepositoryException("Erro ao executar o findByNome.");
		}
	}
}
