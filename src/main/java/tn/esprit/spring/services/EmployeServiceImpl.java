package tn.esprit.spring.services;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tn.esprit.spring.entities.Contrat;
import tn.esprit.spring.entities.Departement;
import tn.esprit.spring.entities.Employe;
import tn.esprit.spring.entities.Entreprise;
import tn.esprit.spring.entities.Mission;
import tn.esprit.spring.entities.Timesheet;
import tn.esprit.spring.repository.ContratRepository;
import tn.esprit.spring.repository.DepartementRepository;
import tn.esprit.spring.repository.EmployeRepository;
import tn.esprit.spring.repository.TimesheetRepository;

@Service
public class EmployeServiceImpl implements IEmployeService {

	@Autowired
	EmployeRepository employeRepository;
	@Autowired
	DepartementRepository deptRepoistory;
	@Autowired
	ContratRepository contratRepoistory;
	@Autowired
	TimesheetRepository timesheetRepository;

	public int ajouterEmploye(Employe employe) {
		employeRepository.save(employe);
		return employe.getId();
	}

	public void mettreAjourEmailByEmployeId(String email, int employeId) {
		Employe employe = new Employe();
		Optional<Employe> e = employeRepository.findById(employeId);
		if(e.isPresent()) {
		employe = e.get();}
		employe.setEmail(email);
		employeRepository.save(employe);

	}

	@Transactional	
public void affecterEmployeADepartement(int employeId, int depId) {
		
		Departement departement = new Departement();
		Optional<Departement> d = deptRepoistory.findById(depId);
		if(d.isPresent()) {
		departement = d.get();}
	
		deptRepoistory.save(departement);	
		Employe employe = new Employe();
		Optional<Employe> e = employeRepository.findById(employeId);
		if(e.isPresent()) {
		employe = e.get();}
		if(departement.getEmployes() == null){

			List<Employe> employes = new ArrayList<>();
			employes.add(employe);
			departement.setEmployes(employes);
		}else{

			departement.getEmployes().add(employe);}

		}

	
	@Transactional
	public void desaffecterEmployeDuDepartement(int employeId, int depId)
	{
		
		Departement departement = new Departement();
		Optional<Departement> d = deptRepoistory.findById(depId);
		if(d.isPresent()) {
		departement = d.get();}
		
        int employeNb = departement.getEmployes().size();
		for(int index = 0; index < employeNb; index++){
			if(departement.getEmployes().get(index).getId() == employeId){
				departement.getEmployes().remove(index);
				break;//a revoir
			}
		}
	}

	public int ajouterContrat(Contrat contrat) {
		contratRepoistory.save(contrat);
		return contrat.getId();
	}

	public void affecterContratAEmploye(int contratId, int employeId) {
		
		Contrat contrat = new Contrat();
		Optional<Contrat> c = contratRepoistory.findById(contratId);
		if(c.isPresent()) {
		contrat = c.get();}
	     contratRepoistory.save(contrat);

		Employe employe = new Employe();
		Optional<Employe> e = employeRepository.findById(employeId);
		if(e.isPresent()) {
		employe = e.get();}

		employeRepository.save(employe);

		contrat.setEmploye(employe);
		contratRepoistory.save(contrat);
		
	}

	public String getEmployePrenomById(int employeId) {
		Employe employe = new Employe();
		Optional<Employe> e = employeRepository.findById(employeId);
		if(e.isPresent()) {
		employe = e.get();}

	

		return employe.getPrenom();
	}
	public void deleteEmployeById(int employeId)
	{
		Employe employe = new Employe();
		Optional<Employe> e = employeRepository.findById(employeId);
		if(e.isPresent()) {
		employe = e.get();}
	


		//Desaffecter l'employe de tous les departements
		//c'est le bout master qui permet de mettre a jour
		//la table d'association
		for(Departement dep : employe.getDepartements()){
			dep.getEmployes().remove(employe);
		}

		employeRepository.delete(employe);
	}

	public void deleteContratById(int contratId) {
		Contrat contrat = new Contrat();
		Optional<Contrat> c = contratRepoistory.findById(contratId);
		if(c.isPresent()) {
		contrat = c.get();}
	     contratRepoistory.save(contrat);

		contratRepoistory.delete(contrat);

	}

	public int getNombreEmployeJPQL() {
		return employeRepository.countemp();
	}
	
	public List<String> getAllEmployeNamesJPQL() {
		return employeRepository.employeNames();

	}
	
	public List<Employe> getAllEmployeByEntreprise(Entreprise entreprise) {
		return employeRepository.getAllEmployeByEntreprisec(entreprise);
	}

	public void mettreAjourEmailByEmployeIdJPQL(String email, int employeId) {
		employeRepository.mettreAjourEmailByEmployeIdJPQL(email, employeId);

	}
	public void deleteAllContratJPQL() {
         employeRepository.deleteAllContratJPQL();
	}
	
	public float getSalaireByEmployeIdJPQL(int employeId) {
		return employeRepository.getSalaireByEmployeIdJPQL(employeId);
	}

	public Double getSalaireMoyenByDepartementId(int departementId) {
		return employeRepository.getSalaireMoyenByDepartementId(departementId);
	}
	
	public List<Timesheet> getTimesheetsByMissionAndDate(Employe employe, Mission mission, Date dateDebut,
			Date dateFin) {
		return timesheetRepository.getTimesheetsByMissionAndDate(employe, mission, dateDebut, dateFin);
	}

	public List<Employe> getAllEmployes() {
				return (List<Employe>) employeRepository.findAll();
	}

}
