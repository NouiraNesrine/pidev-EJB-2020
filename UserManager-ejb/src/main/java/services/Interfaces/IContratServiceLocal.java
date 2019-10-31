package services.Interfaces;

import java.util.List;

import javax.ejb.Local;

import entities.Contrat;

@Local
public interface IContratServiceLocal {
	
	public int addContrat(Contrat c);
	public void updateContrat(int id);
	public List<Contrat> getAllContrat();
	public void removeContrat(int id);
	
}
