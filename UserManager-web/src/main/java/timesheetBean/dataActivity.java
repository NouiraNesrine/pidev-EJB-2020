package timesheetBean;

import java.io.Serializable;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import enumerations.statutsActivity;


	
	
@ManagedBean(name = "dataActivity") 
@ApplicationScoped
	public class dataActivity implements Serializable 
	{
	private static final long serialVersionUID = 14567L;
		
		public statutsActivity[] getActivity()
		{
			return statutsActivity.values();
		}
	}


