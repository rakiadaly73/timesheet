package tn.esprit.spring.services;

import java.util.List;

import tn.esprit.spring.entities.Timesheet;

public interface ITimesheetService {

	public Timesheet ajouterTimesheet(Timesheet Timesheet);

	Timesheet getTimesheetById(long id);

	void deleteTimesheet(long id);

	Timesheet updateTimesheet(Timesheet Timesheet);
	
	public List<Timesheet> retrieveAllTimesheet();
}
