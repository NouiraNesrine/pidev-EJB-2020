package services.Interfaces;

import javax.ejb.Local;

import entities.User;
import entities.UserMissionStats;
@Local
public interface IUserMissionStatsService {
	
	public UserMissionStats addUserStats(User user);
	public void updateParticipantionAmount(User user);
	public void updateApplicationAmount(User user);
	public UserMissionStats getUserStats(User user);
	

}
