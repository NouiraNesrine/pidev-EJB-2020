package entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Objectif implements Serializable {

	private static final long serialVersionUID = 1875445L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idObjectif;
	
	private String name;
	private String description;
	
	@OneToMany(mappedBy="objectif", 
			cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, 
			fetch=FetchType.EAGER)
	private List<EvaluationSheet> evaluations = new ArrayList<>();
	
	public Objectif() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Objectif(String name, String description) {
		super();
		this.name = name;
		this.description = description;
	}


	public Objectif(int idObjectif, String name, String description) {
		super();
		this.idObjectif = idObjectif;
		this.name = name;
		this.description = description;
	}


	

	
	
}
