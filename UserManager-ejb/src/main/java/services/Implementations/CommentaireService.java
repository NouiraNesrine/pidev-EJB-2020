package services.Implementations;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import entities.Commentaire;
import entities.Statu;
import entities.User;
import services.Interfaces.ICommentaireServiceLocal;

@Stateless
public class CommentaireService implements ICommentaireServiceLocal {
	@PersistenceContext(unitName="pidev-ejb")
	EntityManager em;
	User u = new User();
	Statu s = new Statu();
	@Override
	public void addCommentaire(Commentaire c,int id) {
		if(u.getRole().toString()=="employe" && s.getIdStatu()==id) {
			em.persist(c);
		}
	}

	

	@Override
	public void deleteCommentaire(int id) {
		em.remove(em.find(Commentaire.class, id));

	}



	@Override
	public void getComByUser() {
		em.find(Commentaire.class, primaryKey)
		
	}

	
	
	

}
