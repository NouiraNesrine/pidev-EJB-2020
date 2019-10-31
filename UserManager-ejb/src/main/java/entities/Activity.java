package entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import enumerations.statutsActivity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Activity implements Serializable {

	private static final long serialVersionUID = 61919517027L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String nom;

	private String description;

	
	private int NombreHeuresEstimer;

	private int NombreHeuresTravailler;

	@Temporal(TemporalType.DATE)
	private Date datedebut;

	@Temporal(TemporalType.DATE)
	private Date dateFin;

	@Enumerated(EnumType.STRING)
	private statutsActivity statut;
	
	

	
	/*
	@ManyToOne
	private Timesheet timesheet;

*/


	@ManyToOne
	private User user;
	





	




	public Activity(String nom, String description, int nombreHeuresEstimer, statutsActivity statut) {
		super();
		this.nom = nom;
		this.description = description;
		NombreHeuresEstimer = nombreHeuresEstimer;
		this.statut = statut;
	}
















	public static long getSerialversionuid() {
		return serialVersionUID;
	}














	public Activity() {
		super();
	}





	






	public Activity(int id, Date datedebut, statutsActivity statut) {
		super();
		this.id = id;
		this.datedebut = datedebut;
		this.statut = statut;
	}
	








	public Activity(String nom, String description, int nombreHeuresEstimer, statutsActivity statut, User user) {
		super();
		this.nom = nom;
		this.description = description;
		NombreHeuresEstimer = nombreHeuresEstimer;
		this.statut = statut;
		this.user = user;
	}














	public Activity(User user) {
		super();
		this.user = user;
	}














	public Activity(int id, String nom, String description, int nombreHeuresEstimer, statutsActivity statut) {
		super();
		this.id = id;
		this.nom = nom;
		this.description = description;
		NombreHeuresEstimer = nombreHeuresEstimer;
		this.statut = statut;
	}














	public Activity(Date datedebut) {
		
		this.datedebut = datedebut;
	}














	public Activity(String nom, String description, int nombreHeuresEstimer, Date datedebut, statutsActivity statut) {
		super();
		this.nom = nom;
		this.description = description;
		NombreHeuresEstimer = nombreHeuresEstimer;
		this.datedebut = datedebut;
		this.statut = statut;
	}














	public Activity(Date datedebut, statutsActivity statut) {
		super();
		this.datedebut = datedebut;
		this.statut = statut;
	}














	public Activity(statutsActivity statut) {
		super();
		this.statut = statut;
	}














	public Activity(int id, statutsActivity statut) {
		super();
		this.id = id;
		this.statut = statut;
	}
































	public Activity(int id, int nombreHeuresTravailler, Date dateFin, statutsActivity statut) {
		super();
		this.id = id;
		NombreHeuresTravailler = nombreHeuresTravailler;
		this.dateFin = dateFin;
		this.statut = statut;
	}
















	














	

	




	



	









	
	
}
