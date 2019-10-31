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

}
