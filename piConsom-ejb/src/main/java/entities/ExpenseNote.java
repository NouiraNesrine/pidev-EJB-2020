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





@Entity

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

	public int getRefrence() {
		return refrence;
	}

	public void setRefrence(int refrence) {
		this.refrence = refrence;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Boolean getIsApproved() {
		return isApproved;
	}

	public void setIsApproved(Boolean isApproved) {
		this.isApproved = isApproved;
	}

	public double getTotalCost() {
		return totalCost;
	}

	public void setTotalCost(double totalCost) {
		this.totalCost = totalCost;
	}

	public double getTotalRecovered() {
		return totalRecovered;
	}

	public void setTotalRecovered(double totalRecovered) {
		this.totalRecovered = totalRecovered;
	}

	public Mission getMission() {
		return mission;
	}

	public void setMission(Mission mission) {
		this.mission = mission;
	}

	public User getEmployee() {
		return employee;
	}

	public void setEmployee(User employee) {
		this.employee = employee;
	}

	public User getOfficer() {
		return officer;
	}

	public void setOfficer(User officer) {
		this.officer = officer;
	}

	public List<TransportExpenses> getTansportExp() {
		return tansportExp;
	}

	public void setTansportExp(List<TransportExpenses> tansportExp) {
		this.tansportExp = tansportExp;
	}

	public List<AccommodationExpenses> getAccommodationExp() {
		return accommodationExp;
	}

	public void setAccommodationExp(List<AccommodationExpenses> accommodationExp) {
		this.accommodationExp = accommodationExp;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}
