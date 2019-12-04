package restresources;

import javax.ejb.EJB;
import javax.faces.bean.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import entities.Domain;
import enumerations.TypeDomain;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;

import services.Implementations.DomainService;
//a
@RequestScoped
@Path("DomainResources")
public class Domainresources {

	@EJB
	DomainService domain;
	
	@POST	
	@Path("/addDoamin")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response ajouterDomain(Domain dom) {	
		return Response.ok(domain.ajouterDomain(dom), MediaType.TEXT_PLAIN).build();
	}
	
	@GET
	@Path("/afficher")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getDomain() {
		return Response.ok(domain.getlist()).build();
	}
	
	@DELETE
	@Path("/supprimer/{id}")
	@Produces( MediaType.APPLICATION_JSON)
	public void deleteDomainById(@PathParam("id") int id) {
		domain.deleteDomainById(id);
		System.out.println("deleeeteeee");
	}
	@GET
	@Path("{nom}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getProjectById(@PathParam("nom") String name) {
		System.out.println("hahahha");
		return Response.ok(domain.finddomainbyname(name), MediaType.APPLICATION_JSON).build();
	}
	@PUT
	@Path("/update/{name}/{typee}/{domainId}")
	public Response AffectResponceToQuestion(@PathParam(value = "name") String name,@PathParam(value = "typee") TypeDomain typee,@PathParam(value = "domainId") int domainId) {
		domain.mettreAjourDomain(name,typee,domainId);
		return Response.ok( MediaType.APPLICATION_JSON).build();
		
	}
}
