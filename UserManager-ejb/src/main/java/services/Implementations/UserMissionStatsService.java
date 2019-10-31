package services.Implementations;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import entities.User;
import entities.UserMissionStats;
import services.Interfaces.IUserMissionStatsService;

@Stateless
@LocalBean
public class UserMissionStatsService implements IUserMissionStatsService {

	@PersistenceContext(unitName="pidev-ejb")
	EntityManager em;
	@Override
	public UserMissionStats addUserStats(User user) {
		// TODO Auto-generated method stub
		UserMissionStats ums = new UserMissionStats();
		ums.setUser(user);
		em.persist(ums);
		
		return ums;

	}
	@Override
	public void updateParticipantionAmount(User user) {
		// TODO Auto-generated method stub
		Query q = this.em.createQuery("SELECT o FROM UserMissionStats o where o.user = :id");
		q.setParameter("id", user);
		UserMissionStats entity = (UserMissionStats) q.getSingleResult();
		entity.setParticipantionAmount(entity.getParticipantionAmount()+1);
	}
	@Override
	public void updateApplicationAmount(User user) {
		// TODO Auto-generated method stub
		Query q = this.em.createQuery("SELECT o FROM UserMissionStats o where o.user = :id");
		q.setParameter("id", user);
		UserMissionStats entity = (UserMissionStats) q.getSingleResult();
		entity.setApplicationAmount(entity.getApplicationAmount()+1);
		
	}
	@Override
	public UserMissionStats getUserStats(User user) {
		// TODO Auto-generated method stub
		
		
		UserMissionStats entity = null;
		try{
			Query q = this.em.createQuery("SELECT o FROM UserMissionStats o where o.user = :id");
			q.setParameter("id", user);
			entity = (UserMissionStats) q.getSingleResult();
		}
			
		catch (NoResultException nre){
		//Ignore this because as per your logic this is ok!
		}

		return entity;
		
	}

}
