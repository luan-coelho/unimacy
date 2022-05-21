package br.unitins.unimacy.repository;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.OptimisticLockException;
import javax.persistence.Query;

import br.unitins.unimacy.application.JPAUtil;
import br.unitins.unimacy.exception.RepositoryException;
import br.unitins.unimacy.exception.VersionException;
import br.unitins.unimacy.model.DefaultEntity;

public class Repository<T extends DefaultEntity> {

	private EntityManager entityManager;

	public Repository() {
		super();
		setEntityManager(JPAUtil.getEntityManager());
	}

	public void save(T entity) throws RepositoryException, VersionException {
		try {
			getEntityManager().getTransaction().begin();
			getEntityManager().merge(entity);
			getEntityManager().getTransaction().commit();

		} catch (OptimisticLockException e) {
			// excecao do @version
			System.out.println("Problema com o controle de concorrencia.");
			e.printStackTrace();
			try {
				getEntityManager().getTransaction().rollback();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			throw new VersionException("As informações estão desatualizadas, atualize a página");
		} catch (Exception e) {
			System.out.println("Problema ao executar o save.");
			e.printStackTrace();
			try {
				getEntityManager().getTransaction().rollback();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			throw new RepositoryException("Problema ao salvar.");
		}

	}

	public void save(@SuppressWarnings("unchecked") T... entitys) throws RepositoryException {
		try {
			getEntityManager().getTransaction().begin();
			for (T entity : entitys) {
				getEntityManager().merge(entity);
			}
			getEntityManager().getTransaction().commit();
		} catch (Exception e) {
			System.out.println("Problema ao executar o save.");
			e.printStackTrace();
			try {
				getEntityManager().getTransaction().rollback();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			throw new RepositoryException("Problema ao salvar.");
		}
	}

	public void remove(T entity) throws RepositoryException {
		try {
			getEntityManager().getTransaction().begin();
			T obj = getEntityManager().merge(entity);
			getEntityManager().remove(obj);
			getEntityManager().getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			try {
				getEntityManager().getTransaction().rollback();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			throw new RepositoryException("Problema ao remover.");
		}
	}

	public T findById(int id) throws RepositoryException {
		try {
			// obtendo o tipo da classe de forma generica (a classe deve ser publica)
			final ParameterizedType type = (ParameterizedType) getClass().getGenericSuperclass();
			@SuppressWarnings("unchecked")
			Class<T> tClass = (Class<T>) (type).getActualTypeArguments()[0];

			T t = (T) getEntityManager().find(tClass, id);

			return t;
		} catch (Exception e) {
			System.out.println("Erro ao executar o método de find.");
			e.printStackTrace();
			throw new RepositoryException("Erro ao buscar os dados");
		}
	}

	@SuppressWarnings("unchecked")
	public List<T> findByNome(String nome) throws RepositoryException {
		try {
			final ParameterizedType type = (ParameterizedType) getClass().getGenericSuperclass();
			Class<T> tClass = (Class<T>) (type).getActualTypeArguments()[0];
			String classe = tClass.getSimpleName();

			StringBuffer jpsql = new StringBuffer();
			jpsql.append("SELECT ");
			jpsql.append("o ");
			jpsql.append("FROM ");
			jpsql.append(classe + " o ");
			jpsql.append("WHERE ");
			jpsql.append("LOWER(o.nome) ");
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
	public List<T> findAll() throws RepositoryException {
		try {
			// obtendo o tipo da classe de forma generica (a classe deve ser publica)
			final ParameterizedType type = (ParameterizedType) getClass().getGenericSuperclass();
			Class<T> tClass = (Class<T>) (type).getActualTypeArguments()[0];
			String classe = tClass.getSimpleName();

			StringBuffer jpsql = new StringBuffer();
			jpsql.append("SELECT ");
			jpsql.append("o ");
			jpsql.append("FROM ");
			jpsql.append(classe + " o ");

			Query query = getEntityManager().createQuery(jpsql.toString());

			return query.getResultList();

		} catch (Exception e) {
			System.out.println("Erro ao executar o método de find.");
			e.printStackTrace();
			throw new RepositoryException("Erro ao buscar os dados");
		}
	}

	protected EntityManager getEntityManager() {
		return entityManager;
	}

	private void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

}