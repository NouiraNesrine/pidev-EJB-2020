package workspace;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import entities.Mission;
import entities.Skills;
import entities.User;
import enumerations.State;
import login.LoginBean;
import lombok.Getter;
import lombok.Setter;
import services.Implementations.MissionService;
import services.Interfaces.IMissionServiceLocal;
import services.Interfaces.IUserServiceLocal;

@ManagedBean(name="missionBean")
@SessionScoped
@Setter
@Getter
public class MissionBean implements Serializable{

	private static final long serialVersionUID = 182533496077L;
	
	
	private int refrence ;
	private Date date_start;
	private Date date_finish;
	private String title;
	private String description;
	private String client;
	private String place_intervention;
	private double estimated_budget;
	private Boolean isRembursable;
	private Boolean isAnnulationRisque;
	private List<Skills> skillsRequired;
	private List<Skills> allSkills;
	private List<Skills> selectedSkills;
	
	private String lol;
	
	@EJB
	IMissionServiceLocal missionService = new MissionService();
	
	 @PostConstruct
	    public void init() {
		 this.allSkills = new ArrayList<Skills>();
			this.allSkills =new ArrayList<Skills>(missionService.getAllSkills()); 
			
	    }
	public String goToAddMission() {
		
		String navigateTo="null";
		navigateTo = "/template/AddMission?faces-redirect=true";
		return navigateTo;
	}
	
public String addMission() throws ParseException {
		
		String navigateTo="null";
		//get User
		LoginBean lg = new LoginBean();
	//	HttpServletRequest req = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
	//	String date_s = req.getParameter("arrivalAirport");
	//	String date_f = req.getParameter("arrivalAirport");
		FacesContext context = FacesContext.getCurrentInstance();
        Map<String, String> paramMap = context.getExternalContext().getRequestParameterMap();
        String dateStart = paramMap.get("date_start");
        String dateFinish = paramMap.get("date_finish");
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
      	date_start = formatter.parse(dateStart);
      	date_finish = formatter.parse(dateFinish);
      	Set<Skills> set = new HashSet<Skills>(selectedSkills);
		Mission mission = new Mission(date_start, date_finish, title, description, client, place_intervention, estimated_budget, isRembursable, true, isAnnulationRisque, lg.userConn, set);
		missionService.addMission(mission);
		navigateTo = "/template/ManagerSpace?faces-redirect=true";
		return navigateTo;
	}
public String goToUpdateMission() {
	
	String navigateTo="null";
	Mission mission = missionService.getMissionById(5);
	this.client=mission.getClient();
	this.title=mission.getTitle();
	this.description=mission.getDescription();
	this.estimated_budget=mission.getEstimated_budget();
	this.isAnnulationRisque=mission.getIsAnnulationRisque();
	this.isRembursable=mission.getIsRembursable();
	this.place_intervention=mission.getPlace_intervention();
	this.selectedSkills=new ArrayList<Skills>(mission.getSkillsRequired());
	this.date_start=mission.getDate_start();
	this.date_finish=mission.getDate_finish();
	this.refrence=mission.getRefrence();
	navigateTo = "/template/UpdateMission?faces-redirect=true";
	return navigateTo;
}

public String updateThisMission() throws ParseException {
	
	String navigateTo="null";

	LoginBean lg = new LoginBean();
	FacesContext context = FacesContext.getCurrentInstance();
    Map<String, String> paramMap = context.getExternalContext().getRequestParameterMap();
    String dateStart = paramMap.get("date_start");
    String dateFinish = paramMap.get("date_finish");
    SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
  	date_start = formatter.parse(dateStart);
  	date_finish = formatter.parse(dateFinish);
  	Set<Skills> set = new HashSet<Skills>(selectedSkills);
	Mission mission = new Mission(date_start, date_finish, title, description, client, place_intervention, estimated_budget, isRembursable, true, isAnnulationRisque, lg.userConn, set);
	mission.setRefrence(refrence);
	missionService.updateMission(mission);
	//missionService.removeMission(refrence);
	navigateTo = "/template/ManagerSpace?faces-redirect=true";
	return navigateTo;
}
	
	
}
