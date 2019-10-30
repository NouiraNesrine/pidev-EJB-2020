package entities;

import java.io.Serializable;


import java.util.Date;
import java.util.List;

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


import enumerations.isActif;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Timesheet implements Serializable {
	private static final long serialVersionUID = 61919517027L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	

	@Temporal(TemporalType.DATE)
	private Date dateArrivee;
	
	@Temporal(TemporalType.DATE)
	private Date dateSortie;
	
	@Enumerated(EnumType.STRING)
	private isActif isActive;	
	
	
	private int NombreHeureParJour;
	
	private int NombreJoursTravailler;
	
	
	private int nombredejourscongés;
	

	@OneToMany(mappedBy="timesheet")
	private List<Activity> activities;
	
	@OneToOne
	private User user;
	
}
