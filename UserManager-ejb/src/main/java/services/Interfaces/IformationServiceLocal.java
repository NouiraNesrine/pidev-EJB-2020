package services.Interfaces;

import java.util.List;

import javax.ejb.Local;

import entities.Formation;
import entities.User;
import enumerations.Categorie;

@Local
public interface IformationServiceLocal {
	public Formation ajouterFormation(Formation formation);

	public void deleteFormationById(int id);

	public List<Formation> getAllFormation();

	public void updateFormation(Formation formation);

	public void affecterUserAFormation(int formationid, int userid);
	
	public List<User> getAllUserParticipants(int id);
	
	public List<Formation> getFormatiobByCateg(Categorie c);
}
