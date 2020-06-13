package com.practice.EmployeeCrud.Dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.practice.EmployeeCrud.Model.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer>{

	@Modifying
	@Query(value="update tbl_employees set is_active = false where id = :id", nativeQuery = true)
	void setActive(int id);

	@Query(value = "select * from tbl_employees where email = :email",nativeQuery = true)
	Employee getEmployeeByEmail(String email);
	
	@Query(value = "select * from tbl_employees where name = :name",nativeQuery = true)
	Employee getEmployeeByName(String name);
	
}
