package services.Implementations;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import entities.Formation;
import entities.User;
import enumerations.Categorie;
import services.Interfaces.IformationServiceLocal;

@Stateless
@LocalBean
public class FormationService implements IformationServiceLocal {
	@PersistenceContext(unitName = "pidev-ejb")
	EntityManager em;
	Formation f = new Formation();
	@Override
	public Formation ajouterFormation(Formation formation) {
		em.persist(formation);
		return formation;

	}

	@Override
	public void deleteFormationById(int id) {
		em.remove(em.find(Formation.class, id));

	}

	@Override
	public List<Formation> getAllFormation() {
		List<Formation> form = em.createQuery("Select e from Formation e", Formation.class).getResultList();
		return form;
	}

	@Override
	public void updateFormation(Formation formation) {
		Formation fo = em.find(Formation.class, formation.getIdFormation());
		fo.setNomF(formation.getNomF());
		fo.setFormateur(formation.getFormateur());
		fo.setDateDebutF(formation.getDateDebutF());
		fo.setDateFinF(formation.getDateFinF());
		fo.setCategorie(formation.getCategorie());

	}

	@Override
	public void affecterUserAFormation(int formationid, int userid) {
		User UserAajout = em.find(User.class, userid);
		Formation FormaManagedEntity = em.find(Formation.class, formationid);

		if (FormaManagedEntity.getUsersParticipants() == null) {
			List<User> Users = new ArrayList<>();
			Users.add(UserAajout);
			FormaManagedEntity.setUsersParticipants(Users);
			;
		} else {
			FormaManagedEntity.getUsersParticipants().add(UserAajout);
		}

	}

	@Override
	public List<User> getAllUserParticipants(int idformation) 
	{
		List<User> us = em.find(Formation.class, idformation).getUsersParticipants();
		return us ;
	}

	@Override
	public List<Formation> getFormatiobByCateg(Categorie c) {
		
	
		/*List<Formation> listF = getAllFormation();
		
		return listF.stream().filter(e->e.getCategorie().equals(c)).collect(Collectors.toList());
	}*/
		

			TypedQuery<Formation> query =  em.createQuery("select m from Formation m where m.categorie = :category", Formation.class);
			query.setParameter("category", c);
			List<Formation> ca = query.getResultList();
			
			return ca;
		}	
	}
	
	

