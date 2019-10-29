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
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int evalId;
	@Temporal(TemporalType.DATE)
	private Date dateCreation;

	private float noteManager;
	private float noteEmploye;
	private ArrayList<String> commentaire;

	@ManyToOne
	private User user;

	@ManyToOne
	private Objectif objectif;

	@OneToOne
	private Decision decision;

	public EvaluationSheet() {
		super();
		// TODO Auto-generated constructor stub
	}

	public EvaluationSheet(int evalId, Date dateCreation, float noteManager) {
		super();
		this.evalId = evalId;
		this.dateCreation = dateCreation;
		this.noteManager = noteManager;
	}

	public EvaluationSheet(int evalId, Date dateCreation, float noteManager, User user) {
		super();
		this.evalId = evalId;
		this.dateCreation = dateCreation;
		this.noteManager = noteManager;
		this.user = user;
	}

	public EvaluationSheet(Date dateCreation, float noteManager, User user, Objectif objectif) {
		super();
		this.dateCreation = dateCreation;
		this.noteManager = noteManager;
		this.user = user;
		this.objectif = objectif;
	}

	public EvaluationSheet(int evalId, Date dateCreation, float noteManager, float noteEmploye, User user,
			Objectif objectif) {
		super();
		this.evalId = evalId;
		this.dateCreation = dateCreation;
		this.noteManager = noteManager;
		this.noteEmploye = noteEmploye;
		this.user = user;
		this.objectif = objectif;
	}

	
}
