package entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import lombok.Setter;

import lombok.Getter;

@Entity
@Getter
@Setter
public class Conge implements Serializable{

	private static final long serialVersionUID = 3732804416855060281L;
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private int idConge;
	
	@Column
	private Date dateDeb;
	
	@Column
	private Date dateFin;
	
	@Column
	private String description;
	
	@OneToOne
	private User user;




}
