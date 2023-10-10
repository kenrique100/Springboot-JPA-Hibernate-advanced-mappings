package com.kbf.Api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.kbf.Api.model.Sales;

public interface IncomeRepository extends JpaRepository<Sales, Integer> {

	@Query("SELECT s FROM Sales s ORDER BY s.dateSold")
	List<Sales> findAllOrderByDate();

	@Query(value = "SELECT * FROM income i WHERE  i.date_sold > LAST_DAY(CURRENT_DATE - INTERVAL :range MONTH) ORDER BY i.date_sold", nativeQuery = true)
	List<Sales> findByMonthInterval(int range);

}
