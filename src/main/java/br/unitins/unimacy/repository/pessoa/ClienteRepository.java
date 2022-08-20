package br.unitins.unimacy.repository.pessoa;

import java.util.List;

import br.unitins.unimacy.exception.RepositoryException;
import br.unitins.unimacy.model.pessoa.Cliente;
import br.unitins.unimacy.repository.Repository;
import jakarta.persistence.NoResultException;
import jakarta.persistence.Query;

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
			jpsql.append("  LOWER(c.pessoaFisica.nome) ");
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
	public List<Object[]> findAllClientePfByNomeNativeQuery(String nome) throws RepositoryException {
		try {
			StringBuffer jpsql = new StringBuffer();
			jpsql.append("SELECT ");
			jpsql.append("  c.id, ");
			jpsql.append("  pf.nome, ");
			jpsql.append("  pf.sobrenome, ");
			jpsql.append("  pf.cpf, ");
			jpsql.append("  c.ativo ");
			jpsql.append("FROM ");
			jpsql.append("  Cliente c, PessoaFisica pf ");
			jpsql.append("WHERE ");
			jpsql.append("  LOWER(pf.nome) ");
			jpsql.append("LIKE ");
			jpsql.append("	LOWER(:nome) ");
			jpsql.append("AND c.pessoa_id = pf.id");

			Query query = getEntityManager().createNativeQuery(jpsql.toString());
			query.setParameter("nome", "%" + nome + "%");

			return query.getResultList();
		} catch (Exception e) {
			System.out.println("Erro ao executar o método de find.");
			e.printStackTrace();
			throw new RepositoryException("Erro ao buscar clientes PF por nome.");
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> findAllClientePfByCpfNativeQuery(String cpf) throws RepositoryException {
		try {
			StringBuffer jpsql = new StringBuffer();
			jpsql.append("SELECT ");
			jpsql.append("  c.id, ");
			jpsql.append("  pf.nome, ");
			jpsql.append("  pf.sobrenome, ");
			jpsql.append("  pf.cpf, ");
			jpsql.append("  c.ativo ");
			jpsql.append("FROM ");
			jpsql.append("  Cliente c, PessoaFisica pf ");
			jpsql.append("WHERE ");
			jpsql.append("  LOWER(pf.cpf) ");
			jpsql.append("LIKE ");
			jpsql.append("	LOWER(:cpf) ");
			jpsql.append("AND c.pessoa_id = pf.id");
			
			Query query = getEntityManager().createNativeQuery(jpsql.toString());
			query.setParameter("cpf", "%" + cpf + "%");
			
			return query.getResultList();
		} catch (Exception e) {
			System.out.println("Erro ao executar o método de find.");
			e.printStackTrace();
			throw new RepositoryException("Erro ao buscar clientes PF por nome.");
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> findAllClientePfByEmailNativeQuery(String email) throws RepositoryException {
		try {
			StringBuffer jpsql = new StringBuffer();
			jpsql.append("SELECT ");
			jpsql.append("  c.id, ");
			jpsql.append("  pf.nome, ");
			jpsql.append("  pf.sobrenome, ");
			jpsql.append("  pf.cpf, ");
			jpsql.append("  c.ativo ");
			jpsql.append("FROM ");
			jpsql.append("  Cliente c, PessoaFisica pf, Pessoa p ");
			jpsql.append("WHERE ");
			jpsql.append("  LOWER(p.email) ");
			jpsql.append("LIKE ");
			jpsql.append("	LOWER(:email) ");
			jpsql.append("AND c.pessoa_id = pf.id");
			
			Query query = getEntityManager().createNativeQuery(jpsql.toString());
			query.setParameter("email", "%" + email + "%");
			
			return query.getResultList();
		} catch (Exception e) {
			System.out.println("Erro ao executar o método de find.");
			e.printStackTrace();
			throw new RepositoryException("Erro ao buscar clientes PF por nome.");
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> findAllClientePfByTelefoneNativeQuery(String telefone) throws RepositoryException {
		try {
			StringBuffer jpsql = new StringBuffer();
			jpsql.append("SELECT ");
			jpsql.append("  c.id, ");
			jpsql.append("  pf.nome, ");
			jpsql.append("  pf.sobrenome, ");
			jpsql.append("  pf.cpf, ");
			jpsql.append("  c.ativo ");
			jpsql.append("FROM ");
			jpsql.append("  Cliente c, PessoaFisica pf, Pessoa p ");
			jpsql.append("WHERE ");
			jpsql.append("  LOWER(p.telefone) ");
			jpsql.append("LIKE ");
			jpsql.append("	LOWER(:telefone) ");
			jpsql.append("AND c.pessoa_id = pf.id");
			
			Query query = getEntityManager().createNativeQuery(jpsql.toString());
			query.setParameter("telefone", "%" + telefone + "%");
			
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
	
	public Cliente findByIdPessoaFisica(int id) throws RepositoryException {
		try {
			StringBuffer jpql = new StringBuffer();
			jpql.append("SELECT ");
			jpql.append("  c ");
			jpql.append("FROM ");
			jpql.append("  Cliente c ");
			jpql.append("WHERE ");
			jpql.append("  c.pessoa.id = :id ");

			Query query = getEntityManager().createQuery(jpql.toString());
			query.setParameter("id", id);

			return (Cliente) query.getSingleResult();
		} catch (NoResultException e) {
			System.out.println(e.getMessage());
			return null;

		} catch (Exception e) {
			e.printStackTrace();
			throw new RepositoryException("Erro ao executar o findByNome.");
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
	
	@SuppressWarnings("unchecked")
	public List<Object[]> findAllClientePJByNomeNativeQuery(String nome) throws RepositoryException {
		try {
			StringBuffer jpsql = new StringBuffer();
			jpsql.append("SELECT ");
			jpsql.append("  c.id, ");
			jpsql.append("  pj.nomefantasia, ");
			jpsql.append("  pj.cnpj, ");
			jpsql.append("  c.ativo ");
			jpsql.append("FROM ");
			jpsql.append("  Cliente c, PessoaJuridica pj ");
			jpsql.append("WHERE ");
			jpsql.append("  LOWER(pj.nomefantasia) ");
			jpsql.append("LIKE ");
			jpsql.append("	LOWER(:nome) ");
			jpsql.append("AND c.pessoa_id = pj.id");

			Query query = getEntityManager().createNativeQuery(jpsql.toString());
			query.setParameter("nome", "%" + nome + "%");

			return query.getResultList();
		} catch (Exception e) {
			System.out.println("Erro ao executar o método de find.");
			e.printStackTrace();
			throw new RepositoryException("Erro ao buscar clientes Pessoa Jurídica por nome.");
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> findAllClientePJByCnpjNativeQuery(String cnpj) throws RepositoryException {
		try {
			StringBuffer jpsql = new StringBuffer();
			jpsql.append("SELECT ");
			jpsql.append("  c.id, ");
			jpsql.append("  pj.nomefantasia, ");
			jpsql.append("  pj.cnpj, ");
			jpsql.append("  c.ativo ");
			jpsql.append("FROM ");
			jpsql.append("  Cliente c, PessoaJuridica pj ");
			jpsql.append("WHERE ");
			jpsql.append("  LOWER(pj.cnpj) ");
			jpsql.append("LIKE ");
			jpsql.append("	LOWER(:cnpj) ");
			jpsql.append("AND c.pessoa_id = pj.id");
			
			Query query = getEntityManager().createNativeQuery(jpsql.toString());
			query.setParameter("cnpj", "%" + cnpj + "%");
			
			return query.getResultList();
		} catch (Exception e) {
			System.out.println("Erro ao executar o método de find.");
			e.printStackTrace();
			throw new RepositoryException("Erro ao buscar clientes Pessoa Jurídica por cnpj.");
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> findAllClientePjByEmailNativeQuery(String email) throws RepositoryException {
		try {
			StringBuffer jpsql = new StringBuffer();
			jpsql.append("SELECT DISTINCT ");
			jpsql.append("  c.id, ");
			jpsql.append("  pj.nomefantasia, ");
			jpsql.append("  pj.cnpj, ");
			jpsql.append("  c.ativo ");
			jpsql.append("FROM ");
			jpsql.append("  Cliente c, PessoaJuridica pj, Pessoa p ");
			jpsql.append("WHERE ");
			jpsql.append("  LOWER(p.email) ");
			jpsql.append("LIKE ");
			jpsql.append("	LOWER(:email) ");
			jpsql.append("AND c.pessoa_id = pj.id");
			
			Query query = getEntityManager().createNativeQuery(jpsql.toString());
			query.setParameter("email", "%" + email + "%");
			
			return query.getResultList();
		} catch (Exception e) {
			System.out.println("Erro ao executar o método de find.");
			e.printStackTrace();
			throw new RepositoryException("Erro ao buscar clientes Pessoa Jurídica por email.");
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> findAllClientePjByTelefoneNativeQuery(String telefone) throws RepositoryException {
		try {
			StringBuffer jpsql = new StringBuffer();
			jpsql.append("SELECT DISTINCT ");
			jpsql.append("  c.id, ");
			jpsql.append("  pj.nomefantasia, ");
			jpsql.append("  pj.cnpj, ");
			jpsql.append("  c.ativo ");
			jpsql.append("FROM ");
			jpsql.append("  Cliente c, PessoaJuridica pj, Pessoa p ");
			jpsql.append("WHERE ");
			jpsql.append("  LOWER(p.telefone) ");
			jpsql.append("LIKE ");
			jpsql.append("	LOWER(:telefone) ");
			jpsql.append("AND c.pessoa_id = pj.id");
			
			Query query = getEntityManager().createNativeQuery(jpsql.toString());
			query.setParameter("telefone", "%" + telefone + "%");
			
			return query.getResultList();
		} catch (Exception e) {
			System.out.println("Erro ao executar o método de find.");
			e.printStackTrace();
			throw new RepositoryException("Erro ao buscar clientes Pessoa Jurídica por telefone.");
		}
	}
}