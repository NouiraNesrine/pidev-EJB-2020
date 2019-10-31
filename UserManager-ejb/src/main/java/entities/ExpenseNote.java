package entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Getter;
import lombok.Setter;



@Entity
@Getter
@Setter
public class ExpenseNote implements Serializable {

	private static final long serialVersionUID = -4070474710515564501L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int refrence;
	@Temporal(TemporalType.DATE)
	private Date date;
	private Boolean isApproved;
	private double totalCost;
	private double totalRecovered;

	@OneToOne
	private Mission mission;
	@OneToOne
	private User employee;
	@OneToOne
	private User officer;
	@OneToMany(cascade = CascadeType.ALL)
	private List<TransportExpenses> tansportExp;
	@OneToMany(cascade = CascadeType.ALL)
	private List<AccommodationExpenses> accommodationExp;

	public ExpenseNote(double totalCost, Mission mission, User employee, List<TransportExpenses> tansportExp,
			List<AccommodationExpenses> accommodationExp) {
		super();
		this.date = new Date();
		this.totalCost = totalCost;
		this.mission = mission;
		this.employee = employee;
		this.tansportExp = tansportExp;
		this.accommodationExp = accommodationExp;
	}

	public ExpenseNote() {
		super();
	}
	
}
