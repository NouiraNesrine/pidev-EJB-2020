package login;

import java.io.Serializable;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import enumerations.Categorie;
import enumerations.Niveau;
import enumerations.Role;
import enumerations.SkillsReferences;

@ManagedBean
@ApplicationScoped
public class DataBean implements Serializable {
	private static final long serialVersionUID = 1L;

	public Role[] getRoles() {
		return Role.values();
		}
	
	public Niveau[] getNiveaus() {
		return Niveau.values();
		}
	
	public Categorie[] getCategories() {
		return Categorie.values();
		}
	public SkillsReferences[] getSkillsReferencess() {
		return SkillsReferences.values();
		}
	}
