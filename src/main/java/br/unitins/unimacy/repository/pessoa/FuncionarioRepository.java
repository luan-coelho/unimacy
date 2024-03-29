package br.unitins.unimacy.repository.pessoa;

import java.util.List;


import br.unitins.unimacy.exception.RepositoryException;
import br.unitins.unimacy.model.pessoa.Funcionario;
import br.unitins.unimacy.repository.Repository;
import jakarta.persistence.NoResultException;
import jakarta.persistence.Query;

public class FuncionarioRepository extends Repository<Funcionario> {

	public Funcionario validarLogin(String cpf, String senha) throws RepositoryException {
		try {
			StringBuffer jpsql = new StringBuffer();
			jpsql.append("SELECT ");
			jpsql.append("  f ");
			jpsql.append("FROM ");
			jpsql.append("  Funcionario f ");
			jpsql.append("WHERE ");
			jpsql.append("  f.pessoaFisica.cpf = :cpf ");
			jpsql.append("AND ");
			jpsql.append("  f.senha = :senha ");

			Query query = getEntityManager().createQuery(jpsql.toString());

			query.setParameter("cpf", cpf);
			query.setParameter("senha", senha);

			return (Funcionario) query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RepositoryException("Problema ao pesquisar funcionario");
		}

	}

	public Funcionario findByIdPessoaFisica(int id) throws RepositoryException {
		try {
			StringBuffer jpql = new StringBuffer();
			jpql.append("SELECT ");
			jpql.append("  f ");
			jpql.append("FROM ");
			jpql.append("  Funcionario f ");
			jpql.append("WHERE ");
			jpql.append("  f.pessoaFisica.id = :id ");

			Query query = getEntityManager().createQuery(jpql.toString());
			query.setParameter("id", id);

			return (Funcionario) query.getSingleResult();
		} catch (NoResultException e) {
			System.out.println(e.getMessage());
			return null;

		} catch (Exception e) {
			e.printStackTrace();
			throw new RepositoryException("Erro ao executar o findByNome.");
		}
	}

	@SuppressWarnings("unchecked")
	public List<Funcionario> findByNome(String nome) throws RepositoryException {
		try {
			StringBuffer jpsql = new StringBuffer();
			jpsql.append("SELECT ");
			jpsql.append("  f ");
			jpsql.append("FROM ");
			jpsql.append("  Funcionario f ");
			jpsql.append("WHERE ");
			jpsql.append("  LOWER(f.pessoaFisica.nome) ");
			jpsql.append("LIKE ");
			jpsql.append("  LOWER(:nome) ");

			Query query = getEntityManager().createQuery(jpsql.toString());
			query.setParameter("nome", "%" + nome + "%");

			return query.getResultList();

		} catch (Exception e) {
			System.out.println("Erro ao executar o método de find.");
			e.printStackTrace();
			throw new RepositoryException("Erro ao buscar os dados");
		}
	}

	@SuppressWarnings("unchecked")
	public List<Funcionario> findAllByCpf(String cpf) throws RepositoryException {
		try {
			StringBuffer jpsql = new StringBuffer();
			jpsql.append("SELECT ");
			jpsql.append("  f ");
			jpsql.append("FROM ");
			jpsql.append("  Funcionario f ");
			jpsql.append("WHERE ");
			jpsql.append("  f.pessoaFisica.cpf LIKE :cpf ");

			Query query = getEntityManager().createQuery(jpsql.toString());
			query.setParameter("cpf", "%" + cpf + "%");

			return query.getResultList();
		} catch (NoResultException e) {
			return null;
		} catch (Exception e) {
			throw new RepositoryException("Erro ao buscar CPF");
		}
	}

	public Funcionario findByCpf(String cpf) throws RepositoryException {
		try {
			StringBuffer jpsql = new StringBuffer();
			jpsql.append("SELECT ");
			jpsql.append("  f ");
			jpsql.append("FROM ");
			jpsql.append("  Funcionario f ");
			jpsql.append("WHERE ");
			jpsql.append("  f.pessoaFisica.cpf LIKE :cpf ");

			Query query = getEntityManager().createQuery(jpsql.toString());
			query.setParameter("cpf", cpf);

			return (Funcionario) query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		} catch (Exception e) {
			throw new RepositoryException("Erro ao buscar CPF");
		}
	}

