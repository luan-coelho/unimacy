package br.unitins.unimacy.model.venda;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import br.unitins.unimacy.model.DefaultEntity;
import br.unitins.unimacy.model.produto.Produto;

@Entity
public class ProdutoVenda extends DefaultEntity {

	private static final long serialVersionUID = -8367286864500225366L;

	private BigDecimal valorUnitario;
	private Integer quantidade;

	@ManyToOne
	private Produto produto;

	public ProdutoVenda() {
		super();
	}

	public ProdutoVenda(BigDecimal valorUnitario, Integer quantidade, Produto produto) {
		super();
		this.valorUnitario = valorUnitario;
		this.quantidade = quantidade;
		this.produto = produto;
	}

	public BigDecimal getValorUnitario() {
		return valorUnitario;
	}

	public void setValorUnitario(BigDecimal valorUnitario) {
		this.valorUnitario = valorUnitario;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

}
