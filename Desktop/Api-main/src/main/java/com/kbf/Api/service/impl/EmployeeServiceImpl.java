package com.kbf.Api.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kbf.Api.service.EmployeeService;
import com.kbf.Api.model.Employee;
import com.kbf.Api.repository.EmployeeRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService {
	
	@Autowired
	EmployeeRepository empRepo;

	@Override
	public Employee createEmployee(Employee emp) {
		
		return empRepo.save(emp);
	}

	@Override
	public List<Employee> getAll() {
		
		return empRepo.findAll();
	}

	@Override
	public Employee getEmployeeById(int empId) {
		Optional<Employee> emp = empRepo.findById(empId);
		return emp.orElseThrow();
	}

	@Override
	public Employee getEmployeeByName(String empName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Employee updateEmpById(int empId, Employee emp) {
		
		return null;
	}
	@Override
	public String deleteEmpById(int empId) {
		if(empRepo.findById(empId).isPresent()){
			empRepo.deleteById(empId);
			return "Employee removed successfully";
		}
		return "No such Employee in database";
	}
	

}
