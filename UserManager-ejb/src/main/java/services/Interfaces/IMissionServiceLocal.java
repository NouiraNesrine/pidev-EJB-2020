package services.Interfaces;

import java.util.List;
import java.util.Set;

import javax.ejb.Local;

import entities.Mission;
import entities.Skills;
import entities.User;

@Local
public interface IMissionServiceLocal {

	public Mission addMission(Mission mission);
	public Set<Skills> getAllSkills();
	public List<Mission> displayMissions();
	public void removeMission(int id);
	public void updateMission(Mission m);
	public Mission getMissionById(int id);
}
