package entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Commentaire implements Serializable{

	private static final long serialVersionUID = -4899739043817865943L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idCommentaire;
	
	@Column
	private String descriptionCom;
	
	@Column
	private boolean jaime;
	
	@ManyToOne
	Statu statous;
	
	@ManyToOne
	User useToCom;

	public Commentaire() {
		super();
	}

	public Commentaire(String descriptionCom, boolean jaime, Statu statous, User useToCom) {
		super();
		this.descriptionCom = descriptionCom;
		this.jaime = jaime;
		this.statous = statous;
		this.useToCom = useToCom;
	}

	public Commentaire(String descriptionCom,User useToCom) {
		super();
		this.descriptionCom = descriptionCom;
		
		this.useToCom = useToCom;
	}
	
	
}
