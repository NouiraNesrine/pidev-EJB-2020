package entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
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
	
	private Date date_start;
	private Date date_finish;
	
	private String title;
	private String description;
	private String client;
	private String place_intervention;
	
	private double estimated_budget;
	private Boolean isRembursable;
	
	@Enumerated(EnumType.STRING)
	private State state;
		
	private Boolean isPosterManager;// posted by manager 
	private Boolean isAnnulationRisque;
	
	@OneToOne 
	private User postedBy;
	@OneToOne 
	private User suprvisedBy;
	
	@ManyToMany(cascade = CascadeType.MERGE) 
	private Set<User> participants;
	
	
	@ManyToMany(cascade = CascadeType.MERGE,fetch=FetchType.EAGER)
	private Set<Skills> skillsRequired;

	public Mission() {}
	public Mission(Date date_start, Date date_finish, String title, String description, String client,
			String place_intervention, double estimated_budget, Boolean isRembursable,
			Boolean isPosterManager, Boolean isAnnulationRisque, User postedBy, Set<Skills> skillsRequired) {
		super();
		this.date = new Date();
		this.date_start = date_start;
		this.date_finish = date_finish;
		this.title = title;
		this.description = description;
		this.client = client;
		this.place_intervention = place_intervention;
		this.estimated_budget = estimated_budget;
		this.isRembursable = isRembursable;
		this.state = State.WaitingForApproval;
		this.isPosterManager = isPosterManager;
		this.isAnnulationRisque = isAnnulationRisque;
		this.postedBy = postedBy;
		this.skillsRequired = skillsRequired;
		this.participants = new HashSet<User>();
	}

	
	
	
	
	
	
	
	 
	 
}
