package services.Interfaces;

import java.util.List;

import entities.Activity;
import entities.Timesheet;

public interface TimesheetServiceRemote {
	public int ajouterTimesheet(Timesheet timesheet) ;
	public List<Timesheet> getAllTimesheet() ;
	public void updateActivity(Timesheet timesheet);
  //  public void updateActivityEmploye(Activity activity);
    public void clockOut(Timesheet timesheet);
    public void clockIn(Timesheet timesheet);
    public void EnPause(Timesheet timesheet);
    
}
