package entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import enumerations.Categorie;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Formation implements Serializable {
	private static final long serialVersionUID = 8133866145125103469L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idFormation;

	@Column
	private String NomF;

	@Column
	private String Formateur;

	@Temporal(TemporalType.DATE)
	@Column
	private Date DateDebutF;

	@Temporal(TemporalType.DATE)
	@Column
	private Date DateFinF;

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<User> UsersParticipants;
	@ManyToOne
	Skills skillsF;

	@Column
	@Enumerated(value = EnumType.STRING)
	private Categorie categorie;

	public Formation() {
		super();
	}

	public Formation(String nomF, String formateur, Date dateDebutF, Date dateFinF, Categorie c) {
		super();
		NomF = nomF;
		Formateur = formateur;
		DateDebutF = dateDebutF;
		DateFinF = dateFinF;
		categorie = c;

	}

	public Formation(int idFormation, String nomF, String formateur, Date dateDebutF, Date dateFinF, Categorie c) {
		super();
		this.idFormation = idFormation;
		NomF = nomF;
		Formateur = formateur;
		DateDebutF = dateDebutF;
		DateFinF = dateFinF;
		categorie = c;
	}

}
