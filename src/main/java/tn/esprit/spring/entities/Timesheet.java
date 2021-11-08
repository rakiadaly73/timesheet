package tn.esprit.spring.entities;

import java.io.Serializable;
import javax.persistence.Entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


@Entity
public class Timesheet implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3152690779535828408L;

	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
	private String name;
	
	
	private String raisonSocial;
	
	@ManyToOne
    @JoinColumn(name = "idMission", referencedColumnName = "id", insertable=false, updatable=false)
	private Mission mission;
	
	//idEmploye est a la fois primary key et foreign key
	
	@ManyToOne
    @JoinColumn(name = "idEmploye", referencedColumnName = "id", insertable=false, updatable=false)
	private Employe employe;
	
	public Timesheet() {
		super();
	}

	public Mission getMission() {
		return mission;
	}

	public void setMission(Mission mission) {
		this.mission = mission;
	}

	public Employe getEmploye() {
		return employe;
	}

	public void setEmploye(Employe employe) {
		this.employe = employe;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Timesheet(String name, String raisonSocial) {
		this.name = name;
		this.raisonSocial = raisonSocial;
	}

	public Timesheet(long id, String name, String raisonSocial) {
		this.id = id;
		this.name = name;
		this.raisonSocial = raisonSocial;
	}

	public long getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRaisonSocial() {
		return raisonSocial;
	}

	public void setRaisonSocial(String raisonSocial) {
		this.raisonSocial = raisonSocial;
	}


}