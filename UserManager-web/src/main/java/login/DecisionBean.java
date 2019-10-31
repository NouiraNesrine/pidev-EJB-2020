package login;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.model.SelectItem;

import org.primefaces.model.chart.PieChartModel;

import entities.Decision;
import entities.DecisionReference;
import entities.EvaluationSheet;
import entities.User;
import enumerations.TypeDecision;
import lombok.Getter;
import lombok.Setter;
import services.Implementations.DecisionReferenceService;
import services.Implementations.DecisionService;
import services.Implementations.EvaluationSheetService;
import services.Implementations.UserService;
import services.Interfaces.IDecisionReferenceService;
import services.Interfaces.IDecisionService;
import services.Interfaces.IEvaluationSheetService;
import services.Interfaces.IUserServiceLocal;
@ManagedBean
@SessionScoped
@Getter
@Setter
public class DecisionBean implements Serializable{

	private int idDesRef;
	private int min;
	private int max;
	private List<DecisionReference> decisionsReference;
	private List<SelectItem> itemDRList;
	private   Decision d ;
	private List<Decision> decisions;
	private List<Decision> alldecisions;
	private List<Decision> decisionsByUser;
	private int idU;
	@ManagedProperty(value="#{loginBean}")
	private LoginBean lb;
	private PieChartModel pieModel1;
	 
	@EJB
	IDecisionReferenceService dr = new DecisionReferenceService();
	@EJB
	IUserServiceLocal us = new UserService();
	@EJB
	IDecisionService ds = new DecisionService();
	@EJB
	IEvaluationSheetService es = new EvaluationSheetService();
	
	EvaluationSheetBean evb= new EvaluationSheetBean();
	
	//Add DR
	public void addDecisionReference() {
		dr.addDecisionReference(new DecisionReference(min, max));	
	}
	
	//List of DR
	public List<DecisionReference> getDecisionsReference() {
		decisionsReference = dr.getAllDecisionReferences();
		return decisionsReference;
		}
	
	//Delete DR
	public void deleteDecisionReference(int idDR) {
			dr.deleteDecisionReference(idDR);
	}
	
	//SelectItem DR
	public List<SelectItem> getItemDRList() {

		this.itemDRList = new ArrayList<SelectItem>();
		itemDRList.clear();
		
		List<DecisionReference> listDR= dr.getAllDecisionReferences();
		
		for(DecisionReference dr:listDR) {
			SelectItem selectitem = new SelectItem(dr.getIdDecRef());
			this.itemDRList.add(selectitem);
		}
		return itemDRList;
	}
	
	//List of Decisions par employe
		public List<Decision> getDecisions() {
			decisions = new ArrayList<Decision>();
			List<EvaluationSheet> evaluationsEmploye = es.getAllEvaluationSheetByUser(lb.getUser().getIdUser());
			
			for(EvaluationSheet ev:evaluationsEmploye) {
				if (! decisions.contains(ev.getDecision())){
					decisions.add(ev.getDecision());
				}
			}
			return decisions;
			}
		
		//List of All Decisions  
			public List<Decision> getAllDecisions() {
					alldecisions = new ArrayList<Decision>();
					alldecisions = ds.getAllDecisions();							
					return decisions;
					}
	
		//List of Decisions par employe
			public List<Decision> getDecisionsByUser() {
				decisionsByUser = new ArrayList<Decision>();
				List<EvaluationSheet> evaluationsEmploye = es.getAllEvaluationSheetByUser(idU);
				
				for(EvaluationSheet ev:evaluationsEmploye) {
					if (! decisionsByUser.contains(ev.getDecision())){
						decisionsByUser.add(ev.getDecision());
					}
				}
				return decisionsByUser;
				}
	
	//Generate Decision
	public String generateDecision() {
		
		Date currentDate = new Date();
		Calendar c = Calendar.getInstance();
	    c.setTime(currentDate);
	  c.set(Calendar.HOUR_OF_DAY, 0);
	    c.set(Calendar.MINUTE, 0);
	    c.set(Calendar.SECOND, 0);
	    c.set(Calendar.MILLISECOND, 0);
	    Date todayDate = c.getTime();
	    
		//list employees
		List<User> listEmploye = us.getAllEmploye();
		
		//list des evaluationsheets par employe
		List<EvaluationSheet> listESEmploye = new ArrayList<EvaluationSheet>();
		 List<EvaluationSheet> evaluationsheetsApp = new ArrayList<EvaluationSheet>();
		
		for(User u:listEmploye){
			System.out.println("Empl" + u.getFirstname());
			
		 listESEmploye.addAll(u.getEvaluations());
		 
		//List ES Approuvés
		
		 evaluationsheetsApp = listESEmploye;
		 /* for(EvaluationSheet e:listESEmploye) {
			  DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy/MM/dd");
			  LocalDate d1 = LocalDate.parse(, format);
			  LocalDate d2 = LocalDate.parse(e.getDateCreation().toString(), format);
			  Period  period= Period.between(d1, d2);
			  if( period.getMonths() >= 12 ) {
				  //Add ES App
				  evaluationsheetsApp.add(e); 
			  }
		  }*/
		  
		  float scoreTot = this.calculScoreGen(evaluationsheetsApp);
		  DecisionReference desR = dr.getDecisionReferenceById(idDesRef);
		  System.out.println("score" + scoreTot);

		  if(scoreTot < desR.getMin()) {
			  d = new Decision(TypeDecision.ni_prime_ni_augmentation, currentDate, scoreTot, desR);
			  ds.addDecision(d);
			  
			 for(EvaluationSheet ev: evaluationsheetsApp) {
				 ev.setDecision(ds.getLastDecision());
				  es.updateEvaluationSheet(ev);
			  }
			         
		  }else if( scoreTot > desR.getMax()) {
			   d = new Decision(TypeDecision.augmentationSalaire, currentDate, scoreTot, desR);
			  ds.addDecision(d);
			
				 for(EvaluationSheet ev: evaluationsheetsApp) {
					  ev.setDecision(ds.getLastDecision());
					  es.updateEvaluationSheet(ev);
				  }
		  }else {
			   d = new Decision(TypeDecision.prime, currentDate, scoreTot, desR);
			  ds.addDecision(d);
			
				 for(EvaluationSheet ev: evaluationsheetsApp) {
					 ev.setDecision(ds.getLastDecision());
					  es.updateEvaluationSheet(ev);
				  }
		  }

		 listESEmploye.clear();
		}
		
		return "/template/DecisionManager?faces-redirect=true";
		
		//Redirect to DecisionManager
	}
	
	//Calcul score decision
	public float calculScoreGen(List<EvaluationSheet> listES) {
		float score = 0;
		for(EvaluationSheet ev:listES) {
			score += evb.calculScore(ev);
		}
		
		 
		return score/listES.size();
	}
	
	 
	
	 
	
}
