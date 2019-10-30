package entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Setter;
import lombok.Getter;

@Entity
@Getter
@Setter
public class Contrat implements Serializable {
	
	private static final long serialVersionUID = 6191889143079517027L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idContrat;
	
	@Temporal(TemporalType.DATE)
	private Date dateDebut;
	
	private String typeContrat;
	
	private float salaire;
	
	@OneToOne
	private User user;
}