	@SuppressWarnings("unchecked")
	public List<Funcionario> findByEmail(String email) throws RepositoryException {
		try {
			StringBuffer jpsql = new StringBuffer();
			jpsql.append("SELECT ");
			jpsql.append("  f ");
			jpsql.append("FROM ");
			jpsql.append("  Funcionario f ");
			jpsql.append("WHERE ");
			jpsql.append("  LOWER(f.pessoaFisica.email) ");
			jpsql.append("LIKE LOWER(:email) ");

			Query query = getEntityManager().createQuery(jpsql.toString());
			query.setParameter("email", "%" + email + "%");

			return query.getResultList();

		} catch (Exception e) {
			throw new RepositoryException("Erro ao buscar email");
		}
	}

	public Funcionario findOneByEmailAndCpf(String email, String cpf) throws RepositoryException {
		try {
			StringBuffer jpql = new StringBuffer();
			jpql.append("SELECT ");
			jpql.append("  f ");
			jpql.append("FROM ");
			jpql.append("  Funcionario f ");
			jpql.append("WHERE ");
			jpql.append("  f.pessoaFisica.email LIKE :email ");
			jpql.append("AND ");
			jpql.append("  f.pessoaFisica.cpf LIKE :cpf ");

			Query query = getEntityManager().createQuery(jpql.toString());
			query.setParameter("email", email);
			query.setParameter("cpf", cpf);

			return (Funcionario) query.getSingleResult();
		} catch (NoResultException e) {
			System.out.println(e.getMessage());
			return null;

		} catch (Exception e) {
			e.printStackTrace();
			throw new RepositoryException("Erro ao executar o findByNome.");
		}
	}

	@SuppressWarnings("unchecked")
	public List<Funcionario> findByTelefone(String telefone) throws RepositoryException {
		try {
			StringBuffer jpsql = new StringBuffer();
			jpsql.append("SELECT ");
			jpsql.append("  f ");
			jpsql.append("FROM ");
			jpsql.append("  Funcionario f ");
			jpsql.append("WHERE ");
			jpsql.append("  LOWER(f.pessoaFisica.telefone) ");
			jpsql.append("LIKE LOWER(:telefone) ");

			Query query = getEntityManager().createQuery(jpsql.toString());
			query.setParameter("telefone", "%" + telefone + "%");

			return query.getResultList();

		} catch (Exception e) {
			throw new RepositoryException("Erro ao buscar telefone");
		}
	}

	@SuppressWarnings("unchecked")
	public List<Funcionario> findBySalario(String salario) throws RepositoryException {
		try {
			StringBuffer jpsql = new StringBuffer();
			jpsql.append("SELECT ");
			jpsql.append("  f ");
			jpsql.append("FROM ");
			jpsql.append("  Funcionario f ");
			jpsql.append("WHERE ");
			jpsql.append("  LOWER(f.salario <) ");
			jpsql.append("LIKE LOWER(:telefone) ");

			Query query = getEntityManager().createQuery(jpsql.toString());
			query.setParameter("salario", "%" + salario + "%");

			return query.getResultList();

		} catch (Exception e) {
			throw new RepositoryException("Erro ao buscar telefone");
		}
	}

	public String findOneByEmail(String email) throws RepositoryException {
		try {
			StringBuffer jpsql = new StringBuffer();
			jpsql.append("SELECT ");
			jpsql.append("  f.pessoaFisica.email ");
			jpsql.append("FROM ");
			jpsql.append("  Funcionario f ");
			jpsql.append("WHERE ");
			jpsql.append("  f.pessoaFisica.email ");
			jpsql.append("LIKE :email ");

			Query query = getEntityManager().createQuery(jpsql.toString());
			query.setParameter("email", email);

			return (String) query.getSingleResult();
		} catch (NoResultException e) {
			System.out.println(e.getMessage());
			return null;
		} catch (Exception e) {
			throw new RepositoryException("Erro ao buscar email");
		}
	}
}
