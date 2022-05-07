package br.unitins.unimacy.repository;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import br.unitins.unimacy.model.PessoaJuridica;

public class PessoaJuridicaRepository extends Repository<PessoaJuridica> {

	public PessoaJuridica findByCpnj(String cnpj) {
		PessoaJuridica pessoa = null;

		try {
			StringBuffer jpsql = new StringBuffer();
			jpsql.append("SELECT ");
			jpsql.append("p ");
			jpsql.append("FROM ");
			jpsql.append("PessoaJuridica p ");
			jpsql.append("WHERE ");
			jpsql.append("p.cnpj LIKE :cnpj ");

			Query query = getEntityManager().createQuery(jpsql.toString());
			query.setParameter("cnpj", cnpj);

			pessoa = (PessoaJuridica) query.getSingleResult();

		} catch (NoResultException e) {
			return null;
		} catch (Exception e) {
			System.out.println("Erro ao executar o método de find.");
			e.printStackTrace();
		}

		return pessoa;
	}
}
