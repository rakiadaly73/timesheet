package tn.esprit.spring.ContratsTest;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;



import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import tn.esprit.spring.entities.Contrat;
import tn.esprit.spring.entities.Employe;
import tn.esprit.spring.repository.ContratRepository;
import tn.esprit.spring.services.IContratService;
import tn.esprit.spring.services.IEmployeService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UnitTest {
	@Autowired
	IContratService cs;
	@Autowired
	IEmployeService es;

	@Autowired
	private ContratRepository cr;

	private static final Logger l = LogManager.getLogger(UnitTest.class);

	@Test(timeout = 2000)
	public void getAllTest() {
		l.info("entring to test getAllContrats");
		Contrat c1 = new Contrat();
		c1.setDateDebut(new Date());
		c1.setSalaire(11);
		c1.setTypeContrat("CDD");

		Contrat c2 = new Contrat();
		c2.setDateDebut(new Date());
		c2.setSalaire(12);
		c2.setTypeContrat("CDI");

		Contrat c3 = new Contrat();
		c3.setDateDebut(new Date());
		c3.setSalaire(13);
		c3.setTypeContrat("SIVP");
		
		cs.ajouterContrat(c1);
		cs.ajouterContrat(c2);
		cs.ajouterContrat(c3);
		long countContrat = cs.nombreDeContrats();
		//test nombredecontrat
        Assert.assertEquals(countContrat, 3L);	
        //test getAll
        Assert.assertEquals(cs.getAll().size(), 3);	
	}
	
	@Test
	public void findByIdTest() {
		String pattern = "MM-dd-yyyy";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		
		
		Date currentDate = new Date() ;
		Contrat c1 = new Contrat();

		c1.setDateDebut(currentDate);
		c1.setSalaire(11);
		c1.setTypeContrat("CDD");
		
		int id =cs.ajouterContrat(c1); 
		Contrat contrat = cs.getById(id) ; 
		String date = simpleDateFormat.format(contrat.getDateDebut() );
		String expected = simpleDateFormat.format(currentDate);
			Assert.assertEquals(date , expected);
			Assert.assertTrue(contrat.getSalaire()==11) ;
			Assert.assertEquals(contrat.getTypeContrat(), "CDD");
			Assert.assertEquals(contrat.getId(), id);
	}

	@Test
	public void addandDeleteContratTest() {

		Contrat c = new Contrat();
		c.setDateDebut(new Date());
		c.setSalaire(1000);
		c.setTypeContrat("CDI");
         int id = cs.ajouterContrat(c) ; 
         //test add
         Assert.assertTrue(cs.getById(id) !=null);
         Assert.assertTrue(cs.getById(id) .getTypeContrat().equals("CDI"));
         Assert.assertTrue(cs.getById(id) .getSalaire()== 1000);
         Assert.assertTrue(cs.getById(id) .getId() == id);
         //test delete 
        cs.deleteContratById(id);
        Assert.assertTrue(cs.getById(id) ==null);
	}

	/*
	@Test(timeout = 2000)
	public void affectEmplToContrat() {

		Contrat c = new Contrat();
		c.setReference(250);
		c.setDateDebut(new Date());
		c.setSalaire(100);
		c.setTypeContrat("CDD");
		when(cr.findById(250)).thenReturn(Optional.of(c));
		List<Employe> allEmloyes = es.getAllEmployes();
		c = es.affecterContratAEmploye(c.getReference(), allEmloyes.get(0).getId());
		assertEquals(c.getEmploye().getId(), cs.getById(c.getReference()).getEmploye().getId());
	}
}*/

}
