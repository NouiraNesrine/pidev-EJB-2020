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



@Entity

public class Objectif implements Serializable {

	private static final long serialVersionUID = 1875445L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idObjectif;
	
	private String name;
	
	@OneToMany(mappedBy="objectif", 
			cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, 
			fetch=FetchType.EAGER)
	private List<EvaluationSheet> evaluations = new ArrayList<>();

	public int getIdObjectif() {
		return idObjectif;
	}

	public void setIdObjectif(int idObjectif) {
		this.idObjectif = idObjectif;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<EvaluationSheet> getEvaluations() {
		return evaluations;
	}

	public void setEvaluations(List<EvaluationSheet> evaluations) {
		this.evaluations = evaluations;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	
}
