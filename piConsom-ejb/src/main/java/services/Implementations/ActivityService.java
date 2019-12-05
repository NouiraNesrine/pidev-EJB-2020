package services.Implementations;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;


import entities.Activity;
import entities.User;
import services.Interfaces.ActivityServiceRemote;




@Stateless
@LocalBean
public class ActivityService implements ActivityServiceRemote {

	@PersistenceContext
	EntityManager em;
	
	
	
	
	@Override
	public int ajouterActivity(Activity activity) {
		em.persist(activity);
		return activity.getId();
	}


	@Override
	public List<Activity> getAllActivities() {
		 List<Activity> emp = em.createQuery("Select e from Activity e",Activity.class).getResultList(); return emp; 

	}

	@Override
	public void deleteActivityById(int activityId) {
		Activity activity = em.find(Activity.class, activityId);
		
		em.remove(activity);
		
	}
	
	@Override
    public void updateActivity(Activity activity) { 
		Activity emp = em.find(Activity.class, activity.getId()); 
		emp.setNom(activity.getNom());
		emp.setDescription(activity.getDescription()); 
		emp.setNombreHeuresEstimer(activity.getNombreHeuresEstimer());  
		emp.setStatut(activity.getStatut());

		
	//	emp.setRole(activity.getRole()); 
	}

	public EntityManager getEm() {
		return em;
	}

	public void setEm(EntityManager em) {
		this.em = em;
	}


	@Override
	public void updateStartActivity(Activity activity) {
	
		Activity emp = em.find(Activity.class, activity.getId()); 
		emp.setDatedebut(new Date());
		//emp.setDateFin(null);  
       emp.setStatut(activity.getStatut());

	
	
	}


	@Override
	public void EndActivityEmploye(Activity activity) {
		Activity emp = em.find(Activity.class, activity.getId()); 
		//emp.setDatedebut(new Date());
		emp.setDateFin(new Date());  
       emp.setStatut(activity.getStatut());
       emp.setNombreHeuresTravailler(activity.getNombreHeuresTravailler());
	}
	
	public void affecterActivityAEmploye(int depId, int entrepriseId) {
		
		User timesheetManagedEntity = em.find(User.class, entrepriseId);
		Activity ActivityManagedEntity = em.find(Activity.class, depId);

	//	ActivityManagedEntity.setUser(timesheetManagedEntity);
		
	}
	
	
	
	public List<String> getAllActivitiesNamesByEmploye(int entrepriseId) {
		User entrepriseManagedEntity = em.find(User.class, entrepriseId);
		List<String> depNames = new ArrayList<>();
		for(Activity dep : entrepriseManagedEntity.getActivities()){
			depNames.add(dep.getNom());
		}
		
		return depNames;
	}
	
	
	
	public List<User> getUsers() {
		TypedQuery<User> query=em.createQuery("SELECT u from User u ",User.class);
	    return query.getResultList();
		
	}


	@Override
	public void updateActivityEmploye(Activity activity) {
		// TODO Auto-generated method stub
		
	}
	


	
	/*
	public List<Activity> getClientsByCriterias(String criterias) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Activity> cquery = cb.createQuery(Activity.class);
		Root<Activity> sm = cquery.from(Activity.class);
		//for ( String entry : criterias)
	//	{	
			if(criterias.equals("nom"))
	//	{
			cquery.where(cb.like(sm.get("nom"), criterias.toUpperCase() ));

		
		else if(criterias.equals("statut"))
		{ 
			cquery.where(cb.equal(sm.get("statut"), enumerations.statutsActivity.valueOf(criterias.toUpperCase()) ));

		}
		else
			{
			cquery.where(cb.like(sm.get(criterias), criterias.toUpperCase() ));
			}

		
		TypedQuery<Activity> query=em.createQuery(cquery);
		return query.getResultList();
	}
	*/
	
	public List<Activity> rechechertopicc(String nom) {
		TypedQuery<Activity> query = em.createQuery("Select t from Activity t where t.nom=:nom", Activity.class);
		query.setParameter("nom", nom);
		return query.getResultList();

	}



	
	



/*
public int getValidActivity() {
	TypedQuery<Activity> q = em.createQuery("Select c from Activity c where c.statusActivity = :type " ,Activity.class);
	q.setParameter("type", statutsActivity.valide);
	List <Activity> l= q.getResultList();
	return l.size();
}
	*/
	
	}


	

	
	
	

