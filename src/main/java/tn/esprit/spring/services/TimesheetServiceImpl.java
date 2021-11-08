package tn.esprit.spring.services;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.entities.Timesheet;
import tn.esprit.spring.repository.ITimesheeteRepository;

@Service
public class TimesheetServiceImpl implements ITimesheetService{
	
	@Autowired
	ITimesheeteRepository TIMESHEET_REPOSITORY;
	
	private static final Logger l = LogManager.getLogger(TimesheetServiceImpl.class);


	@Override
	public List<Timesheet> retrieveAllTimesheet() {
		
		List<Timesheet> Timesheets = null; 
		try {
	
			l.info("In retrieveAllTimesheet() : ");
			Timesheets = (List<Timesheet>) TIMESHEET_REPOSITORY.findAll();  
			for (Timesheet Timesheet : Timesheets) {
				l.debug("Timesheet +++ : " + Timesheet);
			} 
			l.info("Out of retrieveAllTimesheet() : ");
		}catch (Exception e) {
			l.error("Error in retrieveAllTimesheet() : " + e);
		}

		return Timesheets;
	}

	@Override
	public Timesheet ajouterTimesheet(Timesheet e) {
		Timesheet es = new Timesheet();
		try{
		l.info("In ajouterTimesheet() : ");
		es = TIMESHEET_REPOSITORY.save(e);
		l.debug("Timesheet : " + es);
		l.info("Out ajouterTimesheet() : ");
		}catch (Exception ex){
			l.error("Error in ajouterTimesheet() : " +ex);
		}
		return es;
	}

	@Override
	public void deleteTimesheet(long id) {
		try{
			l.info("In deleteTimesheet() :" );
			TIMESHEET_REPOSITORY.deleteById(id);
			l.info("Out deleteTimesheet() :");
			
		}catch (Exception e){
			l.error("Error in deleteTimesheet() : " +e);
		}		
	}

	@Override
	public Timesheet updateTimesheet(Timesheet e) {
		Timesheet es = new Timesheet();
		try{
		l.info("In ajouterTimesheet() : ");
		es = TIMESHEET_REPOSITORY.save(e);
		l.debug("Timesheet : " + es);
		}catch (Exception ex){
			l.error("Error in ajouterTimesheet() : " +ex);
		}
		return es;
	}


	@Override
	public Timesheet getTimesheetById(long id) {
		l.info("in  retrieveTimesheet id = " + id);
		Timesheet c =  TIMESHEET_REPOSITORY.findById(id).orElse(null); 
		l.info("Timesheet returned : " + c);
		return c; 
	}

}