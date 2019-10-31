package timesheetBean;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import enumerations.isActif;


@ManagedBean(name = "dataIsActif") 
@ApplicationScoped
public class dataIsActif {

	
	public isActif[] getActivity()
	{
		return isActif.values();
	}
}
