package entities;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Getter;
import lombok.Setter;


@Entity
@Getter
@Setter
public class EvaluationSheet implements Serializable {
	
	private static final long serialVersionUID = 152825825828258L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int evalId;
	@Temporal(TemporalType.DATE)
	private Date dateCreation;
	
	private float noteManager;
	private float noteEmploye;
	private ArrayList<String> commentaire;
	
	@ManyToOne
	private User user;
	
	@ManyToOne
	private Objectif objectif ;
	
	@OneToOne(mappedBy="evaluation")
	private Decision decision;
}
