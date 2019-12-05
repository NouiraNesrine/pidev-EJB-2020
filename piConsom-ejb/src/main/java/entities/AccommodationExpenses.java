package entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import enumerations.AccType;



@Entity

public class AccommodationExpenses implements Serializable {
	
	private static final long serialVersionUID = 6003293039237941254L;
	@Id 
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private int id;
	@Enumerated(EnumType.STRING)
	private AccType acctype;
	private int duration;
	private double costs;
	private String accommodationBill;
	
	public AccommodationExpenses(AccType acctype, int duration, double costs, String accommodationBill) {
		super();
		this.acctype = acctype;
		this.duration = duration;
		this.costs = costs;
		this.accommodationBill = accommodationBill;
	}

	public AccommodationExpenses() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public AccType getAcctype() {
		return acctype;
	}

	public void setAcctype(AccType acctype) {
		this.acctype = acctype;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public double getCosts() {
		return costs;
	}

	public void setCosts(double costs) {
		this.costs = costs;
	}

	public String getAccommodationBill() {
		return accommodationBill;
	}

	public void setAccommodationBill(String accommodationBill) {
		this.accommodationBill = accommodationBill;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	
}
