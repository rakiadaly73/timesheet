package tn.esprit.spring.services;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tn.esprit.spring.entities.Departement;
import tn.esprit.spring.entities.Entreprise;
import tn.esprit.spring.repository.DepartementRepository;
import tn.esprit.spring.repository.EntrepriseRepository;


@Service
public class DepartmentServicelmpl implements IDepartmentService {
	
	@Autowired
	DepartementRepository deptRepoistory;
	@Autowired
	EntrepriseRepository entrepriseRepository;
	
	
    //done
	public int ajouterDepartement(Departement dep) {
		deptRepoistory.save(dep);
		return dep.getId();
	}

    
	
	//done
	@Transactional
	public void deleteDepartementById(int depId) {
		
		Departement depManagedEntity;
		Optional<Departement> d = deptRepoistory.findById(depId);
		if(d.isPresent()) {
			depManagedEntity = d.get();
			deptRepoistory.delete(depManagedEntity);}
			
	}

    
	
	//done
	public Departement getDepartementById(int depId) {
		Departement departementManagedEntity = new Departement();
		Optional<Departement> d = deptRepoistory.findById(depId);
		if(d.isPresent()) {
			departementManagedEntity = d.get();}
		return departementManagedEntity;
	}

    
	//done
	public List<Departement> getAllDepartements() {
		return (List<Departement>) deptRepoistory.findAll();
	}
	
	
	//done
	public void mettreAjourNameDepartmentByDepartmentId(String name, int departmentId) {
		Departement department = new Departement();
		Optional<Departement> d = deptRepoistory.findById(departmentId);
		if(d.isPresent()) {
			department = d.get();}
		department.setName(name);
		deptRepoistory.save(department);

	}
	
	
	//done
	@Transactional	
	public void affecterDepartementAEntreprise(int departmentId, int entrepriseId) {
		
		Entreprise entrepriseManagedEntity = new Entreprise();
		Optional<Entreprise> e = entrepriseRepository.findById(entrepriseId);
		if(e.isPresent()) {
			entrepriseManagedEntity = e.get();}
		
		Departement departementManagedEntity = new Departement();
		Optional<Departement> d = deptRepoistory.findById(departmentId);
		if(d.isPresent()) {
			departementManagedEntity = d.get();}
		
		
			departementManagedEntity.setEntreprise(entrepriseManagedEntity);
			deptRepoistory.save(departementManagedEntity);
			entrepriseRepository.save(entrepriseManagedEntity);
			
		

	}
	
	//done
	@Transactional
	public void desaffecterDepartementDuEntreprise(int departmentId, int entrepriseId)
	{
        Departement d = deptRepoistory.findById(departmentId).orElse(new Departement());
        Entreprise e = entrepriseRepository.findById(entrepriseId).orElse(new Entreprise());
        d.setEntreprise(null); deptRepoistory.save(d);
        e.getDepartements().remove(d); entrepriseRepository.save(e);
	}
	
	
	
	public List<Departement> getAllDepartmentByEntreprise(int entrepriseId) {
		return deptRepoistory.getAllEmployeByEntreprisec(entrepriseId);
	}
	
	
	
	public int getNombreDepartment() {
		return deptRepoistory.countdep();
	}
	
	
	

}
