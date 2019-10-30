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
	
	@OneToMany(mappedBy="objectif", 
			cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, 
			fetch=FetchType.EAGER)
	private List<EvaluationSheet> evaluations = new ArrayList<>();

}
