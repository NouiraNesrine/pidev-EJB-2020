package login;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import entities.Objectif;
import lombok.Getter;
import lombok.Setter;
import services.Implementations.ObjectifService;
import services.Interfaces.IObjectifService;

@ManagedBean
@SessionScoped
@Getter
@Setter
public class ObjectifBean implements Serializable {
	
private String name;
private String description;
private Objectif ob;
private List<Objectif> objectifs;
private int idObj;

@EJB
IObjectifService os = new ObjectifService();

public void addObjectif() {
	os.addObjectif(new Objectif(name, description));
}

public void updateObjectif() {
	os.updateObjectif(new Objectif(idObj,name, description));
}


public void displayObjectif(Objectif ob)
{
this.setDescription(ob.getDescription());
this.setName(ob.getName());
this.setIdObj(ob.getIdObjectif());
}

public List<Objectif> getObjectifs() {
	objectifs = os.getAllObjectifs();
	return objectifs;
	}

public void deleteObjectif(int idObjectif) {
	os.deleteObjectif(idObjectif);
}




}
