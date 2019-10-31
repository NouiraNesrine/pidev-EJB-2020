package login;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;

import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import entities.Contrat;
import entities.User;
import lombok.Getter;
import lombok.Setter;
import services.Implementations.ContratService;
import services.Implementations.UserService;
import services.Interfaces.IContratServiceLocal;
import services.Interfaces.IUserServiceLocal;

@ManagedBean(eager=true)
@ViewScoped
@Setter
@Getter
public class ContratBean implements Serializable {

	private static final long serialVersionUID = -2110584799387220007L;
	private int idContrat;
	private Date dateDebut;
	private float salaire;
	private Contrat co;
	private String typeContrat;
	private List<Contrat> contrats;
	private List<User> employes;
	public static Contrat selectedCar;
	private User selectedEmp;
	@ManagedProperty(value = "#{loginBean}")
	private LoginBean lb;
	@ManagedProperty("#{carService}")
	private ContratService service;
	private String value;
	private User u;
	int idu;
	public static Contrat con;
	
	@EJB
	IContratServiceLocal ics = new ContratService();
	@EJB
	IUserServiceLocal ius = new UserService();
	public List<Contrat> getContrats() {
		contrats = ics.getAllContrat();
		return contrats;
  
	}
	public String a(Contrat c) {
		/*ius.affecterContratAEmploye(idc, ide);*/
		String navigate="null";
		this.co=new Contrat();
		this.co=c;
		return navigate = "/template/ListEmploye?faces-redirect=true";
		  
	}
	
	public String affecte() {
		
		System.out.println("bbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbb+"+ContratBean.selectedCar.getIdContrat());
		String navigate="null";
		ius.affecterContratAEmploye(selectedCar.getIdContrat(), selectedEmp.getIdUser());
		return navigate="/template/Contrats?faces-redirect=true";
		
	}
	
	public List<User> getEmployes() {
		employes = ius.getEmploye(); 
		return employes;

	}
	
	public String addContrat() {
		String navigateTo="null";
		Date currentDate = new Date();
		Calendar c = Calendar.getInstance();
	    c.setTime(currentDate);
	    c.set(Calendar.HOUR_OF_DAY, 0);
	    c.set(Calendar.MINUTE, 0);
	    c.set(Calendar.SECOND, 0);
	    c.set(Calendar.MILLISECOND, 0);
	    Date todayDate = c.getTime();
		if(dateDebut.before(todayDate)) {
			FacesContext.getCurrentInstance().addMessage("form1:j_idt90", new FacesMessage("Date invalid"));
		}
		else {
			ics.addContrat(new Contrat(dateDebut, typeContrat, salaire, lb.getUser()));
			return navigateTo = "/template/Contrats?faces-redirect=true";
		}
		
		return navigateTo;
	}
	
	public void delContrat(int id) {
		
			ics.removeContrat(id);
		
	}

	@PostConstruct
	public void init() {
		contrats = getContrats();
	}

	public Contrat getSelectedCar() {
		return selectedCar;
	}
	
	public void setSelectedEmp(User selectedEmp) {
		
		this.selectedEmp = selectedEmp;
		
	}
	public User getSelectedEmp() {
		return selectedEmp;
	}

	public void setSelectedCar(Contrat selectedCar) {
		this.selectedCar = selectedCar;
	}
	
	public void setService(ContratService service) {
		this.service = service;
	}

}
