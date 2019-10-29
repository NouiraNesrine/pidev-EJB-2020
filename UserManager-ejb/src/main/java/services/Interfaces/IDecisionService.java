package services.Interfaces;

import java.util.List;

import javax.ejb.Remote;

import entities.Decision;
@Remote
public interface IDecisionService {
	
	public Decision addDecision(Decision d);
	public List<Decision> getAllDecisions();
	public void deleteDecision (int id);
	

}
