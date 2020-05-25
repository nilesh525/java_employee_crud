package com.practice.EmployeeCrud.cotroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.practice.EmployeeCrud.Model.Employee;
import com.practice.EmployeeCrud.ServiceImpl.EmployeeServiceImpl;

@CrossOrigin(origins = {"http://localhost:8080"})
@RestController
public class ControllerClass {
	
	@Autowired
	public EmployeeServiceImpl employeeserv;
	
	@PostMapping("/create")
	public Employee createEmployee(@RequestBody Employee employee){
		System.out.println(employee.toString());
		Employee emp = employeeserv.createEmployee(employee);
		return emp;
	}
	
	@GetMapping("/getEmp")
	public List<Employee> getEmployee(){
		List<Employee> list = employeeserv.getAllEmployee();
		return list;
	}
	
	@PostMapping("/editEmp")
	public Employee updateEmployee(@RequestBody Employee emp) throws Exception {
		int id = emp.getId();
		Employee checkId = employeeserv.getEmployeeByID(id);
		Employee employee;
		if(checkId.getName()!=null)
			employee =employeeserv.updateEmployee(emp);
		else
			throw new Exception("Employee Not Found");
		return employee;
	}
	
	@GetMapping("getEmpById")
	public Employee getOne(@RequestParam int id) {
		return employeeserv.getEmployeeByID(id);
	}
	
	@DeleteMapping("/deleteEmp")
	public ResponseEntity<String> deleteEmployee(@RequestParam int id) {
		employeeserv.deleteEmployee(id);
		return ResponseEntity.ok("DELETE_SUCCESS");
	}
}
