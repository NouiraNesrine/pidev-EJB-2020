package entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import enumerations.TypeDecision;



@Entity

public class Decision implements Serializable {

	private static final long serialVersionUID = 2588258258825825821L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idDecision;
	@Enumerated(EnumType.STRING)
	private TypeDecision typeDec;
	
	@OneToOne
	private EvaluationSheet evaluation;
	
	@OneToOne
	private DecisionReference decisionRef;

	public int getIdDecision() {
		return idDecision;
	}

	public void setIdDecision(int idDecision) {
		this.idDecision = idDecision;
	}

	public TypeDecision getTypeDec() {
		return typeDec;
	}

	public void setTypeDec(TypeDecision typeDec) {
		this.typeDec = typeDec;
	}

	public EvaluationSheet getEvaluation() {
		return evaluation;
	}

	public void setEvaluation(EvaluationSheet evaluation) {
		this.evaluation = evaluation;
	}

	public DecisionReference getDecisionRef() {
		return decisionRef;
	}

	public void setDecisionRef(DecisionReference decisionRef) {
		this.decisionRef = decisionRef;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	} 
	
	
}
