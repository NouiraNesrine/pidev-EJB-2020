package resource;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import entities.Objectif;
import services.Implementations.ObjectifService;
import services.Interfaces.IObjectifService;

@Path("objectifs")
@RequestScoped
public class ObjectifResource {
	
	@EJB
	IObjectifService os = new ObjectifService();
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addObjectif(Objectif o) {
		os.addObjectif(o);
		return Response.status(Response.Status.OK).entity("add successfully").build();
	}
	

}
