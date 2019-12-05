package resources;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import entities.Commentaire;
import entities.Statu;
import services.Interfaces.ICommentaireServiceLocal;
import services.Interfaces.IStatuServiceLocal;

@Path("com")
public class CommentaireResource {
	
	@EJB
	ICommentaireServiceLocal icl;
	@EJB
	IStatuServiceLocal isl;
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response addCom(Commentaire c) {
		c= icl.addCommentaire(c);
		{
			return Response.status(Response.Status.CREATED).entity(c).build();
		}
	}
}
