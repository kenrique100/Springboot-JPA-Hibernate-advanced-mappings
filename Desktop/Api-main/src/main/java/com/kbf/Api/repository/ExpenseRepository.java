package com.kbf.Api.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.kbf.Api.model.Expense;

public interface ExpenseRepository extends JpaRepository<Expense, Integer> {

	@Query("SELECT t FROM Expense t WHERE t.expenseDate BETWEEN :firstDay AND :lastDay")
	List<Expense> findAllByMonth(LocalDate firstDay, LocalDate lastDay);

	@Query("SELECT t FROM Expense t ORDER BY t.expenseDate")
	List<Expense> findAllOrderByDate();

	@Query(value = "SELECT * FROM expense t WHERE  t.expense_date > LAST_DAY(CURRENT_DATE - INTERVAL :range MONTH) ORDER BY t.expense_date", nativeQuery = true)
	List<Expense> findByMonthInterval(int range);
}
