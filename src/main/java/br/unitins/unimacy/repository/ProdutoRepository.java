package br.unitins.unimacy.repository;

import java.util.List;

import javax.persistence.Query;

import br.unitins.unimacy.exception.RepositoryException;
import br.unitins.unimacy.model.produto.Produto;

public class ProdutoRepository extends Repository<Produto>{
	
	@SuppressWarnings("unchecked")
	public List <Produto> findByCategoria(String nome) throws RepositoryException{
		try {
			StringBuffer jpsql = new StringBuffer();
			jpsql.append("SELECT ");
			jpsql.append("p ");
			jpsql.append("FROM ");
			jpsql.append("Produto p ");
			jpsql.append("WHERE ");
			jpsql.append("LOWER(p.categoria.nome) ");
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
	public List <Produto> findByLote(String lote) throws RepositoryException{
		try {
			StringBuffer jpsql = new StringBuffer();
			jpsql.append("SELECT ");
			jpsql.append("p ");
			jpsql.append("FROM ");
			jpsql.append("Produto p ");
			jpsql.append("WHERE ");
			jpsql.append("LOWER(p.lote) ");
			jpsql.append("LIKE ");
			jpsql.append("LOWER(:lote) ");
			
			Query query = getEntityManager().createQuery(jpsql.toString());
			query.setParameter("lote", "%" + lote + "%");

			return query.getResultList();

		} catch (Exception e) {
			throw new RepositoryException("Problema ao salvar.");
		}
	}

	@SuppressWarnings("unchecked")
	public List <Produto> findByFornecedor(String nome) throws RepositoryException{
		List <Produto> lista = null;
		
		try {
			StringBuffer jpsql = new StringBuffer();
			jpsql.append("SELECT ");
			jpsql.append("p ");
			jpsql.append("FROM ");
			jpsql.append("Produto p ");
			jpsql.append("WHERE ");
			jpsql.append("LOWER(p.fornecedor.pessoaJuridica.nomeFantasia) ");
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
	
	public Produto findOneByNome(String nome) throws RepositoryException{
		Produto produto = null;
		
		try {
			StringBuffer jpsql = new StringBuffer();
			jpsql.append("SELECT ");
			jpsql.append("p ");
			jpsql.append("FROM ");
			jpsql.append("Produto p ");
			jpsql.append("WHERE ");
			jpsql.append("LOWER(p.lote) ");
			jpsql.append("LIKE LOWER(:nome) ");
			
			Query query = getEntityManager().createQuery(jpsql.toString());
			query.setParameter("nome", "%" + nome + "%");

			produto = (Produto) query.getSingleResult();

		} catch (Exception e) {
			System.out.println("Erro ao executar o método de find de nome");
			e.printStackTrace();
			return null;
		}

		return produto;
	}

}