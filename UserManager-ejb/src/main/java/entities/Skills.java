package entities;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import enumerations.Categorie;
import enumerations.Niveau;
import enumerations.Role;
import enumerations.SkillsReferences;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
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
	
	
	@Enumerated(EnumType.STRING)
	@Column
	private Niveau niveau;
	
	
	@Enumerated(EnumType.STRING)
	@Column
	private Categorie categorie;
	
	
	@Enumerated(EnumType.STRING)
	@Column
	private SkillsReferences skillsRef;
	
	@ManyToMany(mappedBy="Userskills")
	private List<User> listUserinSkills;
	
	@ManyToMany(mappedBy="JDSkilss",cascade = CascadeType.ALL)
	private List<JobDescription> ListJD ;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy="skillsF")
	private Set<Formation> listFormations;
	public Skills() {
		super();
	}
	public Skills(  Categorie categorie,String description, Niveau niveau,String nomCompetence,
			SkillsReferences skillsRef) {
		super();
		
		
		
		this.categorie = categorie;
		this.description = description;
		this.niveau = niveau;
		this.nomCompetence = nomCompetence;
		this.skillsRef = skillsRef;
	}
	public Skills(int idSkills, String nomCompetence, String description, Niveau niveau, Categorie categorie,
			SkillsReferences skillsRef) {
		super();
		this.idSkills = idSkills;
		this.categorie = categorie;
		this.description = description;
		this.niveau = niveau;
		this.nomCompetence = nomCompetence;
		this.skillsRef = skillsRef;
	}
	
	

	

}
