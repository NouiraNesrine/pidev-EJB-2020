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
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
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
	
	
}
