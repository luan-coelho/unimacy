package br.unitins.unimacy.repository;

import javax.persistence.Query;

import br.unitins.unimacy.model.PessoaFisica;

public class PessoaFisicaRepository extends Repository<PessoaFisica> {
	
	public PessoaFisica findByCpf(String cpf) {
		PessoaFisica pessoa = null;

		try {
			StringBuffer jpsql = new StringBuffer();
			jpsql.append("SELECT ");
			jpsql.append("p ");
			jpsql.append("FROM ");
			jpsql.append("PessoaFisica p ");
			jpsql.append("WHERE ");
			jpsql.append("p.cpf LIKE :cpf ");

			Query query = getEntityManager().createQuery(jpsql.toString());
			query.setParameter("cpf", "%" + cpf + "%");

			pessoa = (PessoaFisica) query.getSingleResult();

		} catch (Exception e) {
			System.out.println("Erro ao executar o método de find.");
			e.printStackTrace();
		}

		return pessoa;
	}
}
