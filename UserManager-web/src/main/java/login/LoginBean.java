package login;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import entities.User;
import lombok.Getter;
import lombok.Setter;
import services.Implementations.UserService;
import services.Interfaces.IUserServiceLocal;

@ManagedBean
@SessionScoped
@Setter
@Getter
public class LoginBean implements Serializable {

	private static final long serialVersionUID = 1825334962047210120L;
	public String login;
	public String password;
	public User user;
	public Boolean loggedIn;

	@EJB
	IUserServiceLocal us = new UserService();

	public String doLogin() {
		String navigateTo = "null";
		user = us.getUserByEmailAndPassword(login, password);
		if (user != null) {
		switch (user.getRole()) {
		case administrateur:
			navigateTo = "/pages/chahnez/afficherDomain?faces-redirect=true";
			loggedIn = true;
			break;
		case employe:
			navigateTo = "/template/EmployeSpace?faces-redirect=true";
			loggedIn = true;
			break;
		case rh:
			navigateTo = "/template/RhSpace?faces-redirect=true";
			loggedIn = true;
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
	
}
