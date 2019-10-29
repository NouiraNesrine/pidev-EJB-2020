package services.Implementations;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import entities.Decision;
import services.Interfaces.IDecisionService;

@Stateless
@LocalBean
public class DecisionService implements IDecisionService {
	
	@PersistenceContext(unitName="pidev-ejb")
	EntityManager em;
	Decision d = new Decision();

	
	@Override
	public  Decision addDecision (Decision d) {
		em.persist(d);
		return d;
	}

	@Override
	public List<Decision> getAllDecisions() {
		
		return em.createQuery("SELECT d from Decision d ", Decision.class).getResultList();
	}
	
	public void deleteDecision (int id) {
		em.remove(em.find(Decision.class, id));
		System.out.println("Deleted!!!");		
	}

	
}
