package services.Interfaces;

import java.util.List;

import javax.ejb.Remote;

import entities.Decision;
import entities.EvaluationSheet;
import entities.Objectif;
import entities.User;

@Remote
public interface IEvaluationSheetService {
	
	public EvaluationSheet addEvaluationSheet(EvaluationSheet e);
	public List<EvaluationSheet> getAllEvaluations();
	public void updateEvaluationSheetParManager(EvaluationSheet e);
	public void updateEvaluationSheetParEmploye(EvaluationSheet e);
	public void deleteEvaluationSheet (int id);
	//public EvaluationSheet getEvaluationSheetByDateCreation(Date dateCreation);
	public Decision getDecision(EvaluationSheet e);
	public List<EvaluationSheet> getAllEvaluationSheetByUser(int id);
	public EvaluationSheet getEvaluationSheetByUser(User u, EvaluationSheet e);
	public List<EvaluationSheet> getAllEvaluationSheetByObjectif(Objectif o); 



}
