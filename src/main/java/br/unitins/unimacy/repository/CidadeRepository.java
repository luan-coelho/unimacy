package br.unitins.unimacy.repository;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import br.unitins.unimacy.model.pessoa.endereco.Cidade;

public class CidadeRepository extends Repository<Cidade> {

	public Cidade findOneResultByNome(String nomeCidade, String nomeEstado) throws NoResultException {
		Cidade cidade = null;

		try {
			StringBuffer jpsql = new StringBuffer();
			jpsql.append("SELECT ");
			jpsql.append("c ");
			jpsql.append("FROM ");
			jpsql.append("Cidade c ");
			jpsql.append("WHERE ");
			jpsql.append("c.nome LIKE :cidade ");
			jpsql.append("AND ");
			jpsql.append("c.estado.nome ");
			jpsql.append("LIKE ");
			jpsql.append(":estado ");

			Query query = getEntityManager().createQuery(jpsql.toString());
			query.setParameter("cidade", "%" + nomeCidade + "%");
			query.setParameter("estado", "%" + nomeEstado + "%");

			cidade = (Cidade) query.getSingleResult();
		}catch (NoResultException e) {
			throw new NoResultException();
		} 
		catch (Exception e) {
			System.out.println("Erro ao executar o método de find.");
			e.printStackTrace();
			return null;
		}

		if (cidade != null)
			return cidade;
		return null;
	}
}