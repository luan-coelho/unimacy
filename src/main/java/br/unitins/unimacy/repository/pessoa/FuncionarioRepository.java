package br.unitins.unimacy.repository.pessoa;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import br.unitins.unimacy.exception.RepositoryException;
import br.unitins.unimacy.model.pessoa.Funcionario;
import br.unitins.unimacy.repository.Repository;

public class FuncionarioRepository extends Repository<Funcionario> {

	public Funcionario validarLogin(Funcionario funcionario) throws RepositoryException {
		try {
			StringBuffer jpsql = new StringBuffer();
			jpsql.append("SELECT ");
			jpsql.append(" f ");
			jpsql.append("FROM ");
			jpsql.append(" Funcionario f ");
			jpsql.append("WHERE ");
			jpsql.append(" f.pessoaFisica.email = :email ");
			jpsql.append("AND ");
			jpsql.append(" f.pessoaFisica.senha = :senha ");

			Query query = getEntityManager().createQuery(jpsql.toString());

			query.setParameter("email", funcionario.getPessoaFisica().getEmail());
			query.setParameter("senha", funcionario.getPessoaFisica().getSenha());

			return (Funcionario) query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		} catch (Exception e) {
			// mandando pro console o exception gerado
			e.printStackTrace();
			// repassando a excecao para quem vai executar o metodo
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
			jpsql.append("f ");
			jpsql.append("FROM ");
			jpsql.append("Funcionario f ");
			jpsql.append("WHERE ");
			jpsql.append("LOWER(f.pessoaFisica.nome) ");
			jpsql.append("LIKE ");
			jpsql.append("LOWER(:nome) ");

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
			jpsql.append("f ");
			jpsql.append("FROM ");
			jpsql.append("Funcionario f ");
			jpsql.append("WHERE ");
			jpsql.append("f.pessoaFisica.cpf LIKE :cpf ");

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
			jpsql.append("f ");
			jpsql.append("FROM ");
			jpsql.append("Funcionario f ");
			jpsql.append("WHERE ");
			jpsql.append("f.pessoaFisica.cpf LIKE :cpf ");

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
			jpsql.append("f ");
			jpsql.append("FROM ");
			jpsql.append("Funcionario f ");
			jpsql.append("WHERE ");
			jpsql.append("LOWER(f.pessoaFisica.email) ");
			jpsql.append("LIKE LOWER(:email) ");

			Query query = getEntityManager().createQuery(jpsql.toString());
			query.setParameter("email", "%" + email + "%");

			return query.getResultList();

		} catch (Exception e) {
			throw new RepositoryException("Erro ao buscar email");
		}
	}

	public Funcionario findOneByEmail(String email) throws RepositoryException {
		try {
			StringBuffer jpql = new StringBuffer();
			jpql.append("SELECT ");
			jpql.append("f ");
			jpql.append("FROM ");
			jpql.append("Funcionario f ");
			jpql.append("WHERE ");
			jpql.append("f.pessoaFisica.email LIKE :email ");

			Query query = getEntityManager().createQuery(jpql.toString());
			query.setParameter("email", email);

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
			jpsql.append("f ");
			jpsql.append("FROM ");
			jpsql.append("Funcionario f ");
			jpsql.append("WHERE ");
			jpsql.append("LOWER(f.pessoaFisica.telefone) ");
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
			jpsql.append("f ");
			jpsql.append("FROM ");
			jpsql.append("Funcionario f ");
			jpsql.append("WHERE ");
			jpsql.append("LOWER(f.salario <) ");
			jpsql.append("LIKE LOWER(:telefone) ");

			Query query = getEntityManager().createQuery(jpsql.toString());
			query.setParameter("salario", "%" + salario + "%");

			return query.getResultList();

		} catch (Exception e) {
			throw new RepositoryException("Erro ao buscar telefone");
		}
	}
}
