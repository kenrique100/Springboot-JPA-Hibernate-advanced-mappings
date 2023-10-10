package com.kbf.Api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kbf.Api.model.Pond;




public interface PondRepository extends JpaRepository<Pond, Integer> {
	Pond findByPondName(String pondName);

}
