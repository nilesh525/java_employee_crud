package com.practice.EmployeeCrud.Dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.practice.EmployeeCrud.Model.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer>{

	@Modifying
	@Query(value="update tbl_employees e set e.is_active = false where e.id = :id", nativeQuery = true)
	void setActive(int id);

	@Query(value = "select * from tbl_employees e where e.email = :email",nativeQuery = true)
	Employee getEmployeeByEmail(String email);
	
}
