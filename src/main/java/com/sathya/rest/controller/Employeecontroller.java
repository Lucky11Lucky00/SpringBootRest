package com.sathya.rest.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import com.sathya.rest.entity.Employeeentity;
import com.sathya.rest.model.Employeemodel;
import com.sathya.rest.service.Employeeservice;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/v1")
public class Employeecontroller {
@Autowired
Employeeservice employeeservice;

@PostMapping("/saveemp")
public ResponseEntity<Employeeentity> saveEmployee(@RequestBody Employeeentity employee){
	Employeeentity saveEmp = employeeservice.saveEmployee(employee);
	return ResponseEntity.status(HttpStatus.CREATED)
			.header("employee status", "employee saved successfully...")
			.body(saveEmp);
}

@PostMapping("/saveall")
public ResponseEntity<List<Employeeentity>> saveAllEmployees(@RequestBody List<Employeeentity> employees){
	List<Employeeentity> emps = employeeservice.saveAllEmployees(employees);
	return ResponseEntity.status(HttpStatus.CREATED)
			.header("success", "employees saved successfully...")
			.body(emps);
}
@GetMapping("/getall")
public  ResponseEntity <List<Employeeentity>> getAllEmployees(){

List<Employeeentity> emps = employeeservice.getAllEmployees();
  return  ResponseEntity.status(HttpStatus.OK) 
		.header("status","data reading is successfully")
		.body(emps);
}
@GetMapping("/getbyid/{id}")
public ResponseEntity<?> getById(@PathVariable Long id)
{
	Optional<Employeeentity> optionalEmp = employeeservice.getById(id);
if(optionalEmp.isPresent())
{
return ResponseEntity.status(HttpStatus.OK)
		.body(optionalEmp.get());
}
else
{ return ResponseEntity.status(HttpStatus.NOT_FOUND)
		.body("Emp is not found withId.."+id);
}
}
@DeleteMapping("/delete/{id}")
public ResponseEntity<?> deleteEmployeeById(@PathVariable Long id)
{boolean status = employeeservice.deleteEmployeeById(id);
if(status)
{return ResponseEntity.noContent().build();
}
else {
	return  ResponseEntity.status(HttpStatus.NOT_FOUND)
			.header("status", "data is not found")
			.body("data is not found with id.."+id);
}

}
@DeleteMapping("/delete/{email}")
public ResponseEntity<?> deleteEmployeeByEmail(@PathVariable String email)
{boolean status = employeeservice.deleteEmployeeByEmail(email);
if(status)
{return ResponseEntity.noContent().build();
}
else {
	return  ResponseEntity.status(HttpStatus.NOT_FOUND)
			.header("status", "data is not found")
			.body("data is not found with email.."+email);
}
}
@PutMapping("update/{id}")
public ResponseEntity<?> updateEmployeeById(@PathVariable Long id, @RequestBody Employeeentity newEmployee) {
    Optional<Employeeentity> updateEmployee = employeeservice.updateEmployeeById(id, newEmployee);
    if (updateEmployee.isPresent()) {
    	return ResponseEntity.status(HttpStatus.OK)
    			              .body(updateEmployee);
     }
    else
    {
    	return ResponseEntity.status(HttpStatus.NOT_FOUND)
    			              .body("Employee with ID: "+ id + "not found");
    }
    
   
}
@PatchMapping("update/{id}")
public ResponseEntity<?>updatePartialById (@PathVariable Long id, @RequestBody Map<String,Object>updates)
{Optional<Employeeentity> updatedemployee=employeeservice.updatePartialById(id, updates);
if(updatedemployee.isPresent())
{
	return  ResponseEntity.status(HttpStatus.OK)
                          .body(updatedemployee.get());
   }
     else
   {
     return  ResponseEntity.status(HttpStatus.NOT_FOUND)
                      .body("data is not found.."+id);
   }
 }
@GetMapping("/getnames")
public List<String>getnames()
{
    return employeeservice.getNames();
}

}




	
