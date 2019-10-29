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
import lombok.Getter;
import lombok.Setter;


@Entity
@Getter
@Setter
public class Decision implements Serializable {

	private static final long serialVersionUID = 2588258258825825821L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idDecision;
	@Enumerated(EnumType.STRING)
	private TypeDecision typeDec;
	
	@OneToOne(mappedBy="decision")
	private EvaluationSheet evaluation;
	
	@OneToOne
	private DecisionReference decisionRef; 
	
	
}
