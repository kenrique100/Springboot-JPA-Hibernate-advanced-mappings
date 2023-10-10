package com.kbf.Api.model;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "employee")
public class Employee {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int empId;
	
	private String fname;
	private String lname;
	private String bagdeID;
	private int age;
	private String email;
	private String telNumber;
	private LocalDate dateHired;
	private double salary;
	private String jobTitle;
	@Lob
	@Column
	private String jobDescription;	
	
	
	
	//@JsonIgnore
	  @OneToMany(mappedBy = "emp")
	  private List<Payroll> pay;
	  
	//@JsonIgnore
	  @OneToMany(mappedBy = "emp")
	  private List<Task> task;

	
	
	
	
	
	
	

}
