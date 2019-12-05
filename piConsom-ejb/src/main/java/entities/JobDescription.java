package entities;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;


import enumerations.Niveau;



@Entity
public class JobDescription implements Serializable{

	private static final long serialVersionUID = 1134486358621863L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idJob;
	
	@Column
	private String nom_competence ;
	
	@Column
	private String Description ;
	
	@Column
	@Enumerated
	private Niveau niveau;
	
	@ManyToMany(cascade = CascadeType.ALL)
    private Set<Skills> JDSkilss ;

	public int getIdJob() {
		return idJob;
	}

	public void setIdJob(int idJob) {
		this.idJob = idJob;
	}

	public String getNom_competence() {
		return nom_competence;
	}

	public void setNom_competence(String nom_competence) {
		this.nom_competence = nom_competence;
	}

	public String getDescription() {
		return Description;
	}

	public void setDescription(String description) {
		Description = description;
	}

	public Niveau getNiveau() {
		return niveau;
	}

	public void setNiveau(Niveau niveau) {
		this.niveau = niveau;
	}

	public Set<Skills> getJDSkilss() {
		return JDSkilss;
	}

	public void setJDSkilss(Set<Skills> jDSkilss) {
		JDSkilss = jDSkilss;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	} 
	
	
}
