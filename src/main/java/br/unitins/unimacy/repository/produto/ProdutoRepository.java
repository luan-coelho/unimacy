package br.unitins.unimacy.repository.produto;

import java.util.List;

import br.unitins.unimacy.exception.RepositoryException;
import br.unitins.unimacy.model.produto.Produto;
import br.unitins.unimacy.repository.Repository;
import jakarta.persistence.Query;

public class ProdutoRepository extends Repository<Produto> {

	@SuppressWarnings("unchecked")
	public List<Produto> findByCategoria(String nome) throws RepositoryException {
		try {
			StringBuffer jpsql = new StringBuffer();
			jpsql.append("SELECT ");
			jpsql.append("  p ");
			jpsql.append("FROM ");
			jpsql.append("  Produto p ");
			jpsql.append("WHERE ");
			jpsql.append("  LOWER(p.categoria.nome) ");
			jpsql.append("LIKE LOWER(:nome) ");

			Query query = getEntityManager().createQuery(jpsql.toString());
			query.setParameter("nome", "%" + nome + "%");

			return query.getResultList();

		} catch (Exception e) {
			System.out.println("Erro ao executar o método de find de categoria");
			e.printStackTrace();
			throw new RepositoryException("Problema ao buscar categoria");
		}
	}

	@SuppressWarnings("unchecked")
	public List<Produto> findByLote(String lote) throws RepositoryException {
		try {
			StringBuffer jpsql = new StringBuffer();
			jpsql.append("SELECT ");
			jpsql.append("  p ");
			jpsql.append("FROM ");
			jpsql.append("  Produto p ");
			jpsql.append("WHERE ");
			jpsql.append("  LOWER(p.lote) ");
			jpsql.append("LIKE ");
			jpsql.append("  LOWER(:lote) ");

			Query query = getEntityManager().createQuery(jpsql.toString());
			query.setParameter("lote", "%" + lote + "%");

			return query.getResultList();

		} catch (Exception e) {
			throw new RepositoryException("Problema ao salvar.");
		}
	}

	@SuppressWarnings("unchecked")
	public List<Produto> findByFornecedor(String nome) throws RepositoryException {
		List<Produto> lista = null;

		try {
			StringBuffer jpsql = new StringBuffer();
			jpsql.append("SELECT ");
			jpsql.append("  p ");
			jpsql.append("FROM ");
			jpsql.append("  Produto p ");
			jpsql.append("WHERE ");
			jpsql.append("  LOWER(p.fornecedor.pessoaJuridica.nomeFantasia) ");
			jpsql.append("LIKE LOWER(:nome) ");

			Query query = getEntityManager().createQuery(jpsql.toString());
			query.setParameter("nome", "%" + nome + "%");

			lista = query.getResultList();

		} catch (Exception e) {
			System.out.println("Erro ao executar o método de find de fornecedor");
			e.printStackTrace();
			return null;
		}

		return lista;
	}

	public Produto findOneByNome(String nome) throws RepositoryException {
		Produto produto = null;

		try {
			StringBuffer jpsql = new StringBuffer();
			jpsql.append("SELECT ");
			jpsql.append("  p ");
			jpsql.append("FROM ");
			jpsql.append("  Produto p ");
			jpsql.append("WHERE ");
			jpsql.append("  LOWER(p.lote) ");
			jpsql.append("LIKE LOWER(:nome) ");

			Query query = getEntityManager().createQuery(jpsql.toString());
			query.setParameter("nome", nome);

			produto = (Produto) query.getSingleResult();

		} catch (Exception e) {
			System.out.println("Erro ao executar o método de find de nome");
			e.printStackTrace();
			return null;
		}

		return produto;
	}

