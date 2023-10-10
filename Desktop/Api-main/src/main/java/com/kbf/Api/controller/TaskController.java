package com.kbf.Api.controller;

import static com.kbf.Api.config.SwaggerConfig.BEARER_KEY_SECURITY_SCHEME;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.kbf.Api.exception.EntityNotFoundException;
import com.kbf.Api.model.Task;
import com.kbf.Api.service.TaskService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class TaskController {

	@Autowired
	private TaskService taskService;
	
	@Operation(security = { @SecurityRequirement(name = BEARER_KEY_SECURITY_SCHEME) })
	@GetMapping("/kbf/api/1.0/task") 
	public List<Task> getAll() {
		
		return taskService.findAll();
	}
	@Operation(security = {@SecurityRequirement(name = BEARER_KEY_SECURITY_SCHEME)})
	@PutMapping("/kbf/api/1.0/task/{id}") 
	public Task updateTask(@PathVariable("id") int id, @RequestBody Task task) throws EntityNotFoundException {

		return taskService.updateTaskById(id, task);
	}
	@Operation(security = {@SecurityRequirement(name = BEARER_KEY_SECURITY_SCHEME)})
	@PostMapping("/kbf/api/1.0/task") 
	public Task createTask(@RequestBody Task task) throws EntityNotFoundException {

		return taskService.createTask(task);
	}
	@Operation(security = {@SecurityRequirement(name = BEARER_KEY_SECURITY_SCHEME)})
	@DeleteMapping ("/kbf/api/1.0/task/{id}") 
	public String deleteSample(@PathVariable("id") int id) {

		return taskService.deleteTaskById(id);
	}
	
}
