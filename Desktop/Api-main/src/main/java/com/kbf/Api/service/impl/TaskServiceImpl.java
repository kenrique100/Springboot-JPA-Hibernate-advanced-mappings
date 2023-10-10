package com.kbf.Api.service.impl;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kbf.Api.service.TaskService;
import com.kbf.Api.model.Employee;
import com.kbf.Api.model.Task;
import com.kbf.Api.repository.EmployeeRepository;
import com.kbf.Api.repository.TaskRepository;

@Service
public class TaskServiceImpl implements TaskService {
	
	@Autowired
	private TaskRepository taskRepo;
	
	@Autowired
	private EmployeeRepository empRepository;

	@Override
	public Task createTask(int empId, Task task) {
		//need dropd own to  use
		Optional<Employee> emp = empRepository.findById(empId);
    	if (emp.isPresent()) {
			Employee empPresent = emp.get();
			task.setEmp(empPresent);
			task.setAssignedTo(empPresent.getLname());
		    return taskRepo.save(task);
    	}
		
		return null;
	}
	@Override
	public List<Task> findAll() {
		
		return taskRepo.findAll();
	}

	@Override
	public List<Task> getTaskByEmployeeId(int empId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Task updateTaskById(int taskId, Task task) {
		Optional<Task> taskToUpdate = taskRepo.findById(taskId);
		if (taskToUpdate.isPresent()) {
			Task originalTask = taskToUpdate.get();
            
			if (Objects.nonNull(task.getEnteredTitle()) && !"".equalsIgnoreCase(task.getEnteredTitle())) {
				originalTask.setEnteredTitle(task.getEnteredTitle());
			}
			if (Objects.nonNull(task.getEnteredDate())) {
				originalTask.setEnteredDate(task.getEnteredDate());
			}
			if (Objects.nonNull(task.getEnteredDescription()) && !"".equalsIgnoreCase(task.getEnteredDescription())) {
				originalTask.setEnteredDescription(task.getEnteredDescription());
			}
			
			if (Objects.nonNull(task.isCompleted()) ) {
				originalTask.setCompleted(task.isCompleted());
				
			}
			if (Objects.nonNull(task.isPriority()))  {
				originalTask.setPriority(task.isPriority());
				
			}
			if (Objects.nonNull(task.getAssignedTo()) && !"".equalsIgnoreCase(task.getAssignedTo())) {
				originalTask.setAssignedTo(task.getAssignedTo());
			}
			return taskRepo.save(originalTask);
		}
		return null;
	}

	@Override
	public String deleteTaskById(int taskId) {
		
		if(taskRepo.findById(taskId).isPresent()){
			taskRepo.deleteById(taskId);
			return "Task record removed successfully";
		}
		return "No such Task Record in database";
	}

	@Override
	public Task changeAssigneeById(String empLastName, int taskId) {
		Optional<Employee> emp = Optional.of(empRepository.findBylname(empLastName));
		if (emp.isPresent()) {
			Employee empPresent = emp.get();
			Optional<Task> taskToUpdate = taskRepo.findById(taskId);
			if (taskToUpdate.isPresent()) {
				Task originalTask = taskToUpdate.get();
				originalTask.setEmp(empPresent);
				originalTask.setAssignedTo(empPresent.getLname());
				return taskRepo.save(originalTask);
			}
		}

		return null;
	}
	@Override
	public Task createTask(Task task) {
		return taskRepo.save(task);
	}
}
