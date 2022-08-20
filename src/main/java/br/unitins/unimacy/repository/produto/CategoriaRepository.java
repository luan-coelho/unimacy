package br.unitins.unimacy.repository.produto;

import java.util.List;

import br.unitins.unimacy.exception.RepositoryException;
import br.unitins.unimacy.model.produto.Categoria;
import br.unitins.unimacy.repository.Repository;
import jakarta.persistence.Query;

public class CategoriaRepository extends Repository<Categoria> {

	@SuppressWarnings("unchecked")
	public List<Object[]> findByNomeNativeSql(String nome) throws RepositoryException {
		try {
			StringBuffer sql = new StringBuffer();
			sql.append("SELECT ");
			sql.append("  c.id, ");
			sql.append("  c.nome, ");
			sql.append("  c.ativo ");
			sql.append("FROM ");
			sql.append("  Categoria c ");
			sql.append("WHERE ");
			sql.append("  c.nome LIKE :nome ");

			Query query = getEntityManager().createNativeQuery(sql.toString());
			query.setParameter("nome", "%" + nome + "%");

			return query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RepositoryException("Erro ao executar o findByNomeSQL.");
		}
	}
}
