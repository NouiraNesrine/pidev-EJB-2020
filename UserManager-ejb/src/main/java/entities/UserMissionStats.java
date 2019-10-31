package entities;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import enumerations.State;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class UserMissionStats {
	private static final long serialVersionUID = -3661125011192221L;
	@Id 
	@GeneratedValue (strategy = GenerationType.IDENTITY) 
	private int refrence ;
	private double participantionAmount;
	private double applicationAmount;
	@OneToOne
	private User user;

}
