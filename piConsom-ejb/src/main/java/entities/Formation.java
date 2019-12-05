package entities;

import java.io.Serializable;
import java.util.Date;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;



@Entity

public class Formation implements Serializable {
	private static final long serialVersionUID = 8133866145125103469L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idFormation;

	@Column
	private String NomF;
	
	@Column
	private String Formateur;

	@Temporal(TemporalType.DATE)
	@Column
	private Date DateDebutF;

	@Temporal(TemporalType.DATE)
	@Column
	private Date DateFinF;
	
	/*
	
	@ManyToMany( cascade = CascadeType.ALL)
	private Set<User> UsersParticipants;
	*/
	@ManyToMany( cascade = CascadeType.ALL,fetch=FetchType.EAGER)
	private Set<User> UsersParticipants;
	
	@ManyToOne
	Skills skillsF;
	public int getIdFormation() {
		return idFormation;
	}
	public void setIdFormation(int idFormation) {
		this.idFormation = idFormation;
	}
	public String getNomF() {
		return NomF;
	}
	public void setNomF(String nomF) {
		NomF = nomF;
	}
	public String getFormateur() {
		return Formateur;
	}
	public void setFormateur(String formateur) {
		Formateur = formateur;
	}
	public Date getDateDebutF() {
		return DateDebutF;
	}
	public void setDateDebutF(Date dateDebutF) {
		DateDebutF = dateDebutF;
	}
	public Date getDateFinF() {
		return DateFinF;
	}
	public void setDateFinF(Date dateFinF) {
		DateFinF = dateFinF;
	}
	public Set<User> getUsersParticipants() {
		return UsersParticipants;
	}
	public void setUsersParticipants(Set<User> usersParticipants) {
		UsersParticipants = usersParticipants;
	}
	public Skills getSkillsF() {
		return skillsF;
	}
	public void setSkillsF(Skills skillsF) {
		this.skillsF = skillsF;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	

}
