package br.unitins.unimacy.repository;

import javax.persistence.Query;

import br.unitins.unimacy.model.Cliente;

public class ClienteRepository extends Repository<Cliente> {
	
	public Cliente findByCpf(String cpf) {
		Cliente cliente = null;

		try {
			Query query = getEntityManager().createQuery("Select c FROM PessoaFisica c WHERE c.cpf LIKE :cpf");
			query.setParameter("cpf", "%" + cpf + "%");

			cliente = (Cliente) query.getResultList();

		} catch (Exception e) {
			System.out.println("Erro ao executar o método de find.");
			e.printStackTrace();
		}

		return cliente;
	}
}
	
//	public boolean cpfExiste(String cpf) {
//		try {
//			Query query = getEntityManager().createQuery("Select c FROM PessoaFisica c WHERE c.cpf LIKE :cpf");
//			query.setParameter("cpf", "%" + cpf + "%");
//
//			Cliente cliente = (Cliente) query.getResultList();
//			return true;
//
//		} catch (Exception e) {
//			System.out.println("Erro ao executar o método de find.");
//			e.printStackTrace();
//		}
//
//		return false;
//	}
