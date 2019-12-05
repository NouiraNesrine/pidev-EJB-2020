package consom;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import entities.Activity;
import entities.Timesheet;
import services.Implementations.ActivityService;

@Path("/activity")
@RequestScoped
public class activityConsm {
	@EJB
	ActivityService act;
	
	@Path("/get")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllActivities() {
		List<Activity> list = new ArrayList<>();
		list = act.getAllActivities();
		return Response.status(Status.FOUND).entity(list).build();

	}
	


	@POST
	@Consumes(MediaType.APPLICATION_JSON)
//	@Produces(MediaType.APPLICATION_JSON)
	public Response ajouterTimesheet(Activity o)
	{
		
		return Response.status(Status.CREATED).entity(act.ajouterActivity(o)).build();

	}
	
	@DELETE
	  @Path("{id}")
	  public Response deleteModules(@PathParam(value="id")int id)
	  {
	act.deleteActivityById(id);
	  return Response.status(Status.OK).build();}
	 
	@Path("/StartAct")
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public Response UpdateStart(Activity r){
		act.updateStartActivity(r);
		return Response.status(Status.OK).entity("ClockOut").build();
	}
	@Path("/EndAct")
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public Response EndAct(Activity r){
		act.EndActivityEmploye(r);
		return Response.status(Status.OK).entity("ClockOut").build();
	}
	
	 


}
