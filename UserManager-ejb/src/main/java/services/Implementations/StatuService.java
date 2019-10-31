package services.Implementations;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import entities.Commentaire;
import entities.Statu;
import entities.User;
import services.Interfaces.IStatuServiceLocal;

@Stateless
public class StatuService implements IStatuServiceLocal {
	@PersistenceContext(unitName="pidev-ejb")
	EntityManager em;
	User u = new User();
	List<Commentaire> listCom = new ArrayList<Commentaire>();
	@Override
	public void addStatu(Statu s) {
		if(u.getRole().toString()=="administrateur") {
			em.persist(s);
			
		}
		
	}
	@Override
	public List<Commentaire> getComByIdStatu(int ids) {
		/*listCom = em.createQuery("Select, resultClass)*/
		listCom = em.createQuery("Select c from Commentaire c LEFT JOIN c.statous where c.statous.idStatu:=ids ",Commentaire.class).getResultList();
			    
		return listCom;

	}
	@Override
	public Statu getstatByid(int id) {
		// TODO Auto-generated method stub
		return em.find(Statu.class,id);
	}
	@Override
	public List<Statu> getAllStat() {
		return em.createQuery("from Statu",Statu.class).getResultList();
	}

}
