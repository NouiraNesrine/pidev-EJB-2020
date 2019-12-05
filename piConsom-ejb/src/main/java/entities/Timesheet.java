package entities;

import java.io.Serializable;
import java.sql.Time;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


import enumerations.isActif;



@Entity
public class Timesheet implements Serializable {
	private static final long serialVersionUID = 61919517027L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	

	@Temporal(TemporalType.DATE)
	private Date ClockIn;
	//LocalDate ClockIn = LocalDate.now(); 

	
	@Temporal(TemporalType.DATE)
	private Date LastClockOut;
	//LocalDate LastClockOut = LocalDate.now(); 

	
	@Enumerated(EnumType.STRING)
	private isActif isActive;	
	
	
	private int NombreHeureTravailler;
	
	private int NombreJoursTravaillerParmois;
	
	/*

	@OneToMany(mappedBy="timesheet")
	private List<Activity> activities;
	*/
	/*
	@OneToOne(cascade = CascadeType.ALL)
	private User user;
*/
	public Timesheet() {
		super();
	}

	public Timesheet(int id) {
		super();
		this.id = id;
	}

	public Timesheet(int id, Date clockIn, isActif isActive, User user) {
		super();
		this.id = id;
		ClockIn = clockIn;
		this.isActive = isActive;
	}

	public Timesheet(Date clockIn, isActif isActive, User user) {
		super();
		ClockIn = clockIn;
		this.isActive = isActive;
	}

	public Timesheet(int id, isActif isActive) {
		super();
		this.id = id;
		this.isActive = isActive;
	}



	public Timesheet(isActif isActive, User user) {
		super();
		this.isActive = isActive;
	}

	public Timesheet(int id, Date clockIn, Date lastClockOut, isActif isActive, int nombreHeureTravailler,
			int nombreJoursTravaillerParmois, User user) {
		super();
		this.id = id;
		ClockIn = clockIn;
		LastClockOut = lastClockOut;
		this.isActive = isActive;
		NombreHeureTravailler = nombreHeureTravailler;
		NombreJoursTravaillerParmois = nombreJoursTravaillerParmois;
	}

	public Timesheet(Date clockIn, Date lastClockOut, isActif isActive, int nombreHeureTravailler,
			int nombreJoursTravaillerParmois, User user) {
		super();
		ClockIn = clockIn;
		LastClockOut = lastClockOut;
		this.isActive = isActive;
		NombreHeureTravailler = nombreHeureTravailler;
		NombreJoursTravaillerParmois = nombreJoursTravaillerParmois;
	}

	public Timesheet(int id, Date clockIn, Date lastClockOut, isActif isActive, int nombreHeureTravailler,
			int nombreJoursTravaillerParmois) {
		super();
		this.id = id;
		ClockIn = clockIn;
		LastClockOut = lastClockOut;
		this.isActive = isActive;
		NombreHeureTravailler = nombreHeureTravailler;
		NombreJoursTravaillerParmois = nombreJoursTravaillerParmois;
	}

	public Timesheet(int id, Date lastClockOut, isActif isActive, int nombreJoursTravaillerParmois) {
		super();
		this.id = id;
		LastClockOut = lastClockOut;
		this.isActive = isActive;
		NombreJoursTravaillerParmois = nombreJoursTravaillerParmois;
	}

	public Timesheet(int id, Date clockIn, isActif isActive, int nombreJoursTravaillerParmois, User user) {
		super();
		this.id = id;
		ClockIn = clockIn;
		this.isActive = isActive;
		NombreJoursTravaillerParmois = nombreJoursTravaillerParmois;
	}

	public Timesheet(int id, Date lastClockOut, isActif isActive, int nombreHeureTravailler,
			int nombreJoursTravaillerParmois, User user) {
		super();
		this.id = id;
		LastClockOut = lastClockOut;
		this.isActive = isActive;
		NombreHeureTravailler = nombreHeureTravailler;
		NombreJoursTravaillerParmois = nombreJoursTravaillerParmois;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getClockIn() {
		return ClockIn;
	}

	public void setClockIn(Date clockIn) {
		ClockIn = clockIn;
	}

	public Date getLastClockOut() {
		return LastClockOut;
	}

	public void setLastClockOut(Date lastClockOut) {
		LastClockOut = lastClockOut;
	}

	public isActif getIsActive() {
		return isActive;
	}

	public void setIsActive(isActif isActive) {
		this.isActive = isActive;
	}

	public int getNombreHeureTravailler() {
		return NombreHeureTravailler;
	}

	public void setNombreHeureTravailler(int nombreHeureTravailler) {
		NombreHeureTravailler = nombreHeureTravailler;
	}

	public int getNombreJoursTravaillerParmois() {
		return NombreJoursTravaillerParmois;
	}

	public void setNombreJoursTravaillerParmois(int nombreJoursTravaillerParmois) {
		NombreJoursTravaillerParmois = nombreJoursTravaillerParmois;
	}



	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	


	

	
	

	
}
