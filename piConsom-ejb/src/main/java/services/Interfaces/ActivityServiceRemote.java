package services.Interfaces;

import java.util.List;

import entities.Activity;

public interface ActivityServiceRemote {

	public int ajouterActivity(Activity activity) ;
	public List<Activity> getAllActivities() ;
	public void deleteActivityById(int activityId) ;
	public void updateActivity(Activity activity);
    public void updateActivityEmploye(Activity activity);
    public void EndActivityEmploye(Activity activity);
    public void updateStartActivity(Activity activity);

}
