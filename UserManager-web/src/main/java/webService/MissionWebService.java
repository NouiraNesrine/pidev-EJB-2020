package webService;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.net.ssl.SSLEngineResult.Status;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import entities.Mission;
import services.Implementations.MissionService;
import services.Interfaces.IMissionServiceLocal;


@RequestScoped
@Path("/Mission")
public class MissionWebService {

	private static final long serialVersionUID = 18259999496077L;
	@EJB
	IMissionServiceLocal missionService = new MissionService();
	
	@GET
	@Path("/Aproved")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllAprovedMissions()
	{
		List<Mission> missions = new ArrayList<Mission>(missionService.getMissionsAproved());
		return Response.ok(missions).build();
	}
	
	@DELETE
	@Path("Delete/{id}")
	public Response deleteMission(@PathParam(value="id") int id) {
		
		missionService.removeMission(id);
		return Response.ok().build();
    
	}
	
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getMission(@PathParam(value="id") int id) 
	{
		Mission mission = missionService.getMissionById(id);
		return Response.ok(mission).build();
	}
}
