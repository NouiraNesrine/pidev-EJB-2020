package timesheetBean;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import entities.Activity;
import entities.User;
import enumerations.statutsActivity;
import lombok.Getter;
import lombok.Setter;
import services.Implementations.ActivityService;





@Getter
@Setter
@ManagedBean(name = "activityBean")
@SessionScoped 
public class activityBean implements Serializable{
	private static final long serialVersionUID = 1563578L;

	private String nom;
	private String description;
	private int NombreHeuresEstimer;
	private int NombreHeuresTravailler;
	private statutsActivity statut;
	private List<Activity> activities;
	private User  T= new User(1);
	private Date datedebut ;
	private Date dateFin;
	//private String dom;


	
	  
	 

	
@EJB
ActivityService activityService;
	
	public String addActivity() 
	
	
	{ 
		
		
		
		
		String	navigateTo = "/template/showActivityManager?faces-redirect=true";
		activityService.ajouterActivity(new Activity(nom, description, NombreHeuresEstimer, statut,T));
		
		
	return 	navigateTo;
	}
	
	public List<Activity> getActivities() {
		activities = activityService.getAllActivities();
		return activities; 
	}
	
	public void removeActivity(int activityId) 
	{ activityService.deleteActivityById(activityId); }

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	private Integer employeIdToBeUpdated;
	
	
	public String displayActivity(Activity empl) 
	{ 
	String	navigateTo = "/template/updateActivityManager?faces-redirect=true";
		
		
		this.setNom(empl.getNom()); 
	this.setDescription(empl.getDescription());
	 this.setNombreHeuresEstimer(empl.getNombreHeuresEstimer());
	this.setStatut(empl.getStatut());
	this.setEmployeIdToBeUpdated(empl.getId());
	return navigateTo;
	
	}
	
	

	public String updateActivity() 
	{ String	navigateTo = "/template/showActivityManager?faces-redirect=true";
		
		activityService.updateActivity(new Activity(employeIdToBeUpdated,nom, description, NombreHeuresEstimer, statut)); 
	
	return navigateTo;
	}
	
	public String displayStartActivity(Activity empl) 
	{ 
	String	navigateTo = "/template/updateStartActivity?faces-redirect=true";
		
		
		
	this.setStatut(empl.getStatut());
	this.setEmployeIdToBeUpdated(empl.getId());
	return navigateTo;
	
	}
	
	
	
	
	public String StartActivity() 
	{
		
		String	navigateTo = "/template/showActivityEmploye?faces-redirect=true";
	//String	navigateTo = "/template/showActivityEmploye?faces-redirect=true";
		
		activityService.updateStartActivity(new Activity(employeIdToBeUpdated,datedebut,statut));
		return navigateTo;
	
	}
	
	public String EndActivity() 
	{ String	navigateTo = "/template/showActivityEmploye?faces-redirect=true";
		
		activityService.EndActivityEmploye(new Activity(employeIdToBeUpdated,NombreHeuresTravailler,dateFin,statut)); 
	
	return navigateTo;
	}
	
	
	 public List<Activity> rechercheParnomm() {
		return activityService.rechechertopicc(nom);
	 }
	
	 public String emchi() 
	 {
	 	
	 	String	navigateTo = "/template/rechercheActivityEmploye?faces-redirect=true";
		//return "rechercheActivityEmploye?faces-redirect=true";

	 	
	 	return navigateTo;

	 }

	
	
	/*  public List<Activity> findClientsByName(Activity empl){
			   
		  enumerations.statutsActivity ct=null;
		  criterias=new HashMap<String, String>();
		  if(idUser!=null){criterias.put("idUser",idUser);}
		  if(this.setNom(empl.getNom())!=null){criterias.put("nom",nom+"%");}
		  if(description!=null){criterias.put("description", description+"%");}
		  if(NombreHeuresEstimer!=null){criterias.put("NombreHeuresEstimer", NombreHeuresEstimer+"%");}
		  if(NombreHeuresTravailler!=null){criterias.put("NombreHeuresTravailler", NombreHeuresTravailler);}
         if(statut!=null){criterias.put("statut", ct.valueOf(statut).toString());}
		//  if(clientCategory!=null){criterias.put("clientCategory", clientCategory);}
		List<Activity> c=activityService.getClientsByCriterias(criterias);
		return c;
		*/
	 
	 
	 
	 
	 

	  }
	
	
	  
	  
	


