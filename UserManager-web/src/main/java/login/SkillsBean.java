package login;

import java.io.Serializable;


import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import entities.Skills;

import enumerations.Categorie;
import enumerations.Niveau;

import enumerations.SkillsReferences;
import lombok.Getter;
import lombok.Setter;
import services.Implementations.CompetenceService;


import java.util.List;


@ManagedBean(name="skillsbean")
@SessionScoped
@Getter
@Setter
public class SkillsBean  implements Serializable{
	private static final long serialVersionUID = -418724032872793291L;
	
	private String nomCompetence;
	private String description;
	private Niveau niveau;
	private Categorie categorie;
	private SkillsReferences skillsRef;
	
	@EJB
CompetenceService competenceService ;
	

	public String addSkills() {
		 String navigateTo = "/pages/chahnez/afficherskills?faces-redirect=true"; 
		competenceService.ajouterSkills(new Skills(categorie,description,niveau,nomCompetence,skillsRef));
		return navigateTo;
		
	}
	private List<Skills> employes;
	public List<Skills> getEmployes() {
	employes= competenceService.getAllEmployes();
	return employes;
	}
	
	
	public void removeEmploye(int employeId)
	{
		competenceService.removeEmp(employeId);
	}
	
	private Integer employeIdToBeUpdated;
	public String displayEmploye(Skills empl)
	{
		 String navigateTo = "/pages/chahnez/update?faces-redirect=true"; 
	this.setCategorie(empl.getCategorie());
	this.setDescription(empl.getDescription());
	
	this.setNiveau(empl.getNiveau());
	this.setNomCompetence(empl.getNomCompetence());
	this.setNiveau(empl.getNiveau());
	
    this.setEmployeIdToBeUpdated(empl.getIdSkills());
    return navigateTo;
	}
	public String updateEmploye()
	
	{  String navigateTo = "/pages/chahnez/afficherskills?faces-redirect=true"; 
		competenceService.updateEmploye(new Skills(employeIdToBeUpdated,nomCompetence , description, niveau, categorie, skillsRef));
		return navigateTo;
	}
	
	
}
