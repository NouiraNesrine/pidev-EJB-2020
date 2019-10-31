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
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
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
	 
	 
}
