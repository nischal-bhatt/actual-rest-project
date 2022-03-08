package actual.rest.project.service;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import actual.rest.project.exception.EmployeeNotFound;
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
	public EntityModel<Employee> getEmployeeById(@PathVariable int empId)
	{
		 Employee returnval = this.service.getEmployeeById(empId);
		 
		 if (null == returnval)
		 {
			 throw new RuntimeException("employee not found la wahlau");
		 }
		 EntityModel<Employee> model= EntityModel.of(returnval);
		 Link link=WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getAllEmployees()).withRel("all-employees");
		 model.add(link);
		 return model;
	}
	 
	@PostMapping("/employees/user")
	public ResponseEntity<Object> saveEmployee(@Valid @RequestBody Employee emp)
	{
		 Employee employee = service.saveEmployee(emp);
	URI uri = 	 ServletUriComponentsBuilder.fromCurrentRequest().path("{employeeId}").buildAndExpand(employee.getEmployeeId() ).toUri();
		 
	
	    return ResponseEntity.created(uri).build();
		 
	}
	
	
	@GetMapping("/employeesNew/{empId}")
	public Employee getEmployeeNewById(@PathVariable int empId)
	{
		 Employee returnval = this.service.getEmployeeById(empId);
		 
		 if (null == returnval)
		 {
			 throw new EmployeeNotFound("employee not found TEST TEST from new custom exception");
		 }
		 
		 return returnval;
	}
	
	
	@DeleteMapping("/employees/delete/{empId}")
	public void deleteEmployee(@PathVariable int empId)
	{
		Employee emp = this.service.deleteEmployee(empId);
		
		 if (null ==emp)
		 {
			 throw new EmployeeNotFound("employee not found TEST TEST from new custom exception");
		 }
	}
}
