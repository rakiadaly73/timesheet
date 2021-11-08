package tn.esprit.spring.repository;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


import tn.esprit.spring.entities.Timesheet;


@Transactional
@Repository
public interface ITimesheeteRepository  extends CrudRepository<Timesheet,Long > {

}