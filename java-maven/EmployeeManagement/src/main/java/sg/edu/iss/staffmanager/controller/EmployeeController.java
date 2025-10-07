package sg.edu.iss.staffmanager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import sg.edu.iss.staffmanager.model.Employee;
import sg.edu.iss.staffmanager.service.EmployeeService;

@Controller
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;

	@GetMapping("/")
	public String viewHomePage(Model model) {
		model.addAttribute("listEmployees", employeeService.getAllEmployees());
		return "index";
	}

	@GetMapping("/showNewEmployeeForm")
	public String showNewEmployeeForm(Model model) {
		Employee emp = new Employee();
		model.addAttribute("employee", emp);
		return "new_employee";
	}

	@PostMapping("/saveEmployee")
	public String saveEmployee(@ModelAttribute("employee") Employee employee) {
		employeeService.saveEmployee(employee);
		return "redirect:/";
	}

	@GetMapping("/showFormForUpdate/{id}")
	public String showFormForUpdate(Model model, @PathVariable("id") long id) {
		model.addAttribute("employee", employeeService.getEmployeeById(id));
		return "update_employee";
	}
	
	@PostMapping("/removeEmployee")
	public String removeEmployee(@RequestParam Long employeeID) {
		employeeService.removeEmployee(employeeService.getEmployeeById(employeeID));
		return "redirect:/";
	}
}
