package login;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;


import entities.User;
import enumerations.Role;
import lombok.Getter;
import lombok.Setter;
import services.Implementations.UserService;
import services.Interfaces.IUserServiceLocal;

@ManagedBean
@SessionScoped

@Getter
@Setter
public class UserBean implements Serializable {

	private static final long serialVersionUID = -5027299396318962247L;

	private int idUser;
	private String firstname;
	private String lastname;
	private String password;
	private String email;
	private Boolean isActif;
	private Role role;
	private List<User> users;

	private User us;
	private Integer useroyeIdToBeUpdated;
	private User[] users2;
	
	@EJB
	IUserServiceLocal ius = new UserService();

	
	public void addUser() {
		ius.addUser(new User(firstname, lastname, email, password, isActif, role));
	}
	public List<User> getUsers() {
		users = ius.getAllUser();
		return users;
		}
	
	
	
	public void deleteUser(int id) {
		
		
			ius.deleteUser(id);

	}
	public void displayuseroye(User user)
	{
	this.setLastname(user.getLastname());
	this.setFirstname(user.getFirstname());
	this.setIsActif(user.isActiv());
	this.setEmail(user.getEmail());
	this.setRole(user.getRole());
	this.setEmail(user.getEmail());
	this.setPassword(user.getPassword());
	this.setUseroyeIdToBeUpdated(user.getIdUser());
	}
	@EJB
	UserService fms;
	
	public void updateUser()
	{ fms.updateUser(new User(useroyeIdToBeUpdated, firstname, lastname, email,password,  isActif, role)); }
	

}