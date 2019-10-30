package entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class MissionExpenses implements Serializable{

	private static final long serialVersionUID = -4587602517013979931L;
	@Id 
	@GeneratedValue (strategy = GenerationType.IDENTITY) 
	private int refrence;
	private double totalRecovered;
	private Boolean isApproved;
	
	@OneToOne
	private Mission mission;
	@OneToMany(cascade = CascadeType.ALL)
	private List<ExpenseNote> expenseNotes;
	
	public MissionExpenses(double totalRecovered, Boolean isApproved, Mission mission, List<ExpenseNote> expenseNotes) {
		super();
		this.totalRecovered = totalRecovered;
		this.isApproved = isApproved;
		this.mission = mission;
		this.expenseNotes = expenseNotes;
	}
	
}
