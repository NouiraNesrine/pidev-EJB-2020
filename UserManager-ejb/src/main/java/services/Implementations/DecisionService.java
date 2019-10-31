package services.Implementations;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import entities.Decision;
import entities.DecisionReference;
import entities.EvaluationSheet;
import entities.Objectif;
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
		em.flush();
		return d;
	}
	
	
	@Override
	public List<Decision> getAllDecisions() {
		
		return em.createQuery("SELECT d from Decision d ", Decision.class).getResultList();
	}
	
	@Override
	public void deleteDecision (int id) {
		em.remove(em.find(Decision.class, id));
		System.out.println("Deleted!!!");		
	}

	@Override
	public Decision getDecisionById (int idDecision) {
		TypedQuery<Decision> query =
				em.createQuery("SELECT d FROM Decision d where d.idDecision=:idDecision",Decision.class);
				query.setParameter("idDecision", idDecision);
				Decision d = null;
				try { d = query.getSingleResult(); }
				catch (Exception e) { System.out.println("Erreur : " + e); }
				return d;
	}

	@Override
	public Decision getLastDecision() {
		TypedQuery<Decision> query =
				em.createQuery("SELECT d FROM Decision d ORDER BY d.idDecision DESC ",Decision.class);
				Decision d = null;
				try { d = query.getResultList().get(0); }
				catch (Exception e) { System.out.println("Erreur : " + e); }
				return d;
	} 
	
	

	
}
