package services.Implementations;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;

import entities.Mission;
import entities.Skills;
import entities.User;
import enumerations.Role;
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

}
