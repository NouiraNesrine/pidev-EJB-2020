package services.Implementations;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import entities.Decision;
import entities.DecisionReference;
import entities.User;
import services.Interfaces.IDecisionReferenceService;

@Stateless
@LocalBean

public class DecisionReferenceService implements IDecisionReferenceService {

	@PersistenceContext(unitName="pidev-ejb")
	EntityManager em;
	DecisionReference dr = new DecisionReference();

	@Override
	public DecisionReference addDecisionReference(DecisionReference dr) {
		em.persist(dr);
		return dr;
	}

	@Override
	public List<DecisionReference> getAllDecisionReferences() {
		return em.createQuery("SELECT dr from DecisionReference dr ", DecisionReference.class).getResultList();
	}

	@Override
	public void updateDecisionReference(DecisionReference dr) {
		em.persist(dr);
		
	}

	@Override
	public void deleteDecisionReference(int id) {
		em.remove(em.find(DecisionReference.class, id));
		System.out.println("Deleted!!!");	
	}

}
