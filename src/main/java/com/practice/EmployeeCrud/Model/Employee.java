package com.practice.EmployeeCrud.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="TBL_EMPLOYEES")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Employee {
	
	@Id
	@GeneratedValue
	@Column(name="id",updatable = false, nullable = false)
	private int id;
	
	@Column(name="name",nullable = false)
	private String name;
	
	@Column(name="desciption",nullable = true)
	private String desciption;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDesciption() {
		return desciption;
	}

	public void setDesciption(String desciption) {
		this.desciption = desciption;
	}

	@Override
	public String toString() {
		return "Employee [id=" + id + ", name=" + name + ", desciption=" + desciption + "]";
	}
	
	
	
}
