package services.Interfaces;

import java.util.List;

import javax.ejb.Local;

import entities.Skills;



@Local
public interface ICompetenceServiceLocal {
	public Skills ajouterSkills(Skills skill);

	

	public List<Skills> getAllEmployes();



	public void removeEmp(int idSkills);
	
	public void updateEmploye(Skills user);
	
}
