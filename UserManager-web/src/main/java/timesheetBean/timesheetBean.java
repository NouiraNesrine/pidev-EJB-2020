package timesheetBean;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;





import entities.Timesheet;
import entities.User;
import enumerations.isActif;
import lombok.Getter;
import lombok.Setter;
import services.Implementations.TimesheetService;





@Getter
@Setter
@ManagedBean(name = "timesheetttBean")
@SessionScoped
public class timesheetBean implements Serializable {

private static final long serialVersionUID = 15633578L;


private Date ClockIn;
private Date LastClockOut;
private isActif isActive;	
private int NombreHeureTravailler;
private int NombreJoursTravaillerParmois;
private List<Timesheet> Timesheets;
private User u = new User(1);
private Integer employeIdToBeUpdated;
@EJB
TimesheetService timesheetService;


public void addTimesheet() 

{ 
	timesheetService.ajouterTimesheet(new Timesheet(isActive,u));

}

public List<Timesheet> getTimesheets() {
	Timesheets = timesheetService.getAllTimesheet();
	return Timesheets; 
}



public String displaylockIn(Timesheet empl) 
{ 
	
String	navigateTo = "/template/ClockInOutTimesheet?faces-redirect=true";
	
this.setIsActive(empl.getIsActive());
this.setEmployeIdToBeUpdated(empl.getId());
this.setNombreHeureTravailler(empl.getNombreHeureTravailler());
this.setNombreJoursTravaillerParmois(empl.getNombreJoursTravaillerParmois());
return navigateTo;

}



public String clockIn() 
{
	
	String	navigateTo = "/template/timesheetEmploye?faces-redirect=true";
	
	timesheetService.updateStartTimeShit(new Timesheet(employeIdToBeUpdated,ClockIn,isActive,u));
	return navigateTo;

}

public String clockOut() 
{
	
	String	navigateTo = "/template/timesheetEmploye?faces-redirect=true";
	
	timesheetService.clockOutday(new Timesheet(employeIdToBeUpdated,LastClockOut,isActive,NombreHeureTravailler,NombreJoursTravaillerParmois,u));
	sendMessage("takwa.souai@esprit.tn","GoodBye"+"     see you soon  "+getIsActive()+getNombreJoursTravaillerParmois());
	return navigateTo;
	

}

public String boutonAct() 
{
	
	String	navigateTo = "/template/showActivityEmploye?faces-redirect=true";
	
	
	return navigateTo;

}



public  void sendMessage(String mail_client,String nom_client )
{

		final String username ="mohameddhia.soudani@esprit.tn";
		final String password = "alhamdoulilah2019<";
		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");
		props.put("mail.smtp.ssl.trust", "smtp.gmail.com");
		Session session = Session.getInstance(props,
		  new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
			return new PasswordAuthentication(username, password);
			}
		  });
		try {
			

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("mohameddhia.soudani@esprit.tn"));
			message.setRecipients(Message.RecipientType.TO,
			InternetAddress.parse(mail_client));
			message.setSubject("goodBye");
			message.setText("Dear "+nom_client
				
				);
			Transport.send(message);
			System.out.println("Done");
		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
}




}
