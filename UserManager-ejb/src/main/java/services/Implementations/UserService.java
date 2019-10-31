package services.Implementations;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import entities.Contrat;
import entities.User;
import services.Interfaces.IUserServiceLocal;

@Stateless
@LocalBean
public class UserService implements IUserServiceLocal {

	@PersistenceContext(unitName = "pidev-ejb")
	EntityManager em;
	User us = new User();
	List<User> listEmploye = new ArrayList<User>();

	@Override
	public User addUser(User u) {
		em.persist(u);
		return u;
	}

	@Override
	public List<User> getAllUser() {
		return em.createQuery("SELECT u from User u ", User.class).getResultList();
	}

	@Override
	public void updateUser(User u) {
		User up = em.find(User.class, u.getIdUser());

		if (up.getIdUser() == u.getIdUser()) {
			up.setFirstname(u.getFirstname());
			up.setLastname(u.getLastname());
			up.setPassword(u.getPassword());
			up.setEmail(u.getEmail());
			up.setRole(u.getRole());
			up.setActiv(u.isActiv());
			System.out.println("Updated !!" + up);
		}
		System.out.println("No");

	}

	@Override
	public void deleteUser(int id) {

		em.remove(em.find(User.class, id));
		System.out.println("Deleted!!!");

	}

	@Override
	public User getUserByEmailAndPassword(String email, String password) {

		TypedQuery<User> query = em.createQuery("SELECT u FROM User u where u.email=:email AND u.password=:password",
				User.class);
		query.setParameter("email", email);
		query.setParameter("password", password);
		User us = null;
		try {
			us = query.getSingleResult();
		} catch (Exception e) {
			System.out.println("Erreur : " + e);
		}
		return us;
	}
	
	@Override
	public List<User> getEmploye() {
		return getAllUser().stream().filter(e->e.getRole().toString()=="employe").collect(Collectors.toList());
		/*return em.createQuery("Select c from User c where c.role like 'employe'", User.class).getResultList();*/
	}

	@Override
	public void affecterContratAEmploye(int idc, int ide) {
		{
			Contrat contratManagedEntity = em.find(Contrat.class, idc);
			/* User employeManagedEntity = */

			listEmploye = getEmploye();
			for (User e : listEmploye) {
				System.out.println(e.getFirstname());
				if (e.getIdUser() == ide) {
					contratManagedEntity.setUser(e);
					em.merge(contratManagedEntity);
					break;
				}

			}

		}

	}

	@Override
	public void addSignature(User u,int id) {
		
		User up = getUserById(id);
		up.setValue(u.getValue());
		em.merge(up);
		
	}
	@Override
	public User getUserById(int id) {
		return em.find(User.class, id);
		
	}

}
