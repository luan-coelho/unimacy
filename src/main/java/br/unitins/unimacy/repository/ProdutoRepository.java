package br.unitins.unimacy.repository;

import java.util.List;

import javax.persistence.Query;

import br.unitins.unimacy.exception.RepositoryException;
import br.unitins.unimacy.model.Produto;

public class ProdutoRepository extends Repository<Produto>{
	
	@SuppressWarnings("unchecked")
	public List <Produto> findByCategoria(String nome) throws RepositoryException{
		List <Produto> lista = null;
		
		try {
			StringBuffer jpsql = new StringBuffer();
			jpsql.append("SELECT ");
			jpsql.append("p ");
			jpsql.append("FROM ");
			jpsql.append("Produto p ");
			jpsql.append("WHERE ");
			jpsql.append("(SELECT LOWER(c.nome) ");
			jpsql.append("FROM Categoria c ");
			jpsql.append("WHERE ");
			jpsql.append("c.id = p.categoria_id) ");
			jpsql.append("LIKE LOWER(:nome) ");
			
			Query query = getEntityManager().createQuery(jpsql.toString());
			query.setParameter("nome", "%" + nome + "%");

			lista = query.getResultList();

		} catch (Exception e) {
			System.out.println("Erro ao executar o método de find.");
			e.printStackTrace();
			return null;
		}

		return lista;
	}
}