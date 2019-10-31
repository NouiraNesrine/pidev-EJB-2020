package login;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import entities.Commentaire;
import entities.Statu;
import entities.User;
import enumerations.Role;
import lombok.Getter;
import lombok.Setter;
import services.Implementations.CommentaireService;
import services.Implementations.StatuService;
import services.Interfaces.ICommentaireServiceLocal;
import services.Interfaces.IStatuServiceLocal;

@ManagedBean
@SessionScoped
@Getter
@Setter
public class ChatBean implements Serializable{

	private static final long serialVersionUID = -5637302711132156598L;
	
	private int idCommentaire;
	private int idStatu;
	private String descriptionCom;
	private String descriptionSta;
	private Commentaire c;
	private Statu s;
	private List<Commentaire> commentaires;
	private List<Statu> status;
	@ManagedProperty(value = "#{loginBean}")
	private LoginBean lb;
	
	@EJB
	ICommentaireServiceLocal ucos = new CommentaireService();
	@EJB
	IStatuServiceLocal iss = new StatuService();
	 @PostConstruct
	    public void init() {
		// User u = new User("al","al","vxc","vcxvc",Role.employe);
		 status=new ArrayList<Statu>();
		 status.addAll(iss.getAllStat());
		 
	    }
	public void addStatu() {
		iss.addStatu(new Statu(descriptionCom, lb.getUser()));
	}
	public void addCom(int ids) {
		if(c.getStatous().getIdStatu()==ids) {
			ucos.addCommentaire(new Commentaire(descriptionCom, lb.getUser()),ids);
				}
		}
	public List<Commentaire> getCommentaires() {
		/*iss.getComByIdStatu();*/
		return commentaires;
		}
	public List<Statu> getStatus() {
		/*iss.getComByIdStatu();*/
		return status;
		}
	
}
