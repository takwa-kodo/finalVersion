package tn.esprit.timesheet;



import java.util.List;
import org.springframework.boot.test.context.SpringBootTest;





import org.springframework.beans.factory.annotation.Autowired;

import tn.esprit.timesheet.entities.Employe;
import tn.esprit.timesheet.repository.ContratRepository;
import tn.esprit.timesheet.repository.DepartementRepository;
import tn.esprit.timesheet.repository.EmployeRepository;
import tn.esprit.timesheet.services.IEmployeService;




import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;



import org.springframework.beans.factory.annotation.Autowired;


import org.apache.commons.logging.Log;
import org.assertj.core.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.annotation.Rollback;

import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;



@SpringBootTest
public class EmployeTest {

    @Autowired
    private EmployeRepository employeRepository;
    @Autowired
    private IEmployeService  employeService;
   
	@Autowired
	DepartementRepository depRep;
	@Autowired
	ContratRepository contratRep;

    // JUnit test for saveEmployee
    @Test
    @Order(1)																																																																
    @Rollback(value = false)
    public void saveEmployeTest(){

        Employe employe = new Employe();
		   employe.setNom("koko");
		   employe.setPrenom("toutaa");
		   employe.setEmail("tita@gyt");
		   employeService.ajouterEmploye(employe);
		   Employe employeBase = employeRepository.findByEmail(employe.getEmail());
           Assertions.assertThat(employe.getNom().equals(employeBase.getNom()));
           employeRepository.deleteById(employeBase.getId());
    }
    Employe emp1;

	@Test
	public void setUp() {
		
		 //final  Logger log = Logger.getLogger(EmployeTest.class);
		   //log.info("debut test ajout");	  
		    emp1=new Employe();
		
		  emp1.setNom("taki");
		   emp1.setPrenom("tay9");
		   emp1.setEmail("loula@gmail.com");
		   Assertions.assertThat(emp1).isNotNull()	; 
		   employeRepository.save(emp1);
		//log.info(" test ajout"+emp1.getPrenom()+" employé ajouté");
		   
		   
	}

	

    @Test
    @Order(2)
    public void getEmployeTest(){
    	
        Employe employe = employeRepository.findById(2).get();

        Assertions.assertThat(employe.getId()).isEqualTo(2);

    }

    @Test
    @Order(3)
    public void getListOfEmployesTest(){


        List<Employe> employes = (List<Employe>)employeRepository.findAll(); 

        Assertions.assertThat(employes.size()).isGreaterThan(0);

    } 

    @Test
    @Order(4)
    @Rollback(value = false)
    public void updateEmployeTest(){

        Employe employe = employeRepository.findById(2).get();

        employe.setEmail("tak@gmail.com");

        Employe employeUpdated =  employeRepository.save(employe);

        Assertions.assertThat(employeUpdated.getEmail().equals("tak@gmail.com"));

    }

    /*@Test
    @Order(5)
    @Rollback(value = false)
    public void deleteEmployeeTest(){

        Employe employe = employeRepository.findById(24).get();
        if(employe!=null)
        employeRepository.deleteById(employe.getId());
    }*/
    
}

      