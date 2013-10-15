package util;

import java.io.IOException;
import javax.faces.context.FacesContext;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Giovani
 */
public class LoginFiltro implements Filter{

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpSession sessao = ((HttpServletRequest) request).getSession();
        if(Boolean.parseBoolean(sessao.getAttribute("usuariologado").toString()) == false || !sessao.getAttribute("usuarioTipo").toString().equals("1")){
            ((HttpServletResponse) response).sendRedirect("/provaonline/index.jsf");
        }
        else{
            chain.doFilter(request, response);
        }
    }

    @Override
    public void destroy() {
    }
    
  }