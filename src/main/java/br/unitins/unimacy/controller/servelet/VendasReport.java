package br.unitins.unimacy.controller.servelet;

import java.io.Serial;
import java.sql.Connection;
import java.util.HashMap;

import javax.servlet.annotation.WebServlet;

import br.unitins.unimacy.application.JDBCUtil;


@WebServlet("/relatoriovendas.xhtml")
public class VendasReport extends ReportServlet {

    @Serial
    private static final long serialVersionUID = 1L;

    @Override
    public String getArquivoJasper() {
        return "vendas.jasper";
    }

    @Override
    public Connection getConnection() {
        return JDBCUtil.getConnection();
    }

    @Override
    public HashMap<String, Class<?>> getParametros() {
        return null;
    }
}
