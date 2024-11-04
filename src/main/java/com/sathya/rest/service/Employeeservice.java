package com.sathya.rest.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.hibernate.annotations.Cache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sathya.rest.entity.Employeeentity;
import com.sathya.rest.model.Employeemodel;
import com.sathya.rest.repository.Employeerepository;

import jakarta.persistence.Cacheable;

@Service
public class Employeeservice {
	
	@Autowired
	Employeerepository employeerepository;
	public Employeeentity convertemployee(Employeemodel model) {
		// TODO Auto-generated method stub
	
	
//EmployeeEntity employeeEntity = new EmployeeEntity();
        
double hra =  model.getBasicSalary() * 0.20; // 20% of basic salary
double da =  model.getBasicSalary() * 0.10;  // 10% of basic salary
double pf =  model.getBasicSalary() * 0.12;  // 12% of basic salary
double totalSalary =  model.getBasicSalary() + hra + da - pf;

// Create EmployeeEntity and set the calculated values
Employeeentity employeeEntity = new Employeeentity();


employeeEntity.setEmpName( model.getEmpName());
employeeEntity.setEmpDept( model.getEmpDept());
employeeEntity.setBasicSalary( model.getBasicSalary());
employeeEntity.setEmail( model.getEmpEmail());
employeeEntity.setEmpMobile( model.getEmpMobile());
employeeEntity.setHra(hra);
employeeEntity.setDa(da);
employeeEntity.setPf(pf);
employeeEntity.setTotalSalary(totalSalary);

employeeEntity.setJoiningDate(LocalDate.now());
return employeerepository.save(employeeEntity);
    
		
	}
	public Employeeentity saveEmployee(Employeeentity employee) {
		return employeerepository.save(employee);	
		
	}

	public List<Employeeentity> saveAllEmployees(List<Employeeentity> employees) {
		return employeerepository.saveAll(employees);
		
	}
	public List<Employeeentity> getAllEmployees() {
		List<Employeeentity> emps=employeerepository.findAll();
		return emps;
	}
	public Optional<Employeeentity> getById(Long id){
		Optional<Employeeentity> optionalemps=employeerepository.findById(id);
		return optionalemps;
	}
	public boolean deleteEmployeeById(Long id) {
		Optional<Employeeentity> optionalEmp = employeerepository.findById(id);
		if(optionalEmp.isPresent())
		{ employeerepository.deleteById(id);
		return true;
		}
		else {
			return false;
		}			
	}
		public boolean deleteEmployeeByEmail(String email) {
			boolean status = employeerepository.existsByEmail(email);
			if(status)
			{ employeerepository.deleteByEmail(email);
			return true;
			}
			else {
				return false;
	}
}
		public Optional<Employeeentity> updateEmployeeById(Long id, Employeeentity newEmployee) {
			Optional<Employeeentity> optionalEmp = employeerepository.findById(id);
			if(optionalEmp.isPresent()) {
				Employeeentity existingEmployee = optionalEmp.get();
				existingEmployee.setEmpName(newEmployee.getEmpName());
				existingEmployee.setBasicSalary(newEmployee.getBasicSalary());
				existingEmployee.setEmpDept(newEmployee.getEmpDept());
				existingEmployee.setEmail(newEmployee.getEmail());
				existingEmployee.setEmpMobile(newEmployee.getEmpMobile());
				Employeeentity updatedEmployee = employeerepository.save(existingEmployee);
				return Optional.of(updatedEmployee);
			}
			else
			{
				return Optional.empty();
			}
 }
		public Optional<Employeeentity> updatePartialById(Long id, Map<String, Object> updates) {
			
			 Optional<Employeeentity> optionalEmp= employeerepository.findById(id);

			if(optionalEmp.isPresent()) 
			{ Employeeentity existingEmployee = optionalEmp.get();
			updates.forEach((key, value)-> {
			switch (key) {
			case "name":
			existingEmployee.setEmpName((String) value);
			break;
			case "salary":
			existingEmployee.setBasicSalary((Double)value);
			break;
			}
			});
            Employeeentity updatedEmp = employeerepository.save(existingEmployee);
            return Optional.of (updatedEmp);
            }
            else
            	return Optional.empty();
            }
		@org.springframework.cache.annotation.Cacheable("names")
		public List<String> getNames() {
			System.out.println("fecth the names");
			return List.of("Chikke","Lakshmi","hema");
			
		}
	}
			

