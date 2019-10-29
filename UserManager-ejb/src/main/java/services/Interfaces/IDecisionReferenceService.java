package services.Interfaces;

import java.util.List;

import javax.ejb.Remote;

import entities.DecisionReference;

@Remote
public interface IDecisionReferenceService {
	
	public DecisionReference addDecisionReference(DecisionReference dr);
	public List<DecisionReference> getAllDecisionReferences();
	public void updateDecisionReference(DecisionReference dr);
	public void deleteDecisionReference (int id);
	
}
