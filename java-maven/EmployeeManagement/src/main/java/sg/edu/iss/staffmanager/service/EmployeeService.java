package sg.edu.iss.staffmanager.service;

import java.util.List;

import sg.edu.iss.staffmanager.model.Employee;

public interface EmployeeService {
	List<Employee> getAllEmployees();
	
	void saveEmployee(Employee employee);
	
	Employee getEmployeeById(long id);
	
	void removeEmployee(Employee employee);
}
