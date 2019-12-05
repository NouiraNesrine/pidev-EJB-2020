package entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
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
	
	@ManyToOne
	private Projet projet;
	

	
	/*
	@ManyToOne
	private Timesheet timesheet;

*/


	@ManyToOne(cascade =  CascadeType.ALL)
	private User user = new User();
	





	




	public Activity(String nom, String description, int nombreHeuresEstimer, statutsActivity statut) {
		super();
		this.nom = nom;
		this.description = description;
		NombreHeuresEstimer = nombreHeuresEstimer;
		this.statut = statut;
	}
















	public Activity(int id, String nom, String description, int nombreHeuresEstimer, int nombreHeuresTravailler,
			Date datedebut, Date dateFin, statutsActivity statut, Projet projet) {
		super();
		this.id = id;
		this.nom = nom;
		this.description = description;
		NombreHeuresEstimer = nombreHeuresEstimer;
		NombreHeuresTravailler = nombreHeuresTravailler;
		this.datedebut = datedebut;
		this.dateFin = dateFin;
		this.statut = statut;
		this.projet = projet;
	}
















	public Activity(int id, String nom, String description, int nombreHeuresEstimer, int nombreHeuresTravailler,
			Date datedebut, Date dateFin, statutsActivity statut, Projet projet, User user) {
		super();
		this.id = id;
		this.nom = nom;
		this.description = description;
		NombreHeuresEstimer = nombreHeuresEstimer;
		NombreHeuresTravailler = nombreHeuresTravailler;
		this.datedebut = datedebut;
		this.dateFin = dateFin;
		this.statut = statut;
		this.projet = projet;
		this.user = user;
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
















	public int getId() {
		return id;
	}
















	public void setId(int id) {
		this.id = id;
	}
















	public String getNom() {
		return nom;
	}
















	public void setNom(String nom) {
		this.nom = nom;
	}
















	public String getDescription() {
		return description;
	}
















	public void setDescription(String description) {
		this.description = description;
	}
















	public int getNombreHeuresEstimer() {
		return NombreHeuresEstimer;
	}
















	public void setNombreHeuresEstimer(int nombreHeuresEstimer) {
		NombreHeuresEstimer = nombreHeuresEstimer;
	}
















	public int getNombreHeuresTravailler() {
		return NombreHeuresTravailler;
	}
















	public void setNombreHeuresTravailler(int nombreHeuresTravailler) {
		NombreHeuresTravailler = nombreHeuresTravailler;
	}
















	public User getUser() {
		return user;
	}
















	public void setUser(User user) {
		this.user = user;
	}
















	public Date getDatedebut() {
		return datedebut;
	}
















	public void setDatedebut(Date datedebut) {
		this.datedebut = datedebut;
	}
















	public Date getDateFin() {
		return dateFin;
	}
















	public void setDateFin(Date dateFin) {
		this.dateFin = dateFin;
	}
















	public statutsActivity getStatut() {
		return statut;
	}
















	public void setStatut(statutsActivity statut) {
		this.statut = statut;
	}
















	public Projet getProjet() {
		return projet;
	}
















	public void setProjet(Projet projet) {
		this.projet = projet;
	}
































	
















	














	

	




	



	









	
	
}
