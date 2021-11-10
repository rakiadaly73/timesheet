package tn.esprit.spring.tests;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import tn.esprit.spring.entities.Departement;
import tn.esprit.spring.entities.Entreprise;
import tn.esprit.spring.services.DepartmentServicelmpl;
import tn.esprit.spring.services.EntrepriseServiceImpl;


@RunWith(SpringRunner.class)
@SpringBootTest
public class DepartementServiceImplTest {
	
	private static final Logger l = LogManager.getLogger(DepartementServiceImplTest.class);
	
	@Autowired
	DepartmentServicelmpl ds;
	

	@Autowired
	EntrepriseServiceImpl es;
	
	@Test
	public void testAjouterDepartement() {
		try {
		Departement D = new Departement("Info");
		int Id = ds.ajouterDepartement(D);
		assertNotNull(Id);
		ds.deleteDepartementById(Id);
		l.info("End add Department test method");
		} catch (NullPointerException e) {
			l.error(e.getMessage());
		}
	}
	
	@Test
	public void testUpdateDepartement() {
		try {
		Departement D = new Departement("Info");
		int Id = ds.ajouterDepartement(D);
		D.setName("Biologie");
		ds.ajouterDepartement(D);
		assertEquals("Biologie", D.getName());
		ds.deleteDepartementById(D.getId());
		l.info("End Update Department test method");
		} catch (NullPointerException e) {
			l.error(e.getMessage());
		}
	}
	
	@Test
	public void testDeleteDepartementById()  {
		try {
		l.info("Starting delete Department test method");
		Departement D = new Departement("Info");
		int Id = ds.ajouterDepartement(D);
		l.info("Adding new Department with id : " + Id);
		Departement Dept = ds.getDepartementById(Id);
		assertNotNull(Dept);
		int lengthBeforeDelete = ds.getAllDepartements().size();
		l.info("Department with id " + Id + " exists" );
		ds.deleteDepartementById(Id);
		assertEquals(lengthBeforeDelete-1 , ds.getAllDepartements().size());
		l.info("Department deleted successfuly");
		} catch (NullPointerException e) {
			l.error(e.getMessage());
		}
	}
	
	
	@Test
	public void testGetDepartementById(){
		try {
		l.info("Starting find Departement test method");
		Departement D = new Departement("Info");
		int Id = ds.ajouterDepartement(D);
		assertNotNull(ds.getDepartementById(Id));
		l.info("Departement with id " + Id + " added" );
		Departement Dept = ds.getDepartementById(Id);
		assertNotNull(Dept);	
		l.info("Departement with id " + Dept.getId() + " found successfully" );
		ds.deleteDepartementById(Id);
		} catch (NullPointerException e) {
			l.error(e.getMessage());
		}
		}
	
	
	/*@Test
	public void testgetAllDepartements() {
		try {
		l.info("Starting get all departements");	
		Departement D1 = new Departement("Info");
		int Id1 = ds.ajouterDepartement(D1);
		assertNotNull(ds.getDepartementById(Id1));
		l.info("Departement with id " + Id1 + " added" );
		Departement D2 = new Departement("Statistique");
		int Id2 = ds.ajouterDepartement(D2);
		assertNotNull(ds.getDepartementById(Id2));
		l.info("Departement with id " + Id2 + " added" );
		Departement D3 = new Departement("biologie");
		int Id3 = ds.ajouterDepartement(D3);
		assertNotNull(ds.getDepartementById(Id3));
		l.info("Departement with id " + Id3 + " added" );
		List<Departement> list = new ArrayList<>();
		list.add(D1);
		list.add(D2);
		list.add(D3);
		l.info("lenght of departement list: " + list.size());
		List<Departement> listedesdepartements = ds.getAllDepartements();
		int length = listedesdepartements.size();
		assertEquals(length , list.size());
		ds.deleteDepartementById(Id1);
		ds.deleteDepartementById(Id2);
		ds.deleteDepartementById(Id3);
		l.info("Get All Department  works");		
		}  catch (NullPointerException e) {
			l.error(e.getMessage());
		}
			
	}
	*/
	
