package resources;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import entities.User;
import services.Implementations.UserService;
import services.Interfaces.IUserServiceLocal;

@Path("login")
public class LoginResource {
	@EJB
	IUserServiceLocal us = new UserService();
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	
	public Response login(@QueryParam("email") String email,@QueryParam("password") String password) {
		User u = new User();
		u=us.getUserByEmailAndPassword(email, password);
		if(u!=null) {
			return Response.status(Status.CREATED).entity(u).build();
		}
		else
		{
			return Response.status(Status.NOT_FOUND).build();
		}
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	
	public Response register(User u) {
		
		u = us.addUser(u);
		return Response.status(Status.CREATED).entity(u).build();
	}
}
