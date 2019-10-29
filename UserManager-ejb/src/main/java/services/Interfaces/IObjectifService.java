package services.Interfaces;

import java.util.List;

import javax.ejb.Remote;

import entities.Objectif;



@Remote
public interface IObjectifService {
	public Objectif addObjectif(Objectif o);
	public List<Objectif> getAllObjectifs();
	public void updateObjectif(Objectif o);
	public void deleteObjectif(int id);
	public Objectif getObjectifByName(String name);

}
