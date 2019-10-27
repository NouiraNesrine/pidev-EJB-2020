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
	String notAdm = c.getUser().getRole().toString();
	
	@Override
	public int addContrat(Contrat c) {
		while (notAdm=="administrateur") {
			em.persist(c);
			return c.getIdContrat();
		}
		return 0;
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
		
		return ListContr.stream().distinct().collect(Collectors.toList());
		
	}

	@Override
	public void removeContrat(int id) {
		while (notAdm=="administrateur") {
			em.remove(em.find(Contrat.class, id));
		}
	}
	
	

}
