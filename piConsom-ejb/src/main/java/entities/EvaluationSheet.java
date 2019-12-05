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



@Entity

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

	public int getEvalId() {
		return evalId;
	}

	public void setEvalId(int evalId) {
		this.evalId = evalId;
	}

	public Date getDateCreation() {
		return dateCreation;
	}

	public void setDateCreation(Date dateCreation) {
		this.dateCreation = dateCreation;
	}

	public float getNoteManager() {
		return noteManager;
	}

	public void setNoteManager(float noteManager) {
		this.noteManager = noteManager;
	}

	public float getNoteEmploye() {
		return noteEmploye;
	}

	public void setNoteEmploye(float noteEmploye) {
		this.noteEmploye = noteEmploye;
	}

	public ArrayList<String> getCommentaire() {
		return commentaire;
	}

	public void setCommentaire(ArrayList<String> commentaire) {
		this.commentaire = commentaire;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Objectif getObjectif() {
		return objectif;
	}

	public void setObjectif(Objectif objectif) {
		this.objectif = objectif;
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
