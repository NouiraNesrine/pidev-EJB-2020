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
	public void updateParticipants(Mission m);
	public void updateState(Mission m,User u);
	public Mission getMissionById(int id);
	public List<Mission> searchForMission(Mission m);
	public List<Mission> getMissionsToApprove();
	public List<Mission> getMissionsSupervisedByUser(User u);
	public List<Mission> getMissionsAppliedFor(User u);
	public List<Mission> getMissionsParticpatedIn(User u);
	public List<Mission> getMissionsPostedByUser(User u);
	public List<Mission> getMissionsAvailable(User user);
}
