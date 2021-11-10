package tn.esprit.spring.services;
import java.util.List;

import tn.esprit.spring.entities.Departement;
public interface IDepartmentService {

	public int ajouterDepartement(Departement dep);
	public void deleteDepartementById(int depId);
	public Departement getDepartementById(int depId);
	public List<Departement> getAllDepartements();
}
