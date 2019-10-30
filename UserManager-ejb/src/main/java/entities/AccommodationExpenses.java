package entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import enumerations.AccType;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
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
	
}
