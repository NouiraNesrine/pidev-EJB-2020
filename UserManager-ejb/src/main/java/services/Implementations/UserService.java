package services.Implementations;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import entities.User;
import services.Interfaces.IUserServiceLocal;

@Stateless
@LocalBean
public class UserService implements IUserServiceLocal {
	
	@PersistenceContext(unitName="pidev-ejb")
	EntityManager em;
	
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
		em.persist(u);
	}

	@Override
	public void deleteUser(int id) {
		
		em.remove(em.find(User.class, id));
		System.out.println("Deleted!!!");

	}

	@Override
	public User getUserByEmailAndPassword(String email, String password) {
		
		TypedQuery<User> query =
				em.createQuery("SELECT u FROM User u where u.email=:email AND u.password=:password",User.class);
				query.setParameter("email", email);
				query.setParameter("password", password);
				User us = null;
				try { us = query.getSingleResult(); }
				catch (Exception e) { System.out.println("Erreur : " + e); }
				return us;
	}

	@Override
	public User getUserById(int idUser) {
		TypedQuery<User> query =
				em.createQuery("SELECT u FROM User u where u.idUser=:idUser ", User.class).setParameter("idUser", idUser);
				User us = null;
				try { us = query.getSingleResult(); }
				catch (Exception e) { System.out.println("Erreur : " + e); }
				return us;
	}
	
	

}
