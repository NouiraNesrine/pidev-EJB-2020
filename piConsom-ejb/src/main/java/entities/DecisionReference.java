package entities;

import java.io.Serializable;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;




@Entity

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

	public int getIdDecRef() {
		return idDecRef;
	}

	public void setIdDecRef(int idDecRef) {
		this.idDecRef = idDecRef;
	}

	public int getMinPrime() {
		return minPrime;
	}

	public void setMinPrime(int minPrime) {
		this.minPrime = minPrime;
	}

	public int getMaxPrime() {
		return maxPrime;
	}

	public void setMaxPrime(int maxPrime) {
		this.maxPrime = maxPrime;
	}

	public int getMinAugSalaire() {
		return minAugSalaire;
	}

	public void setMinAugSalaire(int minAugSalaire) {
		this.minAugSalaire = minAugSalaire;
	}

	public int getMaxAugSalaire() {
		return maxAugSalaire;
	}

	public void setMaxAugSalaire(int maxAugSalaire) {
		this.maxAugSalaire = maxAugSalaire;
	}

	public Decision getDecision() {
		return decision;
	}

	public void setDecision(Decision decision) {
		this.decision = decision;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	} 
	
	
}