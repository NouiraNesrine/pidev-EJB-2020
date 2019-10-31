package services.Interfaces;

import java.util.List;

import javax.ejb.Local;

import entities.Commentaire;

@Local
public interface ICommentaireServiceLocal {
	
	public void addCommentaire(Commentaire c,int ids);
	
	/*public Commentaire getComByIdStatu();*/
	
	public void deleteCommentaire(int id);
	public void getComByUser();
}
