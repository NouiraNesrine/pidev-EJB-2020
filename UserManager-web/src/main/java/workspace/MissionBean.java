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
import entities.UserMissionStats;
import enumerations.Role;
import enumerations.State;
import login.LoginBean;
import lombok.Getter;
import lombok.Setter;
import services.Implementations.MissionService;
import services.Implementations.UserMissionStatsService;
import services.Interfaces.IMissionServiceLocal;
import services.Interfaces.IUserMissionStatsService;
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
	private List<Skills> allSkills;
	private List<Skills> selectedSkills;
	private List<Skills> skillsRequired;
	private String st_date;
	private String ft_date;
	//manager
	private List<Mission> missionsToApprove;
	private List<Mission> missionsSupervisedByUser;
	//employee
	private List<Mission> missionsAppliedFor;
	private List<Mission> missionsParticpatedIn;
	//both
	private List<Mission> missionsPostedByUser;
	private List<Mission> missionsAvailable;
	private User postedBy;
	private String lol;
	private String posterFirstN,posterLastN;
	private State state;
	private List<Mission> misSerched;
	private List<User> allParticipants;
	private List<User> participantsSelected;
	@EJB
	IMissionServiceLocal missionService = new MissionService();
	@EJB
	IUserMissionStatsService statService = new UserMissionStatsService();
	
	
	 @PostConstruct
	    public void init() {
		 LoginBean lg = new LoginBean();
		 this.allSkills = new ArrayList<Skills>();
		 this.allSkills =new ArrayList<Skills>(missionService.getAllSkills()); 
		 this.missionsToApprove = new ArrayList<Mission>(missionService.getMissionsToApprove());
		 this.missionsAvailable = new ArrayList<Mission>(missionService.getMissionsAvailable(lg.userConn));
		 this.missionsSupervisedByUser = new ArrayList<Mission>(missionService.getMissionsSupervisedByUser(lg.userConn));
		 this.missionsParticpatedIn = new ArrayList<Mission>(missionService.getMissionsParticpatedIn(lg.userConn));
		 this.missionsAppliedFor = new ArrayList<Mission>(missionService.getMissionsAppliedFor(lg.userConn));
		 this.missionsPostedByUser = new ArrayList<Mission>(missionService.getMissionsPostedByUser(lg.userConn));
			
	    }
	 
		public String goToAddMission() {
			
			String navigateTo="null";
			navigateTo = "/template/AddMission?faces-redirect=true";
			return navigateTo;
		}
		public String goToMissionsToApprove() {
			
			String navigateTo="null";
			navigateTo = "/template/ManagerMissionSpace?faces-redirect=true";
			return navigateTo;
		}
		public String goToPostedMissions() {
			
			String navigateTo="null";
			navigateTo = "/template/ManagerMissionPosted?faces-redirect=true";
			return navigateTo;
		}
		public String goToPostedMissionsByUser() {
			
			String navigateTo="null";
			navigateTo = "/template/EmployeeMissionPosted?faces-redirect=true";
			return navigateTo;
		}
		public String goToSuprvisedMissions() {
			
			String navigateTo="null";
			navigateTo = "/template/ManagerMissionSuprvied?faces-redirect=true";
			return navigateTo;
		}
		public String goToAvailbaledMissions() {
			
			String navigateTo="null";
			navigateTo = "/template/EmployeeMissionSpace?faces-redirect=true";
			return navigateTo;
		}
		public String goToAppliedForMissions() {
			
			String navigateTo="null";
			navigateTo = "/template/EmployeeMissionAppliedFor?faces-redirect=true";
			return navigateTo;
		}
		public String goToParticipatedInMissions() {
			
			String navigateTo="null";
			navigateTo = "/template/EmployeeMissionParticipatedIn?faces-redirect=true";
			return navigateTo;
		}
		public String goToSearchForMissions() {
			String navigateTo="null";
			navigateTo = "/template/SearchForMission?faces-redirect=true";
			return navigateTo;
		}
	
	public String addMission() throws ParseException {
		
		String navigateTo="null";
		//get User
		LoginBean lg = new LoginBean();
		FacesContext context = FacesContext.getCurrentInstance();
        Map<String, String> paramMap = context.getExternalContext().getRequestParameterMap();
        String dateStart = paramMap.get("date_start");
        String dateFinish = paramMap.get("date_finish");
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
      	date_start = formatter.parse(dateStart);
      	date_finish = formatter.parse(dateFinish);
      	Set<Skills> set = new HashSet<Skills>(selectedSkills);
      	Mission mission = null;
      	
      	if(lg.userConn.getRole()==Role.administrateur) {
      		mission = new Mission(date_start, date_finish, title, description, client, place_intervention, estimated_budget, isRembursable, true, isAnnulationRisque, lg.userConn, set);
		missionService.addMission(mission);
		if(statService.getUserStats(lg.userConn)==null) {
			statService.addUserStats(lg.userConn);
		}
		statService.updateApplicationAmount(lg.userConn);
		navigateTo = "/template/ManagerMissionSpace?faces-redirect=true";
      	
      	}else {
      		
      		mission = new Mission(date_start, date_finish, title, description, client, place_intervention, estimated_budget, isRembursable, false, isAnnulationRisque, lg.userConn, set);
      		Set <User> s = new HashSet<User>();
      		s.add(lg.userConn);
      		mission.setParticipants(s);
      		Mission m = missionService.addMission(mission);
      		
      		//missionService.updateParticipants(m, lg.userConn);
      		if(statService.getUserStats(lg.userConn)==null) {
    			statService.addUserStats(lg.userConn);
    		}
      		statService.updateParticipantionAmount(lg.userConn);
      		navigateTo = "/template/EmployeeMissionSpace?faces-redirect=true";
      	}
      	
      	 this.missionsToApprove = new ArrayList<Mission>(missionService.getMissionsToApprove());
		 this.missionsAvailable = new ArrayList<Mission>(missionService.getMissionsAvailable(lg.userConn));
		 this.missionsSupervisedByUser = new ArrayList<Mission>(missionService.getMissionsSupervisedByUser(lg.userConn));
		 this.missionsParticpatedIn = new ArrayList<Mission>(missionService.getMissionsParticpatedIn(lg.userConn));
		 this.missionsAppliedFor = new ArrayList<Mission>(missionService.getMissionsAppliedFor(lg.userConn));
		 this.missionsPostedByUser = new ArrayList<Mission>(missionService.getMissionsPostedByUser(lg.userConn));
		return navigateTo;
	}
	

	public String goToUpdateMission(Mission m) throws ParseException {
	
		String navigateTo="null";
		Mission mission = missionService.getMissionById(m.getRefrence());
		this.client=mission.getClient();
		this.title=mission.getTitle();
		this.description=mission.getDescription();
		this.estimated_budget=mission.getEstimated_budget();
		this.isAnnulationRisque=mission.getIsAnnulationRisque();
		this.isRembursable=mission.getIsRembursable();
		this.place_intervention=mission.getPlace_intervention();
		this.selectedSkills=new ArrayList<Skills>(mission.getSkillsRequired());
		this.refrence=mission.getRefrence();
		SimpleDateFormat formatter = new SimpleDateFormat("ddMMyyyy");
		this.st_date=formatter.format(mission.getDate_start());
		this.ft_date =formatter.format(mission.getDate_finish());
		//this.st_date="20122019"; example
		//this.ft_date="25122019"; example
		navigateTo = "/template/UpdateMission?faces-redirect=true";
		return navigateTo;
	}
	public String goToMissionModelPosted(Mission m) throws ParseException {
		
		String navigateTo="null";
		Mission mission = missionService.getMissionById(m.getRefrence());
		this.client=mission.getClient();
		this.title=mission.getTitle();
		this.description=mission.getDescription();
		this.estimated_budget=mission.getEstimated_budget();
		this.isAnnulationRisque=mission.getIsAnnulationRisque();
		this.isRembursable=mission.getIsRembursable();
		this.place_intervention=mission.getPlace_intervention();
		this.selectedSkills=new ArrayList<Skills>(mission.getSkillsRequired());
		this.refrence=mission.getRefrence();
		this.postedBy = mission.getPostedBy();
		this.posterFirstN=postedBy.getFirstname();
		this.posterLastN=postedBy.getLastname();
		this.state=mission.getState();
		SimpleDateFormat formatter = new SimpleDateFormat("ddMMyyyy");
		this.st_date=formatter.format(mission.getDate_start());
		this.ft_date =formatter.format(mission.getDate_finish());
		navigateTo = "/template/MissionModelPosted?faces-redirect=true";
		return navigateTo;
	}
	public String goToMissionModelAvailbale(Mission m) throws ParseException {
		
		String navigateTo="null";
		LoginBean lg = new LoginBean();
		Mission mission = missionService.getMissionById(m.getRefrence());
		this.client=mission.getClient();
		this.title=mission.getTitle();
		this.description=mission.getDescription();
		this.estimated_budget=mission.getEstimated_budget();
		this.isAnnulationRisque=mission.getIsAnnulationRisque();
		this.isRembursable=mission.getIsRembursable();
		this.place_intervention=mission.getPlace_intervention();
		this.selectedSkills=new ArrayList<Skills>(mission.getSkillsRequired());
		this.refrence=mission.getRefrence();
		this.postedBy = mission.getPostedBy();
		this.posterFirstN=postedBy.getFirstname();
		this.posterLastN=postedBy.getLastname();
		this.state=mission.getState();
		SimpleDateFormat formatter = new SimpleDateFormat("ddMMyyyy");
		this.st_date=formatter.format(mission.getDate_start());
		this.ft_date =formatter.format(mission.getDate_finish());
		this.missionsToApprove = new ArrayList<Mission>(missionService.getMissionsToApprove());
		 this.missionsAvailable = new ArrayList<Mission>(missionService.getMissionsAvailable(lg.userConn));
		 this.missionsSupervisedByUser = new ArrayList<Mission>(missionService.getMissionsSupervisedByUser(lg.userConn));
		 this.missionsParticpatedIn = new ArrayList<Mission>(missionService.getMissionsParticpatedIn(lg.userConn));
		 this.missionsAppliedFor = new ArrayList<Mission>(missionService.getMissionsAppliedFor(lg.userConn));
		 this.missionsPostedByUser = new ArrayList<Mission>(missionService.getMissionsPostedByUser(lg.userConn));
		navigateTo = "/template/MissionModelAvailbale?faces-redirect=true";
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
		if(lg.userConn.getRole()==Role.administrateur) {
			navigateTo = "/template/ManagerMissionSpace?faces-redirect=true";
      	}else {
      		navigateTo = "/template/EmployeeMissionSpace?faces-redirect=true";
      	}
		this.missionsToApprove = new ArrayList<Mission>(missionService.getMissionsToApprove());
		 this.missionsAvailable = new ArrayList<Mission>(missionService.getMissionsAvailable(lg.userConn));
		 this.missionsSupervisedByUser = new ArrayList<Mission>(missionService.getMissionsSupervisedByUser(lg.userConn));
		 this.missionsParticpatedIn = new ArrayList<Mission>(missionService.getMissionsParticpatedIn(lg.userConn));
		 this.missionsAppliedFor = new ArrayList<Mission>(missionService.getMissionsAppliedFor(lg.userConn));
		 this.missionsPostedByUser = new ArrayList<Mission>(missionService.getMissionsPostedByUser(lg.userConn));
		return navigateTo;
	}
	
	public String updatePostedMission(int refrence) {
		
		String navigateTo="null";
		Mission mission = missionService.getMissionById(refrence);
		this.client=mission.getClient();
		this.title=mission.getTitle();
		this.description=mission.getDescription();
		this.estimated_budget=mission.getEstimated_budget();
		this.isAnnulationRisque=mission.getIsAnnulationRisque();
		this.isRembursable=mission.getIsRembursable();
		this.place_intervention=mission.getPlace_intervention();
		this.selectedSkills=new ArrayList<Skills>(mission.getSkillsRequired());
		this.refrence=mission.getRefrence();
		SimpleDateFormat formatter = new SimpleDateFormat("ddMMyyyy");
		this.st_date=formatter.format(mission.getDate_start());
		this.ft_date =formatter.format(mission.getDate_finish());
		//this.st_date="20122019"; example
		//this.ft_date="25122019"; example
		navigateTo = "/template/UpdateMission?faces-redirect=true";
		return navigateTo;
	}
	
	public String applyForMission(int refrence) {
		
		String navigateTo="null";
		LoginBean lg = new LoginBean();
		Mission mission = missionService.getMissionById(refrence);
  		mission.getParticipants().add(lg.userConn);
  		missionService.updateParticipants(mission);
  		if(statService.getUserStats(lg.userConn)==null) {
			statService.addUserStats(lg.userConn);
		}
  		statService.updateApplicationAmount(lg.userConn);
  		this.missionsToApprove = new ArrayList<Mission>(missionService.getMissionsToApprove());
		 this.missionsAvailable = new ArrayList<Mission>(missionService.getMissionsAvailable(lg.userConn));
		 this.missionsSupervisedByUser = new ArrayList<Mission>(missionService.getMissionsSupervisedByUser(lg.userConn));
		 this.missionsParticpatedIn = new ArrayList<Mission>(missionService.getMissionsParticpatedIn(lg.userConn));
		 this.missionsAppliedFor = new ArrayList<Mission>(missionService.getMissionsAppliedFor(lg.userConn));
		 this.missionsPostedByUser = new ArrayList<Mission>(missionService.getMissionsPostedByUser(lg.userConn));
		navigateTo = "/template/EmployeeMissionSpace?faces-redirect=true";
		
		return navigateTo;
	}
	public String goToMissionModelAppliedFor(Mission m) throws ParseException {
		
		String navigateTo="null";
		LoginBean lg = new LoginBean();
		Mission mission = missionService.getMissionById(m.getRefrence());
		this.client=mission.getClient();
		this.title=mission.getTitle();
		this.description=mission.getDescription();
		this.estimated_budget=mission.getEstimated_budget();
		this.isAnnulationRisque=mission.getIsAnnulationRisque();
		this.isRembursable=mission.getIsRembursable();
		this.place_intervention=mission.getPlace_intervention();
		this.selectedSkills=new ArrayList<Skills>(mission.getSkillsRequired());
		this.refrence=mission.getRefrence();
		this.postedBy = mission.getPostedBy();
		this.posterFirstN=postedBy.getFirstname();
		this.posterLastN=postedBy.getLastname();
		this.state=mission.getState();
		SimpleDateFormat formatter = new SimpleDateFormat("ddMMyyyy");
		this.st_date=formatter.format(mission.getDate_start());
		this.ft_date =formatter.format(mission.getDate_finish());
		
		navigateTo = "/template/MissionModelAppliedFor?faces-redirect=true";
		return navigateTo;
	}
	
	public String goToMissionModelParticipatedIn(Mission m) throws ParseException {
		
		String navigateTo="null";
		LoginBean lg = new LoginBean();
		Mission mission = missionService.getMissionById(m.getRefrence());
		this.client=mission.getClient();
		this.title=mission.getTitle();
		this.description=mission.getDescription();
		this.estimated_budget=mission.getEstimated_budget();
		this.isAnnulationRisque=mission.getIsAnnulationRisque();
		this.isRembursable=mission.getIsRembursable();
		this.place_intervention=mission.getPlace_intervention();
		this.selectedSkills=new ArrayList<Skills>(mission.getSkillsRequired());
		this.refrence=mission.getRefrence();
		this.postedBy = mission.getPostedBy();
		this.posterFirstN=postedBy.getFirstname();
		this.posterLastN=postedBy.getLastname();
		this.state=mission.getState();
		SimpleDateFormat formatter = new SimpleDateFormat("ddMMyyyy");
		this.st_date=formatter.format(mission.getDate_start());
		this.ft_date =formatter.format(mission.getDate_finish());
		
		navigateTo = "/template/MissionModelAppliedFor?faces-redirect=true";
		return navigateTo;
	}
	
	public String goToMissionModelToApprove(Mission m) throws ParseException {
		
		String navigateTo="null";
		LoginBean lg = new LoginBean();
		Mission mission = missionService.getMissionById(m.getRefrence());
		this.client=mission.getClient();
		this.title=mission.getTitle();
		this.description=mission.getDescription();
		this.estimated_budget=mission.getEstimated_budget();
		this.isAnnulationRisque=mission.getIsAnnulationRisque();
		this.isRembursable=mission.getIsRembursable();
		this.place_intervention=mission.getPlace_intervention();
		this.selectedSkills=new ArrayList<Skills>(mission.getSkillsRequired());
		this.refrence=mission.getRefrence();
		this.postedBy = mission.getPostedBy();
		this.posterFirstN=postedBy.getFirstname();
		this.posterLastN=postedBy.getLastname();
		this.state=mission.getState();
		SimpleDateFormat formatter = new SimpleDateFormat("ddMMyyyy");
		this.st_date=formatter.format(mission.getDate_start());
		this.ft_date =formatter.format(mission.getDate_finish());
		this.allParticipants=new ArrayList<User>(mission.getParticipants());
		navigateTo = "/template/MissionModelToApprove?faces-redirect=true";
		return navigateTo;
	}
	public String approveMission(int refrence) {
		
		String navigateTo="null";
		LoginBean lg = new LoginBean();
		Mission mission = missionService.getMissionById(refrence);
  		mission.setState(State.Approved);
  		missionService.updateState(mission,lg.userConn);
  		if(statService.getUserStats(lg.userConn)==null) {
			statService.addUserStats(lg.userConn);
		}
  		statService.updateParticipantionAmount(lg.userConn);
  		this.missionsToApprove = new ArrayList<Mission>(missionService.getMissionsToApprove());
		 this.missionsAvailable = new ArrayList<Mission>(missionService.getMissionsAvailable(lg.userConn));
		 this.missionsSupervisedByUser = new ArrayList<Mission>(missionService.getMissionsSupervisedByUser(lg.userConn));
		 this.missionsParticpatedIn = new ArrayList<Mission>(missionService.getMissionsParticpatedIn(lg.userConn));
		 this.missionsAppliedFor = new ArrayList<Mission>(missionService.getMissionsAppliedFor(lg.userConn));
		 this.missionsPostedByUser = new ArrayList<Mission>(missionService.getMissionsPostedByUser(lg.userConn));
		navigateTo = "/template/ManagerMissionSpace?faces-redirect=true";
		
		return navigateTo;
	}
	public String refuseMission(int refrence) {
		
		String navigateTo="null";
		LoginBean lg = new LoginBean();
		Mission mission = missionService.getMissionById(refrence);
  		mission.setState(State.Refused);
  		missionService.updateState(mission,lg.userConn);
  		this.missionsToApprove = new ArrayList<Mission>(missionService.getMissionsToApprove());
		 this.missionsAvailable = new ArrayList<Mission>(missionService.getMissionsAvailable(lg.userConn));
		 this.missionsSupervisedByUser = new ArrayList<Mission>(missionService.getMissionsSupervisedByUser(lg.userConn));
		 this.missionsParticpatedIn = new ArrayList<Mission>(missionService.getMissionsParticpatedIn(lg.userConn));
		 this.missionsAppliedFor = new ArrayList<Mission>(missionService.getMissionsAppliedFor(lg.userConn));
		 this.missionsPostedByUser = new ArrayList<Mission>(missionService.getMissionsPostedByUser(lg.userConn));
		navigateTo = "/template/ManagerMissionSpace?faces-redirect=true";
		
		return navigateTo;
	}
	public String approveMissionPostedByManager(int refrence) {
		
		String navigateTo="null";
		LoginBean lg = new LoginBean();
		Mission mission = missionService.getMissionById(refrence);
  		mission.setState(State.Approved);
  		mission.setParticipants(new HashSet<User>(participantsSelected));
  		missionService.updateState(mission,lg.userConn);
  		if(statService.getUserStats(lg.userConn)==null) {
			statService.addUserStats(lg.userConn);
		}
  		statService.updateParticipantionAmount(lg.userConn);
  		this.missionsToApprove = new ArrayList<Mission>(missionService.getMissionsToApprove());
		 this.missionsAvailable = new ArrayList<Mission>(missionService.getMissionsAvailable(lg.userConn));
		 this.missionsSupervisedByUser = new ArrayList<Mission>(missionService.getMissionsSupervisedByUser(lg.userConn));
		 this.missionsParticpatedIn = new ArrayList<Mission>(missionService.getMissionsParticpatedIn(lg.userConn));
		 this.missionsAppliedFor = new ArrayList<Mission>(missionService.getMissionsAppliedFor(lg.userConn));
		 this.missionsPostedByUser = new ArrayList<Mission>(missionService.getMissionsPostedByUser(lg.userConn));
		navigateTo = "/template/ManagerMissionSpace?faces-redirect=true";
		
		return navigateTo;
	}
	public String refuseMissionPostedByManager(int refrence) {
		
		String navigateTo="null";
		LoginBean lg = new LoginBean();
		Mission mission = missionService.getMissionById(refrence);
  		mission.setState(State.Refused);
  		missionService.updateState(mission,lg.userConn);
  		this.missionsToApprove = new ArrayList<Mission>(missionService.getMissionsToApprove());
		 this.missionsAvailable = new ArrayList<Mission>(missionService.getMissionsAvailable(lg.userConn));
		 this.missionsSupervisedByUser = new ArrayList<Mission>(missionService.getMissionsSupervisedByUser(lg.userConn));
		 this.missionsParticpatedIn = new ArrayList<Mission>(missionService.getMissionsParticpatedIn(lg.userConn));
		 this.missionsAppliedFor = new ArrayList<Mission>(missionService.getMissionsAppliedFor(lg.userConn));
		 this.missionsPostedByUser = new ArrayList<Mission>(missionService.getMissionsPostedByUser(lg.userConn));
		navigateTo = "/template/ManagerMissionSpace?faces-redirect=true";
		
		return navigateTo;
	}
	public String goToMissionModelPostedManager(Mission m) throws ParseException {
		
		String navigateTo="null";
		LoginBean lg = new LoginBean();
		Mission mission = missionService.getMissionById(m.getRefrence());
		this.client=mission.getClient();
		this.title=mission.getTitle();
		this.description=mission.getDescription();
		this.estimated_budget=mission.getEstimated_budget();
		this.isAnnulationRisque=mission.getIsAnnulationRisque();
		this.isRembursable=mission.getIsRembursable();
		this.place_intervention=mission.getPlace_intervention();
		this.selectedSkills=new ArrayList<Skills>(mission.getSkillsRequired());
		this.refrence=mission.getRefrence();
		this.postedBy = mission.getPostedBy();
		this.posterFirstN=postedBy.getFirstname();
		this.posterLastN=postedBy.getLastname();
		this.state=mission.getState();
		SimpleDateFormat formatter = new SimpleDateFormat("ddMMyyyy");
		this.st_date=formatter.format(mission.getDate_start());
		this.ft_date =formatter.format(mission.getDate_finish());
		this.allParticipants=new ArrayList<User>(mission.getParticipants());
		navigateTo = "/template/MissionModelPostedManager?faces-redirect=true";
		return navigateTo;
	}
	public String goToMissionModelSupervised(Mission m) throws ParseException {
		
		String navigateTo="null";
		LoginBean lg = new LoginBean();
		Mission mission = missionService.getMissionById(m.getRefrence());
		this.client=mission.getClient();
		this.title=mission.getTitle();
		this.description=mission.getDescription();
		this.estimated_budget=mission.getEstimated_budget();
		this.isAnnulationRisque=mission.getIsAnnulationRisque();
		this.isRembursable=mission.getIsRembursable();
		this.place_intervention=mission.getPlace_intervention();
		this.selectedSkills=new ArrayList<Skills>(mission.getSkillsRequired());
		this.refrence=mission.getRefrence();
		this.postedBy = mission.getPostedBy();
		this.posterFirstN=postedBy.getFirstname();
		this.posterLastN=postedBy.getLastname();
		this.state=mission.getState();
		SimpleDateFormat formatter = new SimpleDateFormat("ddMMyyyy");
		this.st_date=formatter.format(mission.getDate_start());
		this.ft_date =formatter.format(mission.getDate_finish());
		this.allParticipants=new ArrayList<User>(mission.getParticipants());
		navigateTo = "/template/MissionModelSuprvised?faces-redirect=true";
		return navigateTo;
	}
	public String searchForThisMission() {
		LoginBean lg = new LoginBean();
		Mission m = new Mission();
		m.setTitle(title);
		m.setClient(client);
		m.setEstimated_budget(estimated_budget);
		m.setIsAnnulationRisque(isAnnulationRisque);
		m.setIsRembursable(isRembursable);
		List<Mission> mis = new ArrayList<Mission>(missionService.searchForMission(m));
		misSerched= mis;
		return "/template/SearchForMission?faces-redirect=true";
	}
}
