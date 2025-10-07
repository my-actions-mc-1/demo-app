package sg.edu.iss.staffmanager.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sg.edu.iss.staffmanager.model.Employee;
import sg.edu.iss.staffmanager.repository.EmployeeRepo;

@Service
public class EmployeeServiceImpl implements EmployeeService {
	
	@Autowired
	private EmployeeRepo employeeRepo;
	
//	@Autowired
//	EntityManagerFactory emf;
	
	@Override
	public List<Employee> getAllEmployees() {
		return employeeRepo.findAll();
	}

	@Override
	public void saveEmployee(Employee employee) {
		employeeRepo.saveAndFlush(employee);
	}

	@Override
	public Employee getEmployeeById(long id) {
		return employeeRepo.findById(id).get();
	}

	@Transactional
	public void removeEmployee(Employee employee) {
		employeeRepo.delete(employee);
//		EntityManager em = emf.createEntityManager();
//		Employee employee = em.find(Employee.class, id);
//		em.getTransaction().begin();
//		if (employee != null) {
//			em.remove(employee);
//		}
//		em.getTransaction().commit();
	}
	
}
