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

import entities.Timesheet;
import enumerations.isActif;
import services.Implementations.TimesheetService;

@Path("timesheet")
@RequestScoped
public class TimesheetConsom {

	@EJB
	TimesheetService timesheet;

	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getallquestions() {
		return Response.ok(timesheet.getAllTimesheet()).build();
	}

	@DELETE
	  @Path("/supprimer/{id}")
	  public Response deleteModules(@PathParam(value="id")int id)
	  {
	timesheet.deleteTimesheet(id);
	  return Response.status(Status.OK).build();}
	
	@Path("/in")
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public Response ClockIn(Timesheet r){
		timesheet.clockIn(r);
		return Response.status(Status.OK).entity("ClockIn").build();
	}
	@Path("/out")
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public Response ClockOut(Timesheet r){
		timesheet.clockOutday(r);
		return Response.status(Status.OK).entity("ClockOut").build();
	}
	/*
	@PUT
	@Path("/update/{isActif}")
	public Response AffectResponceToQuestion(@PathParam(value = "isActif") isActif isActif,@PathParam(value = "domainId") int domainId) {
		timesheet.clockOut(timesheet);(isActif,domainId);
		return Response.ok( MediaType.APPLICATION_JSON).build();
		
	}
	*/
/*	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addTimesheet(Timesheet d) {
		if (d != null) {
			int i = timesheet.ajouterTimesheet(d);
		}
		return Response.status(Status.CREATED).entity("ok").build();
	}
	*/
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response ajouterTimesheet(Timesheet o)
	{
		
		return Response.status(Status.CREATED).entity(timesheet.ajouterTimesheet(o)).build();

	}
	/*
	@POST	
	@Consumes(MediaType.APPLICATION_JSON)
	public Response ajouterDomain(Timesheet dom) {	
		return Response.ok(timesheet.ajouterTimesheet(dom), MediaType.TEXT_PLAIN).build();
	}
	*/
}
