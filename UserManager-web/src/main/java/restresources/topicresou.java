package restresources;

import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.RequestScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import entities.Topic;
import services.Implementations.DomainService;
import services.Implementations.TopicService;

@RequestScoped
@Path("topicResources")
public class topicresou {
	@EJB
	TopicService ser ;
	

	@GET
	@Path("/afficher")
	@Produces(MediaType.APPLICATION_JSON)

	public Response getDomain() {
		return Response.ok(ser.getlist()).build();
	}
	
	
	@GET
	@Path("/getstat")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getTopictByName() {
		List<Object> top = ser.chartTopic();
		
			return Response.ok(top, MediaType.APPLICATION_JSON).build();
	}
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/ajouter")
	public Response addDomain(Topic t) {
		return Response.ok(ser.ajouterTopic(t), MediaType.TEXT_PLAIN).build();
	}
	
	@GET
	@Path("/top")
	@Produces(MediaType.APPLICATION_JSON)

	public Response getTopic(@QueryParam("id") int id) {
		return Response.ok(ser.gettopics(id)).build();
	}
	
	@GET
	@Path("/recherche/{dom}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response rechechertopic(@PathParam("dom") String dom) {
		return Response.ok(ser.rechechertopic(dom)).build();
	}
	
	 @DELETE
	    @Path("supprimer/{id}")
	    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	    public void deletetopicById(@PathParam("id") int id) {
		 ser.deletetopicById(id);
	    }
	 @GET
		@Path("/topicid/{id}")
		@Produces(MediaType.APPLICATION_JSON)

		public Response getTopicByid(@PathParam("id") int id) {
			return Response.ok(ser.getTopicById(id)).build();
		}
	 @PUT
	 	@Path("/updateTopic")
	    public Response updateUser(Topic t) {

	        if (ser.updateTopic(t)) {
	        	  return Response.ok().status(Response.Status.ACCEPTED).build();
	           // return Response.ok().status(Response.Status.NO_CONTENT).build();
	        } else {
	            return Response.notModified().build();
	        }           
	    }
	 @GET
		@Path("getTopicbyname/{name}")
		@Produces(MediaType.APPLICATION_JSON)
		public Response getTopicByName(@PathParam("name") String name) {
			List<Topic> t = ser.findByNamee(name);
			
				return Response.ok(t, MediaType.APPLICATION_JSON).build();
		}

}
