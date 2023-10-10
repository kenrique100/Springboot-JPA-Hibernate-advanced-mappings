package com.kbf.Api.service;

import java.util.List;

import com.kbf.Api.model.Expense;
import com.kbf.Api.model.Investment;
import com.kbf.Api.model.Sales;
import com.kbf.Api.model.dto.DashboardReportDTO;

public interface TransactionService {

	DashboardReportDTO getDashBoardData(int range);

	Expense createExpense(Expense expense);

	Expense updateExpenseById(int expenseId, Expense expense);

	String deleteExpenseById(int expenseId);

	Sales createSales(Sales sales);

	Sales updateSalesById(int incomeId, Sales sales);

	String deleteSalesById(int incomeId);

	Investment createInvestment(Investment invest);

	Investment updateInvestmentById(int investmentId, Investment invest);

	String deleteInvestmentById(int investmentId);
	
	List<Investment> getAllInvestments();
}
