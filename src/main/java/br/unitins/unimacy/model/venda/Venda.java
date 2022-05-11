package br.unitins.unimacy.model.venda;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Entity;

import br.unitins.unimacy.model.DefaultEntity;
import br.unitins.unimacy.model.pessoa.Cliente;
import br.unitins.unimacy.model.pessoa.Funcionario;
import br.unitins.unimacy.model.venda.pagamento.Pagamento;

@Entity
public class Venda extends DefaultEntity {

	private static final long serialVersionUID = 5808795270444976757L;

	private LocalDateTime dataHoraVenda;
	private BigDecimal valorTotalVenda;
	private Cliente cliente;
	private Funcionario funcionario;
	private ProdutoVenda produtoVenda;
	private Pagamento pagamento;

	public Venda() {
		// TODO Auto-generated constructor stub
	}

	public Venda(LocalDateTime dataHoraVenda, BigDecimal valorTotalVenda, Cliente cliente, Funcionario funcionario,
			ProdutoVenda produtoVenda, Pagamento pagamento) {
		super();
		this.dataHoraVenda = dataHoraVenda;
		this.valorTotalVenda = valorTotalVenda;
		this.cliente = cliente;
		this.funcionario = funcionario;
		this.produtoVenda = produtoVenda;
		this.pagamento = pagamento;
	}

	public LocalDateTime getDataHoraVenda() {
		return dataHoraVenda;
	}

	public void setDataHoraVenda(LocalDateTime dataHoraVenda) {
		this.dataHoraVenda = dataHoraVenda;
	}

	public BigDecimal getValorTotalVenda() {
		return valorTotalVenda;
	}

	public void setValorTotalVenda(BigDecimal valorTotalVenda) {
		this.valorTotalVenda = valorTotalVenda;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	public ProdutoVenda getProdutoVenda() {
		return produtoVenda;
	}

	public void setProdutoVenda(ProdutoVenda produtoVenda) {
		this.produtoVenda = produtoVenda;
	}

	public Pagamento getPagamento() {
		return pagamento;
	}

	public void setPagamento(Pagamento pagamento) {
		this.pagamento = pagamento;
	}

}
