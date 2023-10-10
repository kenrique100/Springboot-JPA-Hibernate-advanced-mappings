package com.kbf.Api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kbf.Api.model.Payroll;




public interface PayrollRepository extends JpaRepository<Payroll, Integer> {

}
