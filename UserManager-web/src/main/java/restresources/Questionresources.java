package restresources;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import entities.Question;
import services.Implementations.QuestionService;
import services.Implementations.TopicService;

@Path("/test/question")
@RequestScoped
public class Questionresources {
	@EJB
	QuestionService question;
	@EJB
	TopicService ser;
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/ajouter")
	public Response ajout_question(Question q)  {
		return Response.ok(question.ajout_question(q) , MediaType.APPLICATION_JSON).build();
	}
	@GET
	@Path("/afficherquestion/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response afficherquestionpartopic(@PathParam("id") int id) {
		return Response.ok(ser.getquestions(id)).build();
	}
	@GET
	@Path("/getlistereponsequestions/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getlistereponsequestions(@PathParam("id") int id) {
		return Response.ok(ser.getlistereponsequestions(id)).build();
	}
}
