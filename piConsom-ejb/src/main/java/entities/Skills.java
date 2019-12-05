package entities;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import enumerations.Categorie;
import enumerations.Niveau;
import enumerations.SkillsReferences;

@Entity
public class Skills implements Serializable{

	private static final long serialVersionUID = 100L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idSkills;
	
	@Column
	private String nomCompetence;
	
	@Column
	private String description;
	
	@Column
	@Enumerated
	private Niveau niveau;
	
	@Column
	@Enumerated
	private Categorie categorie;
	
	@Column
	@Enumerated
	private SkillsReferences skillsRef;
	
	@ManyToMany(mappedBy="Userskills")
	private List<User> listUserinSkills;
	
	@ManyToMany(mappedBy="JDSkilss",cascade = CascadeType.ALL)
	private List<JobDescription> ListJD ;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy="skillsF")
	private Set<Formation> listFormations;

	public int getIdSkills() {
		return idSkills;
	}

	public void setIdSkills(int idSkills) {
		this.idSkills = idSkills;
	}

	public String getNomCompetence() {
		return nomCompetence;
	}

	public void setNomCompetence(String nomCompetence) {
		this.nomCompetence = nomCompetence;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Niveau getNiveau() {
		return niveau;
	}

	public void setNiveau(Niveau niveau) {
		this.niveau = niveau;
	}

	public Categorie getCategorie() {
		return categorie;
	}

	public void setCategorie(Categorie categorie) {
		this.categorie = categorie;
	}

	public SkillsReferences getSkillsRef() {
		return skillsRef;
	}

	public void setSkillsRef(SkillsReferences skillsRef) {
		this.skillsRef = skillsRef;
	}

	public List<User> getListUserinSkills() {
		return listUserinSkills;
	}

	public void setListUserinSkills(List<User> listUserinSkills) {
		this.listUserinSkills = listUserinSkills;
	}

	public List<JobDescription> getListJD() {
		return ListJD;
	}

	public void setListJD(List<JobDescription> listJD) {
		ListJD = listJD;
	}

	public Set<Formation> getListFormations() {
		return listFormations;
	}

	public void setListFormations(Set<Formation> listFormations) {
		this.listFormations = listFormations;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
