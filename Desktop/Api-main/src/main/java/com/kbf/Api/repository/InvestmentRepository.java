package com.kbf.Api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.kbf.Api.model.Investment;




public interface InvestmentRepository extends JpaRepository<Investment, Integer> {
	
	@Query("SELECT i FROM Investment i ORDER BY i.date")
	List<Investment> findAllOrderByDate();

}
