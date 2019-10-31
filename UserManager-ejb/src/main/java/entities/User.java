package entities;

import java.io.Serializable;
import java.util.ArrayList;
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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import enumerations.Role;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class User implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idUser;

	@Column
	private String Firstname;

	@Column
	private String Lastname;

	@Column
	private String email;

	@Column
	private String password;

	@Column
	private boolean isActiv;

	@Enumerated(EnumType.STRING)
	@Column
	Role role;

	@ManyToMany(cascade = CascadeType.ALL)
	private List<Skills> Userskills;

	@ManyToMany(mappedBy = "UsersParticipants", cascade = CascadeType.ALL)
	private List<Formation> formations;

	@OneToOne(mappedBy = "user")
	private Timesheet timesheet;

	@OneToMany(mappedBy = "user", cascade = { CascadeType.PERSIST, CascadeType.REMOVE }, fetch = FetchType.EAGER)
	private List<EvaluationSheet> evaluations = new ArrayList<>();

	@OneToOne(mappedBy = "user")
	private Contrat contrat;

	@OneToOne(mappedBy = "user")
	private Conge conge;
	
	

	public User() {
		super();
	}

	public User(String firstname, String lastname, String email, String password, boolean isActiv, Role role) {
		super();
		Firstname = firstname;
		Lastname = lastname;
		this.email = email;
		this.password = password;
		this.isActiv = isActiv;
		this.role = role;
	}

	


	

	
}
