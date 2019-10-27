package entities;

import java.io.Serializable;
import java.util.Date;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

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
	
	@ManyToMany( cascade = CascadeType.ALL)
	private Set<User> UsersParticipants;
	@ManyToOne
	Skills skillsF;
	

}
