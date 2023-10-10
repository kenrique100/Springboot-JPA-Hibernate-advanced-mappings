package com.kbf.Api.service;

import java.util.List;

import com.kbf.Api.model.Task;

public interface TaskService {
	
	Task createTask(int empId,Task task);
	
	Task createTask(Task task);
	
	List<Task> findAll();

	List<Task> getTaskByEmployeeId(int empId);
	
	Task updateTaskById(int taskId, Task task);
	
	String deleteTaskById(int taskId);
	
	Task changeAssigneeById(String empLastName,int taskId);

}