package services.Implementations;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import entities.Objectif;
import services.Interfaces.IObjectifService;
@Stateless
@LocalBean
public class ObjectifService implements IObjectifService {
	
	@PersistenceContext(unitName="pidev-ejb")
	EntityManager em;
	Objectif ob= new Objectif();

	@Override
	public Objectif addObjectif(Objectif o) {

		em.persist(o);
		return o;
	}

	@Override
	public List<Objectif> getAllObjectifs() {
		List<Objectif> listO=  em.createQuery("from Objectif ", Objectif.class).getResultList();
		
		return listO;
	}
	
	@Override
	public void updateObjectif(Objectif o) {
		Objectif ob = em.find(Objectif.class, o.getIdObjectif());
		if(ob.getIdObjectif()==o.getIdObjectif()) {
			ob.setName(o.getName());
			ob.setDescription(o.getDescription());
		}
	}

	@Override
	public void deleteObjectif(int id) {
		em.createQuery("delete from Objectif o where o.id=" + id).executeUpdate();
	}

	@Override
	public Objectif getObjectifByName(String name) {

		TypedQuery<Objectif> query =
				em.createQuery("SELECT o FROM Objectif o where o.name=:name",Objectif.class);
				query.setParameter("name", name);
				Objectif ob = null;
				try { ob = query.getSingleResult(); }
				catch (Exception e) { System.out.println("Erreur : " + e); }
				return ob;
	}

}
