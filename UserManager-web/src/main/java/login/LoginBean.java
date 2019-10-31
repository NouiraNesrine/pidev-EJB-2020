package login;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import entities.Mission;
import entities.Skills;
import entities.User;
import lombok.Getter;
import lombok.Setter;
import services.Implementations.MissionService;
import services.Implementations.UserService;
import services.Interfaces.IMissionServiceLocal;
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
	public static User userConn;
	
	
	@EJB
	IUserServiceLocal us = new UserService();
	
	
	
	public String doLogin() {
		
		String navigateTo = "null";
		user = us.getUserByEmailAndPassword(login, password);
		if (user != null) {
			userConn = user;
		switch (user.getRole()) {
		case administrateur:
			
			navigateTo = "/template/ManagerMissionSpace?faces-redirect=true";
			loggedIn = true;
			break;
		case employe:
			navigateTo = "/template/EmployeeMissionSpace?faces-redirect=true";
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
