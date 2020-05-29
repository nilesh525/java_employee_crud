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
	
	@Column(name="email",nullable = false)
	private String email;
	
	@Column(name="pass",nullable = false)
	private String pwd;
	
	@Column(name="is_active",columnDefinition = "boolean default true")
	private boolean isActive=true;
	
	@Column(name="source",nullable = false)
	private String source;

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public int getId() {
		return id;
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
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}
	

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "Employee [id=" + id + ", name=" + name + ", desciption=" + desciption + ", email=" + email + ", pwd="
				+ pwd + ", isActive=" + isActive + ", source=" + source + "]";
	}
	
}
