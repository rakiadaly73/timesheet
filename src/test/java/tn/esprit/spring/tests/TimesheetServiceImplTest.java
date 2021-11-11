package tn.esprit.spring.tests;

import java.text.ParseException;
import java.util.List;


import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.annotation.Order;
import org.springframework.test.context.junit4.SpringRunner;
import tn.esprit.spring.services.ITimesheetService;
import tn.esprit.spring.entities.Timesheet;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TimesheetServiceImplTest {


	@Autowired
	ITimesheetService Timesheet; 

	@Test
	@Order(2)
	public void testretrieveAllTimesheet() {
		List<Timesheet> TIMESHEETS= Timesheet.retrieveAllTimesheet(); 
		Assert.assertFalse(TIMESHEETS.isEmpty());
	}

	

	@Test
	@Order(1)
	public void testajouterTimesheet() throws ParseException {
		Timesheet e = new Timesheet("SSII consulting", "Cite El Ghazela");
		//@SuppressWarnings("Unused")
		Timesheet TimesheetAdded = Timesheet.ajouterTimesheet(e); 
		
		Assert.assertNotNull(Timesheet.getTimesheetById(TimesheetAdded.getId()));
		System.out.println(TimesheetAdded.getId());
	}
	

	@Test
	@Order(3)
	public void testupdateTimesheet() throws ParseException   {
		Timesheet e = new Timesheet(5, "IGA Tunisie", "Cite El Ghazela");
		//@SuppressWarnings("Unused")
		Timesheet TimesheetUpdated  = Timesheet.updateTimesheet(e); 
		Assert.assertEquals(e.getRaisonSocial(), TimesheetUpdated.getRaisonSocial());
	}

	
	

	@Test
	@Order(4)
	public void testgetTimesheetById() {
		Timesheet TIMESHEET_RETRIEVED = Timesheet.getTimesheetById(1); 
		Assert.assertEquals(1, TIMESHEET_RETRIEVED.getId());
	}
	

	
	
	
	@Test
	@Order(5)
	public void testdeleteTimesheet() {
		Timesheet.deleteTimesheet(2);
		Assert.assertNull(Timesheet.getTimesheetById(2));
	}
	
	

	
	// 6 tests unitaires  

}