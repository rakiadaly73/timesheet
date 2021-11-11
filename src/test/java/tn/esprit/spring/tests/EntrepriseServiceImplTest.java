package tn.esprit.spring.tests;


import static org.junit.Assert.*;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import tn.esprit.spring.entities.Departement;
import tn.esprit.spring.entities.Entreprise;
import tn.esprit.spring.services.EntrepriseServiceImpl;


@RunWith(SpringRunner.class)
@SpringBootTest
public class EntrepriseServiceImplTest {

	private static final Logger l = (Logger) LogManager.getLogger(EntrepriseServiceImplTest.class);
	
	@Autowired
	EntrepriseServiceImpl es;
	
	@Test
	public void testAddEntreprise() {
		try {
		Entreprise E = new Entreprise("Samsung","EURL");
		int Id = es.ajouterEntreprise(E);
		assertNotNull(Id);
		es.deleteEntrepriseById(Id);
		l.info("Add Entreprise works");
		} catch (NullPointerException e) {
			l.error(e.getMessage());
		}
	}
	
	@Test
	public void testUpdateEntreprise() {
		try {
		Entreprise E = new Entreprise("Samsung","EURL");
		int Id = es.ajouterEntreprise(E);
		E.setName("Iphone");
		es.ajouterEntreprise(E);
		Entreprise Ese = es.getEntrepriseById(Id);
		assertEquals("Iphone",Ese.getName());
		es.deleteEntrepriseById(Id);
		l.info("Update Entreprise works");
		} catch (NullPointerException e) {
			l.error(e.getMessage());
		}
	}
	
	//test % size of list 
	@Test
	public void testDeleteEntrepriseById_METHOD1() {
		try {
		Entreprise entreprise = new Entreprise("Samsung","EURL");
		// ajouter une entreprise :
		int Id = es.ajouterEntreprise(entreprise);
		int firstSize = es.getAllEntreprises().size();
		// supprimer l'entreprise ajoutée :
		es.deleteEntrepriseById(Id);
		int secondSize = es.getAllEntreprises().size();
	      assertNotEquals(firstSize , secondSize);

		l.info("Delete Entreprise (%size) works");
		} catch (NullPointerException e) {
			l.error(e.getMessage());
		}
	}

	


	@Test
	public void testAffectDepartmentToEntreprise(){
		try {
		Entreprise E = new Entreprise("Samsung","EURL");
		int IdE = es.ajouterEntreprise(E);
		Departement D = new Departement("Info");
		int IdD = es.ajouterDepartement(D);
		assertNull(D.getEntreprise());
		es.affecterDepartementAEntreprise(IdD, IdE);
		assertNotNull(D.getEntreprise().getId());
		assertEquals(D.getEntreprise().getId(),IdE);
		es.deleteDepartementById(IdD);
		es.deleteEntrepriseById(IdE);
		l.info("Affect Department to Entreprise works");
		} catch (NullPointerException e) {
			l.error(e.getMessage());
		}
	}
}
