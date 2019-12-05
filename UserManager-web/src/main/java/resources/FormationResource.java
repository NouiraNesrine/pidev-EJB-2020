package resources;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.ejb.EJB;
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
import javax.ws.rs.core.Response.Status;

import entities.Formation;
import enumerations.Categorie;
import services.Interfaces.IformationServiceLocal;

@Path("/formation")
public class FormationResource {

	@EJB
	IformationServiceLocal ifs;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("all")
	public Response getAllF() {
		return Response.status(Status.ACCEPTED).entity(ifs.getAllFormation()).build();
	}
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/aff")
	public Response affecter(@QueryParam("idf") int idf, @QueryParam("idu") int idu) {
		ifs.affecterUserAFormation(idf, idu);
		return Response.status(Status.ACCEPTED).entity("Affecté avec succés").build();
	}
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("update")
	public Response updateF(Formation f) {
		System.out.println("dddddddddddddd");
		ifs.updateFormation(f);
		return Response.status(Status.ACCEPTED).build();
	}
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("add")
	public Response addeF(Formation f) {
		ifs.ajouterFormation(f);

		return Response.status(Status.ACCEPTED).build();
	}
	@GET
	@Path("update/{dateFin}/{dateDebut}/{categorie}/{formateur}/{nomF}/{id}")
	public Response updateFF(@PathParam("dateFin") String dateFin, @PathParam("dateDebut") String dateDebut,
			@PathParam("categorie") Categorie categorie, @PathParam("formateur") String formateur,
			@PathParam("nomF") String nomF, @PathParam("id") int id) throws ParseException {
		Formation f = new Formation();
		f.setDateDebutF(new SimpleDateFormat("yyyy-MM-dd").parse(dateDebut));
		f.setDateFinF(new SimpleDateFormat("yyyy-MM-dd").parse(dateFin));
		f.setFormateur(formateur);
		f.setCategorie(categorie);
		f.setNomF(nomF);
		f.setIdFormation(id);		
		ifs.updateFormation(f);
		return Response.status(Status.OK).build();
	}
	@DELETE
	@Path("delete/{id}")
	public Response delete(@PathParam("id") int id) {
		ifs.deleteFormationById(id);
		return Response.status(Status.ACCEPTED).build();
	}

	@GET
	@Path("add/{dateFin}/{dateDebut}/{categorie}/{formateur}/{nomF}")
	public Response addF(@PathParam("dateFin") String dateFin, @PathParam("dateDebut") String dateDebut,
			@PathParam("categorie") Categorie categorie, @PathParam("formateur") String formateur,
			@PathParam("nomF") String nomF) throws ParseException {
		Formation f = new Formation();
		f.setDateDebutF(new SimpleDateFormat("yyyy-MM-dd").parse(dateDebut));
		f.setDateFinF(new SimpleDateFormat("yyyy-MM-dd").parse(dateFin));
		f.setFormateur(formateur);
		f.setCategorie(categorie);
		f.setNomF(nomF);
		
		ifs.ajouterFormation(f);
		return Response.status(Status.CREATED).build();
	}
}
