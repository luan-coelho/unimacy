package br.unitins.unimacy.controller.venda;

import javax.faces.view.ViewScoped;

import br.unitins.unimacy.controller.Controller;
import br.unitins.unimacy.model.venda.pagamento.Pagamento;
import br.unitins.unimacy.repository.venda.PagamentoRepository;
import jakarta.inject.Named;

import java.io.Serial;

@Named
@ViewScoped
public class PagamentoController extends Controller<Pagamento> {

    @Serial
    private static final long serialVersionUID = 1L;

    public PagamentoController() {
        super(new PagamentoRepository());
    }

    @Override
    public Pagamento getEntity() {
        return null;
    }
}