package login;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import entities.Decision;
import entities.EvaluationSheet;
import entities.Objectif;
import entities.User;
import lombok.Getter;
import lombok.Setter;
import services.Implementations.EvaluationSheetService;
import services.Implementations.ObjectifService;
import services.Implementations.UserService;
import services.Interfaces.IEvaluationSheetService;
import services.Interfaces.IObjectifService;
import services.Interfaces.IUserServiceLocal;
@ManagedBean
@SessionScoped
@Getter
@Setter
public class EvaluationSheetBean implements Serializable{

	
	private Date dateCreation;
	//ArrayList<String> cars = new ArrayList<String>();
	private float noteManager;
	
	private List<EvaluationSheet> evaluations;
	private List<EvaluationSheet> evaluationsEmploye;
	private EvaluationSheet eval;
	private User u;
	private Objectif obj;
	private List<SelectItem> itemObjectifList;
	private List<SelectItem> itemUserList;
	private String nameObj;
	private int idUs;
	private int idE;
	private List<Objectif> listObj;
	private List<User> listUser;
	private float noteEmploye;
	@ManagedProperty(value="#{loginBean}")
	private LoginBean lb;
	@EJB
	IEvaluationSheetService ess= new EvaluationSheetService();
	@EJB
	IObjectifService os = new ObjectifService();
	@EJB
	IUserServiceLocal iusl = new UserService();
	
	public void addEvaluationSheet() {
		Date currentDate = new Date();
		Calendar c = Calendar.getInstance();
	    c.setTime(currentDate);
	    c.set(Calendar.HOUR_OF_DAY, 0);
	    c.set(Calendar.MINUTE, 0);
	    c.set(Calendar.SECOND, 0);
	    c.set(Calendar.MILLISECOND, 0);
	    Date todayDate = c.getTime();
	    obj = os.getObjectifByName(nameObj);
		u = iusl.getUserById(idUs);
		ess.addEvaluationSheet(new EvaluationSheet(todayDate,noteManager, u, obj));
	}
	
	public List<EvaluationSheet> getEvaluationSheets() {
		evaluations = ess.getAllEvaluations();
		return evaluations;
		}
	
	public List<EvaluationSheet> getEvaluationSheetsEmploye() {
		evaluationsEmploye = ess.getAllEvaluationSheetByUser(lb.getUser().getIdUser());
		return evaluationsEmploye;
		}
	
	//Delete Evaluationsheet
	public void deleteEvaluation(int ideval) {
		ess.deleteEvaluationSheet(ideval);
	}
	
	//Update Evaluationsheet Manager
	public void updateEvaluation() {
		ess.updateEvaluationSheetParManager(new EvaluationSheet(idE, dateCreation,noteManager, noteEmploye, u, obj));
	}
	
	//Update Evaluationsheet Employee
		public void updateEvaluationEmploye(EvaluationSheet e) {
			ess.updateEvaluationSheetParEmploye(e);
		}
		
		public void displayEvaluation(EvaluationSheet ev)
		{
		this.setDateCreation(ev.getDateCreation());
		this.setNoteManager(ev.getNoteManager());
		this.setU(ev.getUser());
		this.setObj(ev.getObjectif());
		this.setNoteEmploye(ev.getNoteEmploye());
		this.setIdE(ev.getEvalId());
		}

	
	public List<EvaluationSheet> getEvaluations() {
    evaluations = ess.getAllEvaluations();
	return evaluations;
	}
	
	public List<EvaluationSheet> getEvaluationByObjectif(String name) {
		Objectif obj = os.getObjectifByName(name);
	    evaluations = ess.getAllEvaluationSheetByObjectif(obj);
		return evaluations;
		}
    
	
	//Generate Decision
	public Decision generateDecision(User) {
		
	}

	public float getNoteManager() {
		return noteManager;
	}

	public void setNoteManager(float noteManager) {
		this.noteManager = noteManager;
	}

	public EvaluationSheet getEval() {
		return eval;
	}

	public void setEval(EvaluationSheet eval) {
		this.eval = eval;
	}

	public User getU() {
		return u;
	}

	public void setU(User u) {
		this.u = u;
	}

	public Objectif getObj() {
		return obj;
	}

	public void setObj(Objectif obj) {
		this.obj = obj;
	}

	public List<SelectItem> getItemObjectifList() {

		this.itemObjectifList = new ArrayList<SelectItem>();
		itemObjectifList.clear();
		
		List<Objectif> listObj= os.getAllObjectifs();
		
		for(Objectif ob:listObj) {
			SelectItem selectitem = new SelectItem(ob.getName());
			this.itemObjectifList.add(selectitem);
		}
		
		return itemObjectifList;
	}
	
	public List<SelectItem> getItemUserList() {

		this.itemUserList = new ArrayList<SelectItem>();
		itemUserList.clear();
		
		List<User> listUser= iusl.getAllUser();
		
		for(User us:listUser) {
			SelectItem selectitem = new SelectItem(us.getIdUser(), us.getLastname());
			this.itemUserList.add(selectitem);
		}
		
		return itemUserList;
	}
	
	//Calcul du score
	public float calculScore(EvaluationSheet es) {
		
		float score;
	    score =(float) (es.getNoteEmploye()*0.3 + es.getNoteManager()*0.7);
		return score;
	}


	public void setItemObjectifList(List<SelectItem> itemObjectifList) {
		this.itemObjectifList = itemObjectifList;
	}

	public String getNameObj() {
		return nameObj;
	}

	public void setNameObj(String nameObj) {
		this.nameObj = nameObj;
	}

	public List<Objectif> getListObj() {
		return listObj;
	}

	public void setListObj(List<Objectif> listObj) {
		this.listObj = listObj;
	}

	public void setEvaluations(List<EvaluationSheet> evaluations) {
		this.evaluations = evaluations;
	}

	
	
}
