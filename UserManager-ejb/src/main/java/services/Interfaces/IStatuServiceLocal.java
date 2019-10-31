package services.Interfaces;

import java.util.List;

import javax.ejb.Local;

import entities.Commentaire;
import entities.Statu;

@Local
public interface IStatuServiceLocal {
	
	public void addStatu(Statu s);
	public List<Commentaire> getComByIdStatu(int id);
	public Statu getstatByid(int id );
	public List<Statu> getAllStat();
}
