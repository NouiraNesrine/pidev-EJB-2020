package resources;



import java.util.List;

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

import entities.Statu;
import login.LoginBean;
import services.Interfaces.IStatuServiceLocal;
import services.Interfaces.IUserServiceLocal;

@Path("statu")
public class StauResource {
	
	@EJB
	IStatuServiceLocal isl;
	
	@EJB
	IUserServiceLocal ius;
	
	
	
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response addStatu(Statu s) {
		s = isl.addStatu(s);
		
		if(s.getDescription()!=null ) {
			return Response.status(Status.CREATED).entity(s).build();
		}
		return Response.status(Status.NO_CONTENT).build();
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAll() {
		List<Statu> ls = isl.getAllStat();
		if(ls.size()==0) {
			return Response.status(Response.Status.NOT_FOUND).build();
		}
		return Response.status(Response.Status.OK).entity(ls).build();
	}
	
	
}
