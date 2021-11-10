package tn.esprit.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import tn.esprit.spring.entities.Departement;

public interface DepartementRepository extends CrudRepository<Departement, Integer>{

	
    @Query(value="SELECT  * , 0 AS clazz_ FROM departement d WHERE d.entreprise_id= :id", nativeQuery = true)
    public List<Departement> getAllEmployeByEntreprisec(@Param("id")int id);
    
    
    @Query("SELECT count(*) FROM Departement")
    public int countdep(); 
    
    
}