	@SuppressWarnings("unchecked")
	public List<Object[]> findByNomeNativeSql(String nome) throws RepositoryException {
		try {
			StringBuffer sql = new StringBuffer();
			sql.append("SELECT DISTINCT ");
			sql.append("  p.id, ");
			sql.append("  p.nome, ");
			sql.append("  p.quantestoque, ");
			sql.append("  p.preco, ");
			sql.append("  c.ativo ");
			sql.append("FROM ");
			sql.append("  Produto p ");
			sql.append("WHERE ");
			sql.append("  p.nome LIKE :nome");

			Query query = getEntityManager().createNativeQuery(sql.toString());
			query.setParameter("nome", "%" + nome + "%");

			return query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RepositoryException("Erro ao executar o findByNomeSQL.");
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> findByCategoriaNativeSql(String nome) throws RepositoryException {
		try {
			StringBuffer sql = new StringBuffer();
			sql.append("SELECT ");
			sql.append("  p.id, ");
			sql.append("  p.nome, ");
			sql.append("  p.quantestoque, ");
			sql.append("  p.preco, ");
			sql.append("  c.nome AS nome_categoria, ");
			sql.append("  c.ativo ");
			sql.append("FROM ");
			sql.append("  Produto p, ");
			sql.append("  Categoria c ");
			sql.append("WHERE ");
			sql.append("  c.nome LIKE :nome");

			Query query = getEntityManager().createNativeQuery(sql.toString());
			query.setParameter("nome", "%" + nome + "%");

			return query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RepositoryException("Erro ao executar o findByNomeSQL.");
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> findByLoteNativeSql(String lote) throws RepositoryException {
		try {
			StringBuffer sql = new StringBuffer();
			sql.append("SELECT ");
			sql.append("  p.id, ");
			sql.append("  p.nome, ");
			sql.append("  p.quantestoque, ");
			sql.append("  p.preco, ");
			sql.append("  c.nome AS nome_categoria, ");
			sql.append("  c.ativo ");
			sql.append("FROM ");
			sql.append("  Produto p, ");
			sql.append("  Categoria c ");
			sql.append("WHERE ");
			sql.append("  p.lote LIKE :lote");
			
			Query query = getEntityManager().createNativeQuery(sql.toString());
			query.setParameter("lote", "%" + lote + "%");
			
			return query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RepositoryException("Erro ao executar o findByNomeSQL.");
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> findByFornecedorNativeSql(String nome) throws RepositoryException {
		try {
			StringBuffer sql = new StringBuffer();
			sql.append("SELECT ");
			sql.append("  p.id, ");
			sql.append("  p.nome, ");
			sql.append("  p.quantestoque, ");
			sql.append("  p.preco, ");
			sql.append("  c.nome AS nome_categoria, ");
			sql.append("  c.ativo ");
			sql.append("FROM ");
			sql.append("  Produto p, ");
			sql.append("  Categoria c, ");
			sql.append("  Fornecedor f, ");
			sql.append("WHERE ");
			sql.append("  f.pessoaJuridica.nome LIKE :nome");
			
			Query query = getEntityManager().createNativeQuery(sql.toString());
			query.setParameter("nome", "%" + nome + "%");
			
			return query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RepositoryException("Erro ao executar o findByNomeSQL.");
		}
	}

	@SuppressWarnings("unchecked")
	public List<Object[]> findByIdNativeSql(Integer id) throws RepositoryException {
		try {
			StringBuffer sql = new StringBuffer();
			sql.append("SELECT ");
			sql.append("  p.id, ");
			sql.append("  p.nome, ");
			sql.append("  p.quantestoque, ");
			sql.append("  p.preco, ");
			sql.append("  c.nome AS nome_categoria ");
			sql.append("FROM ");
			sql.append("  Produto p, ");
			sql.append("  Categoria c ");
			sql.append("WHERE ");
			sql.append("  p.id = :id");

			Query query = getEntityManager().createNativeQuery(sql.toString());
			query.setParameter("id", id);

			return query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RepositoryException("Erro ao executar o findByNomeSQL.");
		}
	}

}