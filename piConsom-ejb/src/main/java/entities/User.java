package entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
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

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import enumerations.Role;


@Entity

public class User implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idUser;

	
	
	
	

	private String Firstname;

	@Column
	private String Lastname;

	@Column
	private String email;

	@Column
	private String password;

	@Column
	private boolean isActiv;

	@Enumerated(EnumType.STRING)
	@Column
	Role role;

	@ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
	private List<Skills> Userskills;
/*
	@ManyToMany(mappedBy = "UsersParticipants", cascade = CascadeType.ALL)
	private Set<Formation> formations;
*/
	/*
	@ManyToMany(cascade = CascadeType.PERSIST,fetch=FetchType.EAGER)
	private List<Skills> Userskills;
*/
	
	
	
	
	
	
	
	
	
	
	@ManyToMany(mappedBy = "UsersParticipants", cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
	private Set<Formation> formations;
	
	
	
	
	
	
	
	
	/*
	@OneToOne(mappedBy = "user" , cascade =CascadeType.ALL)
	@JsonBackReference(value="timesheet")
	private Timesheet timesheet;
	
	
	
	
	
	*/
/*	
	@OneToMany(mappedBy="user", cascade =  CascadeType.ALL , fetch = FetchType.LAZY)
	 @JsonIgnore
	private List<Activity> activities;*/
	@OneToMany(mappedBy="user", cascade =  CascadeType.ALL)
	 @JsonBackReference(value="activities")
	private List<Activity> activities ;
	
	
	/*
	@OneToMany(mappedBy = "user", cascade =  CascadeType.PERSIST , fetch = FetchType.LAZY)
	 @JsonIgnore
	private List<Activity> activities = new ArrayList<>();
	*/
	
	@OneToMany(mappedBy = "user", cascade =  CascadeType.PERSIST , fetch = FetchType.LAZY)
	 @JsonIgnore
	private List<EvaluationSheet> evaluations = new ArrayList<>();

	
	/*
	@OneToMany(mappedBy = "user", cascade = { CascadeType.PERSIST, CascadeType.REMOVE }, fetch = FetchType.EAGER)
	private List<EvaluationSheet> evaluations = new ArrayList<>();
*/
	@OneToOne(mappedBy="user")
	private Contrat contrat;

	@OneToOne(mappedBy="user")
	private Conge conge;

	public User() {
		super();
	}


	public User(String firstname, String lastname, String email, String password, boolean isActiv, Role role) {
		super();
		Firstname = firstname;
		Lastname = lastname;
		this.email = email;
		this.password = password;
		this.isActiv = isActiv;
		this.role = role;
	}

	public User(int idUser) {
		super();
		this.idUser = idUser;
	}

	public User(String firstname) {
		super();
		Firstname = firstname;
	}

	public Set<Formation> getFormations() {
		return formations;
	}


	public void setFormations(Set<Formation> formations) {
		this.formations = formations;
	}


	public int getIdUser() {
		return idUser;
	}

	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}

	public String getFirstname() {
		return Firstname;
	}

	public void setFirstname(String firstname) {
		Firstname = firstname;
	}

	public String getLastname() {
		return Lastname;
	}

	public void setLastname(String lastname) {
		Lastname = lastname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isActiv() {
		return isActiv;
	}

	public void setActiv(boolean isActiv) {
		this.isActiv = isActiv;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public List<Skills> getUserskills() {
		return Userskills;
	}

	public void setUserskills(List<Skills> userskills) {
		Userskills = userskills;
	}


	

	public List<Activity> getActivities() {
		return activities;
	}

	public void setActivities(List<Activity> activities) {
		this.activities = activities;
	}

	public List<EvaluationSheet> getEvaluations() {
		return evaluations;
	}

	public void setEvaluations(List<EvaluationSheet> evaluations) {
		this.evaluations = evaluations;
	}

	public Contrat getContrat() {
		return contrat;
	}

	public void setContrat(Contrat contrat) {
		this.contrat = contrat;
	}

	public Conge getConge() {
		return conge;
	}

	public void setConge(Conge conge) {
		this.conge = conge;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	
	
	

}
