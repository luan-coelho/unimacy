package br.unitins.unimacy.model.venda;

import java.io.Serial;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import br.unitins.unimacy.model.DefaultEntity;
import br.unitins.unimacy.model.pessoa.Cliente;
import br.unitins.unimacy.model.pessoa.Funcionario;
import br.unitins.unimacy.model.venda.pagamento.Pagamento;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Venda extends DefaultEntity {

	@Serial
	private static final long serialVersionUID = 1L;

	private BigDecimal valorTotalVenda;

	@ManyToOne
	private Cliente cliente;

	@ManyToOne
	private Funcionario funcionario;

	@OneToMany(cascade = CascadeType.ALL)
	private List<ProdutoVenda> produtoVenda;

	@ManyToOne(cascade = CascadeType.ALL)
	private Pagamento pagamento;

	public Venda() {
		// TODO Auto-generated constructor stub
	}

	public Venda(LocalDateTime dataHoraVenda, BigDecimal valorTotalVenda, Cliente cliente, Funcionario funcionario,
			List<ProdutoVenda> produtoVenda, Pagamento pagamento) {
		this.valorTotalVenda = valorTotalVenda;
		this.cliente = cliente;
		this.funcionario = funcionario;
		this.produtoVenda = produtoVenda;
		this.pagamento = pagamento;
	}

	public Venda(Pagamento pagamento) {
		this.pagamento = pagamento;
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

	public List<ProdutoVenda> getProdutoVenda() {
		return produtoVenda;
	}

	public void setProdutoVenda(List<ProdutoVenda> produtoVenda) {
		this.produtoVenda = produtoVenda;
	}

	public Pagamento getPagamento() {
		return pagamento;
	}

	public void setPagamento(Pagamento pagamento) {
		this.pagamento = pagamento;
	}

}
