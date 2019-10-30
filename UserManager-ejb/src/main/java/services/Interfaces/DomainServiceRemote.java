package services.Interfaces;

import java.util.List;

import javax.ejb.Remote;

import entities.Domain;
import enumerations.TypeDomain;


@Remote
public interface DomainServiceRemote {
	int ajouterDomain(Domain dep);

	void deleteDomainById(int domainld);

	Domain getDomainById(int domainId);

	void mettreAjourDomain(String name, TypeDomain typee, int domainId);


	public List<Domain> getlist();

}
