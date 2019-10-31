package services.Implementations;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import entities.Mission;
import entities.Skills;
import entities.User;
import enumerations.Role;
import enumerations.State;
import services.Interfaces.IMissionServiceLocal;

@Stateless
@LocalBean
public class MissionService implements IMissionServiceLocal {

	@PersistenceContext(unitName="pidev-ejb")
	EntityManager em;
	@Override
	public Mission addMission(Mission mission) {
		
		em.merge(mission);
		
		return mission;
	}
	@Override
	public Set<Skills> getAllSkills(){
		 return new HashSet<Skills> (em.createQuery("select m from Skills m", Skills.class).getResultList());
		
	};
	@Override
	public List<Mission> displayMissions() {
		List<Mission> missions = em.createQuery("select m from Mission m", Mission.class).getResultList();
		return missions; 
	}
	@Override
	public void removeMission(int id) {
		// TODO Auto-generated method stub
		em.remove(em.find(Mission.class, id)); 

	}
	@Override
	public void updateMission(Mission m) {
		// TODO Auto-generated method stub
		Mission mission = em.find(Mission.class, m.getRefrence()); 
		mission.setDescription(m.getDescription());
		mission.setTitle(m.getTitle());
		mission.setClient(m.getClient());
		mission.setEstimated_budget(m.getEstimated_budget());
		mission.setIsAnnulationRisque(m.getIsAnnulationRisque());
		mission.setIsRembursable(m.getIsRembursable());
		mission.setPlace_intervention(m.getPlace_intervention());
		mission.setSkillsRequired(m.getSkillsRequired());
		mission.setDate_start(m.getDate_start());
		mission.setDate_finish(m.getDate_finish());
		
		
	}
	@Override
	public Mission getMissionById(int id) {
		// TODO Auto-generated method stub
		Mission mission = em.find(Mission.class, id); 
		return mission;

	}
	@Override
	public List<Mission> getMissionsToApprove() {
		// TODO Auto-generated method stub
		TypedQuery<Mission> query = em.createQuery("SELECT e FROM Mission e WHERE e.state = :stat AND e.isPosterManager = :isPosterManage " , Mission.class);
		//AND e.suprvisedBy = :suprvisedB
		return query.setParameter("stat", State.WaitingForApproval).setParameter("isPosterManage", false).getResultList();	
		
	}
	@Override
	public List<Mission> getMissionsSupervisedByUser(User u) {
		// TODO Auto-generated method stub
		TypedQuery<Mission> query = em.createQuery("SELECT e FROM Mission e WHERE e.suprvisedBy = :suprviser AND e.state= :stat" , Mission.class);
		
		return query.setParameter("suprviser", u).setParameter("stat", State.Approved).getResultList();
	}
	@Override
	public List<Mission> getMissionsAppliedFor(User u) {
		// TODO Auto-generated method stub
		TypedQuery<Mission> query = em.createQuery("SELECT m FROM Mission m inner join m.participants u  WHERE m.isPosterManager=:poster AND m.state = :stat AND u.idUser= :id" , Mission.class);
		
		return query.setParameter("poster", true).setParameter("stat", State.WaitingForApproval).setParameter("id", u.getIdUser()).getResultList();
	}
	@Override
	public List<Mission> getMissionsParticpatedIn(User u) {
		// TODO Auto-generated method stub
		TypedQuery<Mission> query = em.createQuery("SELECT m FROM Mission m inner join m.participants u  WHERE m.isPosterManager=:poster AND m.state = :stat AND u.idUser= :id" , Mission.class);
		
		return query.setParameter("poster", false).setParameter("stat", State.Approved).setParameter("id", u.getIdUser()).getResultList();
	}
	@Override
	public List<Mission> getMissionsPostedByUser(User u) {
		// TODO Auto-generated method stub
		TypedQuery<Mission> query = em.createQuery("SELECT u FROM Mission u WHERE u.postedBy=:poster AND u.state= :stat " , Mission.class);
				
		return query.setParameter("poster", u).setParameter("stat", State.WaitingForApproval).getResultList();
	}
	@Override
	public List<Mission> getMissionsAvailable(User user) {
		TypedQuery<Mission> query = em.createQuery("SELECT m FROM Mission m WHERE  m.state = :stat AND m.isPosterManager=:poster" , Mission.class);
		List<Mission> m = new ArrayList<Mission>(query.setParameter("stat", State.WaitingForApproval).setParameter("poster", true).getResultList());
		List<Mission> n = new ArrayList<Mission>();
		for (Mission s : m) {
		List<User> p = new ArrayList<User>(s.getParticipants());
		for (User u : p) {
			if(u.getIdUser()==user.getIdUser())
			{
				return n;
			}
		}
		}
		return m;
	}
	@Override
	public void updateParticipants(Mission m) {
		Mission mission = em.find(Mission.class, m.getRefrence()); 
		mission.setParticipants(m.getParticipants());
	}
	@Override
	public void updateState(Mission m,User u) {
		Mission mission = em.find(Mission.class, m.getRefrence()); 
		mission.setState(m.getState());
		/*if(m.getParticipants()!=null)
		mission.setParticipants(m.getParticipants());*/
		mission.setSuprvisedBy(u);
	}
	@Override
	public List<Mission> searchForMission(Mission m) {
		// TODO Auto-generated method stub
		double min = m.getEstimated_budget()-200;
		double max = m.getEstimated_budget()+200;
		
		Query query = em.createQuery(
	              "SELECT e FROM Mission e WHERE e.title  LIKE :titl AND e.client LIKE :clien AND e.estimated_budget > :min AND e.estimated_budget < :max "
	              + "AND e.isAnnulationRisque = :annul AND e.isRembursable = :remb AND e.place_intervention= :int");
	      query.setParameter("titl", m.getTitle()+"%");
	      query.setParameter("clien", m.getClient()+"%");
	      query.setParameter("min", min);
	      query.setParameter("max", max);
	      query.setParameter("annul", m.getIsAnnulationRisque());
	      query.setParameter("remb", m.getIsRembursable());
	      query.setParameter("int", m.getPlace_intervention()+"%");
	      List<Mission> resultList = query.getResultList();
	      /*List<Mission> p = new ArrayList<Mission>();
	      if(m.getSkillsRequired()!=null) {
			for (Mission u : resultList) {
				for(Skills s : u.getSkillsRequired())
				{
					for(Skills a :m.getSkillsRequired()) {
						if(a.getIdSkills()==s.getIdSkills())
							p.add(u);
					}
				}
			}
		return p;}else {*/
		return resultList;
	}
	
	

}
