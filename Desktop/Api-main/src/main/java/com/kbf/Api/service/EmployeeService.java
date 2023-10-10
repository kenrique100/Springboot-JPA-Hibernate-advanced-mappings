package com.kbf.Api.service;

import java.util.List;

import com.kbf.Api.model.Employee;

public interface EmployeeService {

    Employee createEmployee(Employee emp);
    
    List<Employee> getAll();

    Employee getEmployeeById(int empId);
    
    Employee getEmployeeByName(String empName);

    Employee updateEmpById(int empId, Employee emp);

    String deleteEmpById(int empId);

}
