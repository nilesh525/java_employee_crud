package com.practice.EmployeeCrud.ServiceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.practice.EmployeeCrud.Dao.EmployeeRepository;
import com.practice.EmployeeCrud.Model.Employee;

@Component
public class EmployeeServiceImpl {
	
	@Autowired
	private EmployeeRepository employeeRepos;
	
	public Employee createEmployee(Employee employee){
		return employeeRepos.save(employee);
	}

	public List<Employee> getAllEmployee() {
		return employeeRepos.findAll();
	}
	
	public Employee updateEmployee(Employee emp) {
		return employeeRepos.save(emp);
	}

	public void deleteEmployee(int id) {
		employeeRepos.deleteById(id);
	}

	public Employee getEmployeeByID(int id) {
		
		return employeeRepos.getOne(id);
	}
}
