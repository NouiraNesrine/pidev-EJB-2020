package entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;




@Entity
public class Projet {
	private static final long serialVersionUID = 61919517027L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String nom;

	private String description;
	
	private String mailClient;

	private int NombreHeuresEstimer;

	private int NombreHeuresTravailler;

	private int NombreHeuresEnRetard;


	
	
	
	@OneToMany(mappedBy="projet")
	// @JsonBackReference(value="activities")
	private List<Activity> activities ;





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





	public String getMailClient() {
		return mailClient;
	}





	public void setMailClient(String mailClient) {
		this.mailClient = mailClient;
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





	public int getNombreHeuresEnRetard() {
		return NombreHeuresEnRetard;
	}





	public void setNombreHeuresEnRetard(int nombreHeuresEnRetard) {
		NombreHeuresEnRetard = nombreHeuresEnRetard;
	}





	public List<Activity> getActivities() {
		return activities;
	}





	public void setActivities(List<Activity> activities) {
		this.activities = activities;
	}





	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	



	
	

}