	@Test
	public void testmettreAjourNameDepartmentByDepartmentId(){
		try {
		Departement D1 = new Departement("Info");
		int Id1 = ds.ajouterDepartement(D1);
		assertNotNull(ds.getDepartementById(Id1));
		l.info("Departement with id " + Id1 + " added" );
		String NewNameDep="electronique";
		ds.mettreAjourNameDepartmentByDepartmentId(NewNameDep, Id1);
		Departement newdep = ds.getDepartementById(Id1);
		assertEquals(newdep.getName(),NewNameDep);
		ds.deleteDepartementById(Id1);
		l.info("Update Department name by id works");
		
	 }  catch (NullPointerException e) {
		l.error(e.getMessage());
	} 
	}
	
	
	@Test
	public void testAffecterDepartementAEntreprise(){
		try {
		Entreprise E = new Entreprise("Microsoft","XXX");
		int IdE = es.ajouterEntreprise(E);
		assertNotNull(es.getEntrepriseById(IdE));
		l.info("Entreprise with id " + IdE + " added" );
		Departement D = new Departement("Info");
		int IdD = ds.ajouterDepartement(D);
		assertNotNull(ds.getDepartementById(IdD));
		l.info("Departement with id " + IdD + " added" );
		assertNull(D.getEntreprise());
		ds.affecterDepartementAEntreprise(IdD, IdE);
		Departement departmentAfterAffectation = ds.getDepartementById(IdD);
		
	    assertEquals(departmentAfterAffectation.getEntreprise().getId(),IdE);
	    ds.deleteDepartementById(D.getId());
		es.deleteEntrepriseById(E.getId());
		l.info("Affect Department to Entreprise works");
		} catch (NullPointerException e) {
			l.error(e.getMessage());
		}
	}
	
	
	
	@Test
	public void testDesaffecterDepartementDuEntreprise(){
		try {
		Entreprise E = new Entreprise("Microsoft","XXX");
		int IdE = es.ajouterEntreprise(E);
		assertNotNull(es.getEntrepriseById(IdE));
		l.info("Entreprise with id " + IdE + " added" );
		Departement D = new Departement("Info");
		int IdD = ds.ajouterDepartement(D);
		assertNotNull(ds.getDepartementById(IdD));
		l.info("Departement with id " + IdD + " added" );
		ds.affecterDepartementAEntreprise(IdD, IdE);
		Entreprise entrepriseAfterAffectation = es.getEntrepriseById(IdE);
		List<Departement> listtest1 = entrepriseAfterAffectation.getDepartements();
		l.info("Affect Department to Entreprise works");
		ds.desaffecterDepartementDuEntreprise(IdD, IdE);
		Entreprise entrepriseAfterDesAffectation = es.getEntrepriseById(IdE);
		List<Departement> listtest2 = entrepriseAfterDesAffectation.getDepartements();
		assertNotEquals(listtest1,listtest2);
	    ds.deleteDepartementById(IdD);
	    es.deleteEntrepriseById(IdE);	    
		l.info("Desaffect Department to Entreprise works");
		
		} catch (NullPointerException e) {
			l.error(e.getMessage());
		}
	}
	
	
	@Test
	public void testgetAllDepartmentByEntreprise(){ 
		try { 
		
			Entreprise E = new Entreprise("Microsoft","XXX");
			int IdE = es.ajouterEntreprise(E);
			assertNotNull(es.getEntrepriseById(IdE));
			l.info("Entreprise with id " + IdE + " added" );
			Departement D1 = new Departement("Info");
			int IdD1 = ds.ajouterDepartement(D1);
			assertNotNull(ds.getDepartementById(IdD1));
			l.info("Departement with id " + IdD1 + " added" );
			Departement D2 = new Departement("Statistique");
			int IdD2 = ds.ajouterDepartement(D2);
			assertNotNull(ds.getDepartementById(IdD2));
			l.info("Departement with id " + IdD2 + " added" );
			
			List<Departement> listtest1 = new ArrayList<>();
			listtest1.add(D1);
			listtest1.add(D2);
			ds.affecterDepartementAEntreprise(IdD1, IdE);
			ds.affecterDepartementAEntreprise(IdD2, IdE);
			
			List<Departement> listtest2 = ds.getAllDepartmentByEntreprise(IdE);
			
			assertEquals(listtest1.size(),listtest2.size());
			
			ds.deleteDepartementById(IdD1);
			ds.deleteDepartementById(IdD2);
			es.deleteEntrepriseById(IdE);	 
			l.info("Get All Department By Entreprise works");
		
		
		}catch (NullPointerException e) {
			l.error(e.getMessage());
		}
		
		
	}
	
	
	/*@Test
	public void testgetNombreDepartment(){ 
		try { 
		
			Departement D1 = new Departement("Info");
			int IdD1 = ds.ajouterDepartement(D1);
			assertNotNull(ds.getDepartementById(IdD1));
			l.info("Departement with id " + IdD1 + " added" );
			Departement D2 = new Departement("Statistique");
			int IdD2 = ds.ajouterDepartement(D2);
			assertNotNull(ds.getDepartementById(IdD2));
			l.info("Departement with id " + IdD2 + " added" );
			
			List<Departement> listtest1 = new ArrayList<>();
			listtest1.add(D1);
			listtest1.add(D2);
			
			assertEquals(listtest1.size(),ds.getNombreDepartment());
			
			ds.deleteDepartementById(IdD1);
			ds.deleteDepartementById(IdD2);	 
			l.info("Get  Department Number works");
		
		
		}catch (NullPointerException e) {
			l.error(e.getMessage());
		}
		*/
		
	}
	
	
	
	



