package entities;

import java.io.Serializable;

import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import enumerations.State;


@Entity

public class Mission implements Serializable{
	
	private static final long serialVersionUID = -3661125003191192221L;
	//SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
	//System.out.println(formatter.format(date));
	@Id 
	@GeneratedValue (strategy = GenerationType.IDENTITY) 
	private int refrence ;
	@Temporal(TemporalType.DATE) 
	private Date date;
	private String subject;
	private String description;
	private int participantsNumber;
	@Enumerated(EnumType.STRING)
	private State state;
	private Boolean isProvidedAccd;
	private Boolean isProvidedTrsp;
	
	 @OneToOne 
	 private User postedBy;
	 @OneToMany(cascade = CascadeType.ALL) 
	 private List<User> participants;
	 
	 private String skillsRequired;
	
	public Mission(String subject, String description,
			Boolean isProvidedAccd, Boolean isProvidedTrsp, User postedBy,String skillsRequired) {
		super();
		this.date = new Date();
		this.subject = subject;
		this.description = description;
		this.participantsNumber = 0;
		this.state = State.Availbale;
		this.isProvidedAccd = isProvidedAccd;
		this.isProvidedTrsp = isProvidedTrsp;
		this.postedBy = postedBy;
		this.participants = null;
		this.skillsRequired=skillsRequired;
		
	}

	public Mission() {
		super();
	}

	public int getRefrence() {
		return refrence;
	}

	public void setRefrence(int refrence) {
		this.refrence = refrence;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getParticipantsNumber() {
		return participantsNumber;
	}

	public void setParticipantsNumber(int participantsNumber) {
		this.participantsNumber = participantsNumber;
	}

	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}

	public Boolean getIsProvidedAccd() {
		return isProvidedAccd;
	}

	public void setIsProvidedAccd(Boolean isProvidedAccd) {
		this.isProvidedAccd = isProvidedAccd;
	}

	public Boolean getIsProvidedTrsp() {
		return isProvidedTrsp;
	}

	public void setIsProvidedTrsp(Boolean isProvidedTrsp) {
		this.isProvidedTrsp = isProvidedTrsp;
	}

	public User getPostedBy() {
		return postedBy;
	}

	public void setPostedBy(User postedBy) {
		this.postedBy = postedBy;
	}

	public List<User> getParticipants() {
		return participants;
	}

	public void setParticipants(List<User> participants) {
		this.participants = participants;
	}

	public String getSkillsRequired() {
		return skillsRequired;
	}

	public void setSkillsRequired(String skillsRequired) {
		this.skillsRequired = skillsRequired;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	 
	 
}
