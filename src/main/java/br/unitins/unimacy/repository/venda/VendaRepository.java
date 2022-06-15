package br.unitins.unimacy.repository.venda;

import java.util.List;

import javax.persistence.Query;

import br.unitins.unimacy.exception.RepositoryException;
import br.unitins.unimacy.model.venda.Venda;
import br.unitins.unimacy.repository.Repository;

public class VendaRepository extends Repository<Venda>{
	
	@SuppressWarnings("unchecked")
	public List<Object[]> findAllNativeSql() throws RepositoryException {
		try {
			StringBuffer sql = new StringBuffer();
			
			sql.append("SELECT ");
			sql.append("  v.datacadastro, ");
			sql.append("  v.valortotalvenda, ");
			sql.append("  pf2.nome AS funcionario, ");
			sql.append("  pf.nome AS Cliente ");
			sql.append("FROM ");
			sql.append("  Venda v, ");
			sql.append("  PessoaFisica pf, ");
			sql.append("  Cliente c, ");
			sql.append("  PessoaFisica pf2, ");
			sql.append("  Funcionario f ");
			sql.append("WHERE v.cliente_id = c.id ");
			sql.append("  AND c.pessoa_id = pf.id ");
			sql.append("  AND f.pessoafisica_id = pf2.id ");
			sql.append("ORDER BY v.id ");
			
			Query query = getEntityManager().createNativeQuery(sql.toString());

			return query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RepositoryException("Erro ao executar o findAll.");
		}
	}
}