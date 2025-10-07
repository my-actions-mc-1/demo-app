package sg.edu.iss.staffmanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import sg.edu.iss.staffmanager.model.Employee;

@Repository
public interface EmployeeRepo extends JpaRepository<Employee, Long> {
	
	@Query("SELECT e FROM Employee e where e.id = :id")
	Employee findEmployeeById(@Param("id") String id);
	
}
