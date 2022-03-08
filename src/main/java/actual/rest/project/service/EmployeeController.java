package actual.rest.project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import actual.rest.project.model.Employee;
import actual.rest.project.model.EmployeeDao;

@RestController
public class EmployeeController {

	@Autowired
	EmployeeDao service;
	
	@GetMapping("/employees")
	public List<Employee> getAllEmployees()
	{
		return this.service.getAllEmployees();
	}
	
	@GetMapping("/employees/{empId}")
	public Employee getEmployeeById(@PathVariable int empId)
	{
		 Employee returnval = this.service.getEmployeeById(empId);
		 
		 return returnval;
	}
	 
	@PostMapping("/employees/user")
	public void saveEmployee(@RequestBody Employee emp)
	{
		 service.saveEmployee(emp);
	}
}
