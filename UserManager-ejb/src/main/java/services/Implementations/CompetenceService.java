package services.Implementations;

import java.util.List;


import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import entities.Skills;

import services.Interfaces.ICompetenceServiceLocal;



@Stateless
@LocalBean
public class CompetenceService implements ICompetenceServiceLocal {
	@PersistenceContext(unitName="pidev-ejb")
	EntityManager em;
	
	@Override
	public Skills ajouterSkills(Skills skill) {
		em.persist(skill);
		
		return skill;

	}

	@Override
	public List<Skills> getAllEmployes() {
	List<Skills> emp= em.createQuery("Select e from Skills e", Skills.class).getResultList();
	return emp;
	}
	@Override
	public void removeEmp(int idSkills) {
		System.out.println("In removeEmp : ");
		em.remove(em.find(Skills.class, idSkills));
		System.out.println("Out of removeEmp : ");

	}
	
	@Override
	public void updateEmploye(Skills emp) {
		Skills e = em.find(Skills.class, emp.getIdSkills());
		e.setCategorie(emp.getCategorie());
		e.setDescription(emp.getDescription());
		e.setNiveau(emp.getNiveau());
		e.setNomCompetence(emp.getNomCompetence());
		e.setSkillsRef(emp.getSkillsRef());
	



	}

	
	
		 
	
	
}
