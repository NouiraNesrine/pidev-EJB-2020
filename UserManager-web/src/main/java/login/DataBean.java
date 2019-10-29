package login;

import java.io.Serializable;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import enumerations.Role;

@ManagedBean
@ApplicationScoped
public class DataBean implements Serializable {
	private static final long serialVersionUID = 1L;

	public Role[] getRoles() {
		return Role.values();
		}
	}
