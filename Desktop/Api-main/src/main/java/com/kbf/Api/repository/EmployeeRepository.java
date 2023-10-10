package com.kbf.Api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kbf.Api.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
	Employee findBylname(String lname);
}
