package filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.unitins.unimacy.model.pessoa.Funcionario;

@WebFilter(filterName = "SecurityFilter", urlPatterns = { "/pages/*" })
public class SecurityFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest servletRequest = (HttpServletRequest) request;

		HttpSession session = servletRequest.getSession(false);

		Funcionario funcionarioLogado = null;
		if (session != null)
			funcionarioLogado = (Funcionario) session.getAttribute("funcionarioLogado");

		if (funcionarioLogado == null) {
			((HttpServletResponse) response).sendRedirect("/Unimacy/faces/login.xhtml");
		}
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

		Filter.super.init(filterConfig);
		System.out.println("Filtro SecurityFilter inicializado.");
	}
}