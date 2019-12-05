package services.Implementations;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;


import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import entities.Activity;
import entities.Timesheet;
import entities.User;
import services.Interfaces.TimesheetServiceRemote;




@Stateless
@LocalBean
public class TimesheetService  implements TimesheetServiceRemote{

	
	@PersistenceContext
	EntityManager em;
	
	
	@Override
	public int ajouterTimesheet(Timesheet timesheet) {
			
		
		em.persist(timesheet);
		return timesheet.getId();}

	@Override
	public List<Timesheet> getAllTimesheet() {
		 List<Timesheet> emp = em.createQuery("Select e from Timesheet e",Timesheet.class).getResultList();
		 
		 return emp; 

	}
	
	@Override
	public void deleteTimesheet(int id) {
		Timesheet activity = em.find(Timesheet.class, id);
		
		em.remove(activity);
		
	}

	@Override
	public void updateActivity(Timesheet timesheet) {
		// TODO Auto-generated method stub
		
	}
	
	public void updateStartTimeShit(Timesheet activity) {
		
		Timesheet emp = em.find(Timesheet.class, activity.getId()); 
		emp.setClockIn(new Date());
       emp.setIsActive(activity.getIsActive());


	
	
	}

	
	public void clockOutday(Timesheet timesheet) {
		Timesheet emp = em.find(Timesheet.class, timesheet.getId()); 
		emp.setLastClockOut(new Date());
		  
		emp.setIsActive(timesheet.getIsActive());
		emp.setNombreHeureTravailler(timesheet.getNombreHeureTravailler());
        emp.setNombreJoursTravaillerParmois(timesheet.getNombreJoursTravaillerParmois()+1);
		
	}
	
	public void affecterTimesheetAEmploye(int TimesheetId, int userId) {
		Timesheet timesheetManagedEntity = em.find(Timesheet.class, TimesheetId);
		User userManagedEntity = em.find(User.class, userId);
		
	//	timesheetManagedEntity.setUser(userManagedEntity);
		
		em.merge(timesheetManagedEntity);
	}

	@Override
	public void clockIn(Timesheet timesheet) {
		Timesheet emp = em.find(Timesheet.class, timesheet.getId()); 
		emp.setClockIn(timesheet.getClockIn());
		  
		emp.setIsActive(timesheet.getIsActive());
		
	}

	@Override
	public void EnPause(Timesheet timesheet) {
		Timesheet emp = em.find(Timesheet.class, timesheet.getId()); 
	
		  
		emp.setIsActive(timesheet.getIsActive());
		
	}

	@Override
	public void clockOut(Timesheet timesheet) {
		// TODO Auto-generated method stub
		
	}
	
	

}
