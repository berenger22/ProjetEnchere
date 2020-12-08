package fr.byoim.encheres.filters;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.byoim.encheres.bo.Utilisateur;

/**
 * Servlet Filter implementation class restrictionFilters
 */
@WebFilter(urlPatterns = { "/nouvelleVente", "/modificationVente", "/ProfilUtilisateur", "/afficherProfil",
		"/nouvelleVente", "/modificationVente", "/detailVente", "/modification", "/suppressionArticle",
		"/suppressionUtilisateur" })
public class restrictionFilters implements Filter {

	/**
	 * Default constructor.
	 */
	public restrictionFilters() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;

		HttpSession session = httpRequest.getSession();
		Utilisateur utilisateur = (Utilisateur) session.getAttribute("sessionUser");
		// Si l'utilisateur n'est pas dans la session(pas connecté) alors il est
		// redirigé vers la page de connexion si il essaie pas passer par l'URL
		if (utilisateur == null) {
			RequestDispatcher rd = httpRequest.getRequestDispatcher("/WEB-INF/JSP/connexion.jsp");
			rd.forward(httpRequest, httpResponse);
		}

		// pass the request along the filter chain
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
