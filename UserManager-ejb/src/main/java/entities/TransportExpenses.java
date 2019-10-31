package entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import enumerations.TrspType;
import lombok.Getter;
import lombok.Setter;


@Entity
@Getter
@Setter
public class TransportExpenses implements Serializable{
	
	private static final long serialVersionUID = -4319640886989862213L;
	@Id 
	@GeneratedValue (strategy = GenerationType.IDENTITY) 
	private int id;
	@Enumerated(EnumType.STRING)
	private TrspType trspType;
	private double kms;
	private double visa;
	private double costs;
	private String boardingTicket;
	
	public TransportExpenses(TrspType trspType, double kms, double visa, double costs, String boardingTicket) {
		super();
		this.trspType = trspType;
		this.kms = kms;
		this.visa = visa;
		this.costs = costs;
		this.boardingTicket = boardingTicket;
	}

	public TransportExpenses() {
		super();
	}
	

}
