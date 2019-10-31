package services.Implementations;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import entities.Contrat;
import services.Interfaces.IContratServiceLocal;

@Stateless
@LocalBean
public class ContratService implements IContratServiceLocal {
	
	@PersistenceContext(unitName="pidev-ejb")
	EntityManager em;
	List<Contrat> ListContr = new ArrayList<Contrat>();
	Contrat c = new Contrat();
	
	
	@Override
	public int addContrat(Contrat c) {
		
			em.persist(c);
			return c.getIdContrat();
	}

	@Override
	public void updateContrat(int id) {
		Contrat upContr = em.find(Contrat.class, id);
		if(upContr.getIdContrat()==id) {
			upContr.setDateDebut(c.getDateDebut());
			upContr.setTypeContrat(c.getTypeContrat());
			upContr.setSalaire(c.getSalaire());
			while(upContr.getUser().getRole().toString()=="administrateur") {
				upContr.setUser(c.getUser());
			}
			System.out.println(""+upContr);
		}
		System.out.println("no");
	}

	@Override
	public List<Contrat> getAllContrat() {
		ListContr = em.createQuery("from Contrat", Contrat.class).getResultList();
		return ListContr ;
		
	}

	@Override
	public void removeContrat(int id) {
	
			em.remove(em.find(Contrat.class, id));
		
	}

	
	

}
