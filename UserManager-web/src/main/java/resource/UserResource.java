package resource;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import entities.User;
import services.Implementations.UserService;
import services.Interfaces.IUserServiceLocal;

@Path("users")
@RequestScoped
public class UserResource {
	
	@EJB
	IUserServiceLocal us= new UserService();

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("login")
	public Response login(@QueryParam("email") String email,@QueryParam("password") String password)
	{
		User u=new User();
		u=us.getUserByEmailAndPassword(email, password);
		//System.out.println("12233"+p);
		if (u!=null)
		{System.out.println("aaaa");
			return  Response.ok().entity(u).build();
		}else
		{ System.out.println("abc");
			return  Response.status(Status.NOT_FOUND).build();}
	}
}
