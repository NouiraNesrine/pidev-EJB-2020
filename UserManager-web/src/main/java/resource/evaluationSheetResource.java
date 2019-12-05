package resource;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import services.Implementations.EvaluationSheetService;
import services.Interfaces.IEvaluationSheetService;

@Path("evaluations")
@RequestScoped
public class evaluationSheetResource {
	
	@EJB
	IEvaluationSheetService ess= new EvaluationSheetService();
	
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getEvaluations() {
		
		return Response.ok(ess.getAllEvaluations()).build();
		
	}

}
