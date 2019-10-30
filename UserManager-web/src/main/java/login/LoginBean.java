package login;

import java.io.FileFilter;
import java.io.IOException;
import java.io.Serializable;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.application.ResourceHandler;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
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

import entities.User;
import lombok.Getter;
import lombok.Setter;
import services.Implementations.UserService;
import services.Interfaces.IUserServiceLocal;

@ManagedBean
@SessionScoped
@Setter
@Getter
public class LoginBean implements Serializable, Filter {

	private static final long serialVersionUID = 1825334962047210120L;
	public String login;
	public String password;
	public User user;
	

	@EJB
	IUserServiceLocal us = new UserService();

	public String doLogin() {
		String navigateTo = "null";
		user = us.getUserByEmailAndPassword(login, password);
		if (user != null) {
		switch (user.getRole()) {
		case administrateur:
			navigateTo = "/pages/chahnez/afficherDomain?faces-redirect=true";
			
			break;
		case employe:
			navigateTo = "/template/EmployeSpace?faces-redirect=true";
			
			break;
		case rh:
			navigateTo = "/template/RhSpace?faces-redirect=true";
			
			break;
		default:
			break;
		}}
		 else {
			
			FacesContext.getCurrentInstance().addMessage("form:btn", new FacesMessage("Bad Credentials"));
			
		}
		return navigateTo;
	}

	public String doLogout() {
		
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		System.out.println("done");
		return "/loggin?faces-redirect=true";
	}

	public LoginBean() {
		super();
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;
		HttpSession session = request.getSession(false);
		String loginURI = request.getContextPath() + "/loggin.xhtml";

		boolean loggedIn = session != null && session.getAttribute("user") != null;
		boolean loginRequest = request.getRequestURI().equals(loginURI);
		boolean resourceRequest = request.getRequestURI()
				.startsWith(request.getContextPath() + ResourceHandler.RESOURCE_IDENTIFIER);

		if (loggedIn || loginRequest || resourceRequest) {
			chain.doFilter(request, response);
		} else {
			response.sendRedirect(loginURI);
		}
		
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}
	
}
