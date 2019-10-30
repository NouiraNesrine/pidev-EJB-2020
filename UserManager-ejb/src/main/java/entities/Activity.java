package entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import enumerations.statutsActivity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Activity implements Serializable {

	private static final long serialVersionUID = 61919517027L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String nom;

	private String description;

	@Temporal(TemporalType.DATE)
	private Date NombreHeuresEstimer;

	@Temporal(TemporalType.DATE)
	private Date NombreHeuresTravailler;

	@Temporal(TemporalType.DATE)
	private Date datedebut;

	@Temporal(TemporalType.DATE)
	private Date dateFin;

	@Enumerated(EnumType.STRING)
	private statutsActivity statut;

	@ManyToOne
	private Timesheet timesheet;

}
