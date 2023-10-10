package com.kbf.Api.model;


import java.time.LocalDate;



import com.fasterxml.jackson.annotation.JsonIgnore;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "task")
public class Task {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int taskId;
	
	private String enteredTitle;
	@Lob
	@Column
	private String enteredDescription;
	private LocalDate enteredDate;
	private boolean isPriority;
	private boolean isCompleted;
	private String assignedTo;
	
	
	@ManyToOne
	  @JsonIgnore
	  @JoinColumn(name = "empId")
	  private Employee emp;
	
	
	
	
}
