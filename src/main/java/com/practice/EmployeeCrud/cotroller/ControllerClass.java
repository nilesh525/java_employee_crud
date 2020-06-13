package com.practice.EmployeeCrud.cotroller;

import java.io.IOException;
import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

import javax.mail.MessagingException;
import javax.transaction.Transactional;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.practice.EmployeeCrud.Model.Employee;
import com.practice.EmployeeCrud.Model.MailSignature;
import com.practice.EmployeeCrud.ServiceImpl.EmployeeServiceImpl;
import com.practice.EmployeeCrud.ServiceImpl.PassGenerator;
import com.practice.EmployeeCrud.sendmail.EmailManagment;

@CrossOrigin(origins = {"*"},allowedHeaders = "*")
@RestController
public class ControllerClass {
	
	@Autowired
	public EmployeeServiceImpl employeeserv;
	
	@Autowired
	EmailManagment emailManagment;
	
	
	@PostMapping("/create")
	public ResponseEntity<Employee> createEmployee(@RequestBody Employee employee) throws Exception{
		Employee emp = new Employee();
		try {
			System.out.println(employee.toString());
			Employee checkEmp = null;
			if(employee.getEmail()!=null)
				 checkEmp = employeeserv.getEmployeeByEmail(employee.getEmail());
			if(checkEmp!=null) {
				if(employee.getPwd().equals(checkEmp.getPwd()))
					return ResponseEntity.ok(checkEmp);
				else
					return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
			}
			else {
				Employee persistEmp = new Employee();
				String tomailuser;
				if((employee.getSource().equals("gamil")) ) {
					emailManagment.authenticate(employee.getEmail(),employee.getPwd());
					System.out.println("inside gmail");
					tomailuser = employee.getEmail().replaceAll("@gmail.com", "");
					persistEmp.setName(tomailuser);
					persistEmp.setDesciption("NA(Please update)");
					persistEmp.setEmail(employee.getEmail());
					persistEmp.setPwd(employee.getPwd());
					persistEmp.setSource(employee.getSource());
					emp = employeeserv.createEmployee(persistEmp);
					String mailid=emp.getEmail();					
					emailManagment.sendmail(mailid);
				}else if((employee.getSource().equals("facebook")) ) {
					
					Employee checkEmpfb = employeeserv.getEmployeeByName(employee.getName());
					if(checkEmpfb!=null) {
						emp=checkEmpfb;
					}else {
					boolean flag=true;
					persistEmp.setName(employee.getName());
					persistEmp.setDesciption("NA(Please update)");
					if(employee.getEmail()==null) {
						persistEmp.setEmail("NA(Please update)");
						flag=false;
					}else
						persistEmp.setEmail(employee.getEmail());
					persistEmp.setPwd(employee.getPwd());
					persistEmp.setSource(employee.getSource());
					emp = employeeserv.createEmployee(persistEmp);
					String mailid=emp.getEmail();	
					if(flag)
						emailManagment.sendmail(mailid);
					}
				}else {
					persistEmp = employee;
					emp = employeeserv.createEmployee(persistEmp);
					String mailid=emp.getEmail();
					emailManagment.sendmail(mailid);
				}
			}
		} catch (MessagingException | IOException e) {
			System.out.println("Error "+e.getMessage());
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		return ResponseEntity.ok(emp);
	}
	
	@GetMapping("/getEmp")
	public List<Employee> getEmployee(){
		List<Employee> list = employeeserv.getAllEmployee();
		return list.stream().filter(emp->emp.isActive()).collect(Collectors.toList());
	}
	
	@PostMapping("/editEmp")
	public Employee updateEmployee(@RequestBody Employee emp) throws Exception {
		int id = emp.getId();
		Employee checkId = employeeserv.getEmployeeByID(id);
		Employee employee;
		Employee updateEmp=new Employee();
		updateEmp.setId(emp.getId());
		updateEmp.setName(emp.getName());
		updateEmp.setEmail(checkId.getEmail());
		updateEmp.setPwd(emp.getPwd());
		updateEmp.setDesciption(emp.getDesciption());
		updateEmp.setActive(checkId.isActive());
		updateEmp.setSource(checkId.getSource());
		if(checkId.getName()!=null)
			employee =employeeserv.updateEmployee(updateEmp);
		else
			throw new Exception("Not Found");
		return employee;
	}
	
	@GetMapping("getEmpById")
	public ResponseEntity<Employee> getOne(@RequestParam int id) {
		Employee emp = employeeserv.getEmployeeByID(id);
		if(emp.isActive())
			return ResponseEntity.ok(emp);
		else
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}
	@GetMapping("getEmpByEmail")
	public ResponseEntity<Employee> getOneByEmail(@RequestParam String email,@RequestParam String pass) {
		Employee emp = employeeserv.getEmployeeByEmail(email);
		if(emp!=null && emp.isActive() && emp.getPwd().equals(pass))
			return ResponseEntity.ok(emp);
		else
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}
	
	@DeleteMapping("/deleteEmp")
	@Transactional
	public ResponseEntity<String> deleteEmployee(@RequestParam int id) {
		employeeserv.deleteEmployee(id);
		return ResponseEntity.ok("DELETE_SUCCESS");
	}
	
	@PostMapping("/sendmail")
	public String mailSend(@RequestBody MailSignature mailsig) {
		try {
			Employee emp = employeeserv.getEmployeeByEmail(mailsig.getFromMail());
			emailManagment.sendmailcostom(mailsig,emp.getPwd());
		} catch (MessagingException | IOException e) {
			System.out.println(e.getMessage());
			return "Can not send message. Username and Password not accepted";
		}
		return "message sent";
	}
	
	@PostMapping(value = "/forgotpass/{mail}")
	public ResponseEntity<String> forget(@PathVariable String mail)  {

		
		PassGenerator passgen = new PassGenerator();
		String password = passgen.generatePassayPassword();
		try {
			emailManagment.sendmailforget(mail,password);
		} catch (MessagingException | IOException e) {
			e.printStackTrace();
			return ResponseEntity.ok("error occured.") ;
		}
		return  ResponseEntity.ok("Password has been sent to your mail.");
	}
	
//	@GetMapping("/user")
//	public Principal getUser(Principal user) {
//		System.out.println(user);
//        return user;
//    }
}
