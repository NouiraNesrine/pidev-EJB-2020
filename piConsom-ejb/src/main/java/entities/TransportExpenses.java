package entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import enumerations.TrspType;



@Entity

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

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public TrspType getTrspType() {
		return trspType;
	}

	public void setTrspType(TrspType trspType) {
		this.trspType = trspType;
	}

	public double getKms() {
		return kms;
	}

	public void setKms(double kms) {
		this.kms = kms;
	}

	public double getVisa() {
		return visa;
	}

	public void setVisa(double visa) {
		this.visa = visa;
	}

	public double getCosts() {
		return costs;
	}

	public void setCosts(double costs) {
		this.costs = costs;
	}

	public String getBoardingTicket() {
		return boardingTicket;
	}

	public void setBoardingTicket(String boardingTicket) {
		this.boardingTicket = boardingTicket;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	

}
