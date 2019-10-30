package entities;

import java.io.Serializable;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import lombok.Getter;
import lombok.Setter;


@Entity
@Getter
@Setter
public class DecisionReference implements Serializable {

	private static final long serialVersionUID = 15425458L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idDecRef;
	
	private int minPrime;
	private int maxPrime;
	private int minAugSalaire;
	private int maxAugSalaire;
	
	@OneToOne(mappedBy="decisionRef")
	private Decision decision; 
}