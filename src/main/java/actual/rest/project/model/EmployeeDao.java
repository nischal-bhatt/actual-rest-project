package actual.rest.project.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class EmployeeDao {
   static List<Employee> list = new ArrayList<>();
   
   
   static {
	   list.add(new Employee(11,"nish","nish@gmail.com"));
	   list.add(new Employee(12,"adam","adam@gmail.com"));
	   list.add(new Employee(13,"lambert","lambert@gmail.com"));
	   list.add(new Employee(14,"test","test@gmail.com"));
	   list.add(new Employee(15,"test123","test123@gmail.com"));
   }
   
   public List<Employee> getAllEmployees()
   {
	   return list;
   }

public Employee getEmployeeById(int empId) {
	
	for (int i =0; i<list.size(); i++)
	{
		if (empId == list.get(i).getEmployeeId())
		{
			return list.get(i);
		}
	}
	
	return null;
}

public Employee saveEmployee(Employee emp) {
	emp.setEmployeeId(list.size()+1);
	list.add(emp);
	return emp;
	
}
}
