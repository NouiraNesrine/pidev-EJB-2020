package resource;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import entities.Decision;
import services.Implementations.DecisionService;
import services.Interfaces.IDecisionService;

@Path("decisions")
@RequestScoped
public class DecisionResource {
	
	@EJB
	IDecisionService ds = new DecisionService();
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addDecision(Decision d) {
		ds.addDecision(d);
		return Response.status(Response.Status.OK).entity("add successfully").build();
	}

}
