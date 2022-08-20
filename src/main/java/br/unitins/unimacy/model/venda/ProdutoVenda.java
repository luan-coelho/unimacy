package br.unitins.unimacy.model.venda;

import java.io.Serial;
import java.math.BigDecimal;

import br.unitins.unimacy.model.DefaultEntity;
import br.unitins.unimacy.model.produto.Produto;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;

@Entity
public class ProdutoVenda extends DefaultEntity {

    @Serial
    private static final long serialVersionUID = 1L;

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

    @Override
    public String toString() {
        return "ProdutoVenda [valorUnitario=" + valorUnitario + ", quantidade=" + quantidade + ", produto=" + produto + "]";
    }
}
