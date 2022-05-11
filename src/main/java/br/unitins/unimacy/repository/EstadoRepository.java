package br.unitins.unimacy.repository;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import br.unitins.unimacy.model.pessoa.endereco.Estado;

public class EstadoRepository extends Repository<Estado> {

	public Estado findOneResultByNome(String nomeEstado) throws NoResultException {
		Estado estado = null;

		try {
			StringBuffer jpsql = new StringBuffer();
			jpsql.append("SELECT ");
			jpsql.append("e ");
			jpsql.append("FROM ");
			jpsql.append("Estado e ");
			jpsql.append("WHERE ");
			jpsql.append("e.nome ");
			jpsql.append("LIKE ");
			jpsql.append(":estado ");

			Query query = getEntityManager().createQuery(jpsql.toString());
			query.setParameter("estado", "%" + nomeEstado + "%");

			estado = (Estado) query.getSingleResult();
		}catch (NoResultException e) {
			throw new NoResultException();
		} 
		catch (Exception e) {
			System.out.println("Erro ao executar o método de find.");
			e.printStackTrace();
			return null;
		}

		if (estado != null)
			return estado;
		return null;
	}
}