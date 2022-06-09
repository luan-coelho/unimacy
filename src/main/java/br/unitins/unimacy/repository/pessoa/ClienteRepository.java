package br.unitins.unimacy.repository.pessoa;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import br.unitins.unimacy.exception.RepositoryException;
import br.unitins.unimacy.model.pessoa.Cliente;
import br.unitins.unimacy.repository.Repository;

public class ClienteRepository extends Repository<Cliente> {
	
	@SuppressWarnings("unchecked")
	public List<Cliente> findAllClientePfByNome(String nome) throws RepositoryException {
		try {
			StringBuffer jpsql = new StringBuffer();
			jpsql.append("SELECT ");
			jpsql.append("  c ");
			jpsql.append("FROM ");
			jpsql.append("  Cliente c ");
			jpsql.append("WHERE ");
			jpsql.append("  LOWER(c.pessoa.nome) ");
			jpsql.append("LIKE LOWER(:nome) ");

			Query query = getEntityManager().createQuery(jpsql.toString());
			query.setParameter("nome", "%" + nome + "%");

			return query.getResultList();
		} catch (Exception e) {
			System.out.println("Erro ao executar o método de find.");
			e.printStackTrace();
			throw new RepositoryException("Erro ao buscar clientes PF por nome.");
		}
	}

	@SuppressWarnings("unchecked")
	public List<Cliente> findAllClientePfByCpf(String cpf) throws RepositoryException {
		try {
			StringBuffer jpsql = new StringBuffer();
			jpsql.append("SELECT ");
			jpsql.append("c ");
			jpsql.append("FROM ");
			jpsql.append("Cliente c ");
			jpsql.append("WHERE ");
			jpsql.append("c.pessoaFisica.cpf LIKE :cpf ");

			Query query = getEntityManager().createQuery(jpsql.toString());
			query.setParameter("cpf", "%" + cpf + "%");

			return query.getResultList();
		} catch (NoResultException e) {
			return null;
		} catch (Exception e) {
			throw new RepositoryException("Erro ao buscar CPF");
		}
	}

	public Cliente findClientePfByCpf(String cpf) throws RepositoryException {
		try {
			StringBuffer jpsql = new StringBuffer();
			jpsql.append("SELECT ");
			jpsql.append("c ");
			jpsql.append("FROM ");
			jpsql.append("Cliente c ");
			jpsql.append("WHERE ");
			jpsql.append("c.pessoaFisica.cpf LIKE :cpf ");

			Query query = getEntityManager().createQuery(jpsql.toString());
			query.setParameter("cpf", cpf);

			return (Cliente) query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		} catch (Exception e) {
			throw new RepositoryException("Erro ao buscar CPF");
		}
	}

	@SuppressWarnings("unchecked")
	public List<Cliente> findAllClientePfByEmail(String email) throws RepositoryException {
		try {
			StringBuffer jpsql = new StringBuffer();
			jpsql.append("SELECT ");
			jpsql.append("c ");
			jpsql.append("FROM ");
			jpsql.append("Cliente c ");
			jpsql.append("WHERE ");
			jpsql.append("LOWER(c.pessoaFisica.email) ");
			jpsql.append("LIKE LOWER(:email) ");

			Query query = getEntityManager().createQuery(jpsql.toString());
			query.setParameter("email", "%" + email + "%");

			return query.getResultList();

		} catch (Exception e) {
			throw new RepositoryException("Erro ao buscar email");
		}
	}

	@SuppressWarnings("unchecked")
	public List<Cliente> findAllClientePfByTelefone(String telefone) throws RepositoryException {
		try {
			StringBuffer jpsql = new StringBuffer();
			jpsql.append("SELECT ");
			jpsql.append("c ");
			jpsql.append("FROM ");
			jpsql.append("Cliente c ");
			jpsql.append("WHERE ");
			jpsql.append("LOWER(c.pessoaFisica.telefone) ");
			jpsql.append("LIKE LOWER(:telefone) ");

			Query query = getEntityManager().createQuery(jpsql.toString());
			query.setParameter("telefone", "%" + telefone + "%");

			return query.getResultList();

		} catch (Exception e) {
			throw new RepositoryException("Erro ao buscar telefone");
		}
	}

	// Pessoa Juridica

	@SuppressWarnings("unchecked")
	public List<Cliente> findAllClientePjByNome(String nome) throws RepositoryException {
		try {
			StringBuffer jpsql = new StringBuffer();
			jpsql.append("SELECT ");
			jpsql.append("c ");
			jpsql.append("FROM ");
			jpsql.append("Cliente c ");
			jpsql.append("WHERE ");
			jpsql.append("LOWER(c.pessoaJuridica.nomeFantasia) ");
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

	public Cliente findClientePjByCpnj(String cnpj) {
		Cliente cliente = null;

		try {
			StringBuffer jpsql = new StringBuffer();
			jpsql.append("SELECT ");
			jpsql.append("c ");
			jpsql.append("FROM ");
			jpsql.append("Cliente c ");
			jpsql.append("WHERE ");
			jpsql.append("c.pessoaJuridica.cnpj LIKE :cnpj ");

			Query query = getEntityManager().createQuery(jpsql.toString());
			query.setParameter("cnpj", cnpj);

			cliente = (Cliente) query.getSingleResult();

		} catch (NoResultException e) {
			return null;
		} catch (Exception e) {
			System.out.println("Erro ao executar o método de find.");
			e.printStackTrace();
		}

		return cliente;
	}

	@SuppressWarnings("unchecked")
	public List<Cliente> findAllClientePjByTelefone(String telefone) throws RepositoryException {
		try {
			StringBuffer jpsql = new StringBuffer();
			jpsql.append("SELECT ");
			jpsql.append("c ");
			jpsql.append("FROM ");
			jpsql.append("Cliente c ");
			jpsql.append("WHERE ");
			jpsql.append("LOWER(c.pessoaJuridica.telefone) ");
			jpsql.append("LIKE LOWER(:telefone) ");

			Query query = getEntityManager().createQuery(jpsql.toString());
			query.setParameter("telefone", "%" + telefone + "%");

			return query.getResultList();

		} catch (Exception e) {
			throw new RepositoryException("Erro ao buscar telefone");
		}
	}

	@SuppressWarnings("unchecked")
	public List<Cliente> findAllClienteByEmail(String email) throws RepositoryException {
		try {
			StringBuffer jpsql = new StringBuffer();
			jpsql.append("SELECT ");
			jpsql.append("c ");
			jpsql.append("FROM ");
			jpsql.append("Cliente c ");
			jpsql.append("WHERE ");
			jpsql.append("LOWER(c.pessoaJuridica.email) ");
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
	public List<Cliente> findAllClientePjByCnpj(String cnpj) throws RepositoryException {
		try {
			StringBuffer jpsql = new StringBuffer();
			jpsql.append("SELECT ");
			jpsql.append("c ");
			jpsql.append("FROM ");
			jpsql.append("Cliente c ");
			jpsql.append("WHERE ");
			jpsql.append("c.pessoaJuridica.cnpj LIKE :cnpj ");

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