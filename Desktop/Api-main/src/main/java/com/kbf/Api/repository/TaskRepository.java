package com.kbf.Api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kbf.Api.model.Task;




public interface TaskRepository extends JpaRepository<Task, Integer> {
	

}
