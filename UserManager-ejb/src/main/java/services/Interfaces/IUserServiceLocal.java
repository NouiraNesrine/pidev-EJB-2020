package services.Interfaces;

import java.util.List;

import javax.ejb.Local;

import entities.User;

@Local
public interface IUserServiceLocal {
	public User addUser(User u);
	public List<User> getAllUser();
	public void updateUser(User u);
	public void deleteUser(int id);
	public User getUserByEmailAndPassword(String email, String password);
	
	
}
