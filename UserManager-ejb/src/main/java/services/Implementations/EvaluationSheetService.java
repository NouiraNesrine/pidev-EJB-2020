package services.Implementations;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import entities.Decision;
import entities.EvaluationSheet;
import entities.Objectif;
import entities.User;
import services.Interfaces.IEvaluationSheetService;

@Stateless
@LocalBean
public class EvaluationSheetService implements IEvaluationSheetService {
	
	@PersistenceContext(unitName="pidev-ejb")
	EntityManager em;
	EvaluationSheet eval= new EvaluationSheet();

	@Override
	public EvaluationSheet addEvaluationSheet(EvaluationSheet e) {
		em.persist(e);
		return e;
	}

	

	@Override
	public List<EvaluationSheet> getAllEvaluations() {
		
		return em.createQuery("SELECT e from EvaluationSheet e ", EvaluationSheet.class).getResultList();
	}


	
	@Override
	public void updateEvaluationSheetParManager(EvaluationSheet e) {
		EvaluationSheet ev = em.find(EvaluationSheet.class, e.getEvalId());
		if(ev.getEvalId()==e.getEvalId()) {
			ev.setNoteManager(e.getNoteManager());
			ev.setNoteEmploye(e.getNoteEmploye());
		}
	}
	
	@Override
	public void updateEvaluationSheetParEmploye(EvaluationSheet e) {
		
		EvaluationSheet ev = em.find(EvaluationSheet.class, e.getEvalId());	
		if(ev.getEvalId()==e.getEvalId()) {
			ev.setNoteEmploye(e.getNoteEmploye());
		}
	}
	
	@Override
	public void updateEvaluationSheet(EvaluationSheet e) {
		
		EvaluationSheet ev = em.find(EvaluationSheet.class, e.getEvalId());	
		if(ev.getEvalId()==e.getEvalId()) {
			ev.setDecision(e.getDecision());
		}
	}
	

	@Override
	public void deleteEvaluationSheet(int id) {		
		em.createQuery("delete from EvaluationSheet e where e.id=" + id).executeUpdate();
			
	}

	/*@Override
	public EvaluationSheet getEvaluationSheetByDateCreation(Date dateCreation) {
	
		TypedQuery<EvaluationSheet> query =
				em.createQuery("SELECT e FROM EvaluationSheet e where e.dateCreation=:dateCreation",EvaluationSheet.class);
				query.setParameter("datCreation", dateCreation);
				EvaluationSheet eval = null;
				try { eval = query.getSingleResult(); }
				catch (Exception e) { System.out.println("Erreur : " + e); }
				return eval;
	}*/
	
	@Override
	public List<EvaluationSheet> getAllEvaluationSheetByObjectif(Objectif o) {

			return	em.createQuery("SELECT e FROM EvaluationSheet e where e.objectif_idObjectif=:o.idObjectif",EvaluationSheet.class).setParameter("o.idObjectif", o.getIdObjectif()).getResultList();
	}

	@Override
	public Decision getDecision(EvaluationSheet e) {
		TypedQuery<Decision> query =
				em.createQuery("SELECT d FROM Decision d where d.idDecision=:e.decision.idDecision",Decision.class);
				query.setParameter("e.decision.idDecision",e.getDecision().getIdDecision());
				Decision d = null;
				try { d = query.getSingleResult(); }
				catch (Exception ex) { System.out.println("Erreur : " + ex); }
				return d;
	}
	
	public EvaluationSheet getEvaluationSheetByUser(User u, EvaluationSheet e) {
	
		TypedQuery<EvaluationSheet> query =
				em.createQuery("SELECT ev FROM EvaluationSheet ev where ev.user.idUser=:u.idUser AND ev.evalId=e.evalId",EvaluationSheet.class);
				query.setParameter("e.decision.idDecision",e.getDecision().getIdDecision());
				EvaluationSheet eval = null;
				try { eval = query.getSingleResult(); }
				catch (Exception ex) { System.out.println("Erreur : " + ex); }
				return eval;
	}
	
	public List<EvaluationSheet> getAllEvaluationSheetByUser(int idUser){
		return	em.createQuery("SELECT e FROM EvaluationSheet e where e.user.idUser=:idUser", EvaluationSheet.class).
				              setParameter("idUser", idUser).getResultList();
	}
	
	
}
