package entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Statu implements Serializable{

	private static final long serialVersionUID = 3556962806042124469L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idStatu;
	
	@Column
	private String description;
	
	
	@OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER, mappedBy="statous")
	private List<Commentaire> commentaires;
	
	@ManyToOne
	User useToSta;

	public Statu() {
		super();
	}

	public Statu(String description, List<Commentaire> commentaires, User useToSta) {
		super();
		this.description = description;
		this.commentaires = commentaires;
		this.useToSta = useToSta;
	}

	public Statu(String description, User useToSta) {
		super();
		this.description = description;
		this.useToSta = useToSta;
	}
	
	
}
