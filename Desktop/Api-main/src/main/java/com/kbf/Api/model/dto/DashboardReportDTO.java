package com.kbf.Api.model.dto;


import java.util.ArrayList;
import java.util.List;

import com.kbf.Api.model.Expense;
import com.kbf.Api.model.Investment;
import com.kbf.Api.model.Sales;

import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class DashboardReportDTO {
	
	
	private double expenseAmount;
	private double incomeAmount;
    private List<Sales> incomes = new ArrayList<>();
	private List<Expense> expenses = new ArrayList<>();
	private List<Investment> invest = new ArrayList<>();
	
	private List<String> incomeCategories = new ArrayList<>();
	private List<Double> incomeAmounts = new ArrayList<>();
	private List<String> expensesCategories = new ArrayList<>();
	private List<Double> expensesAmount = new ArrayList<>();
	private List<String> investCategories = new ArrayList<>();
	private List<Double> investAmounts = new ArrayList<>();
	private String chartTitle;
	
	

	
	public DashboardReportDTO(List<Sales> incomes, List<Expense> expenses, List<Investment> seeds,
			List<String> incomeTypes, List<Double> incomeAmounts,
			List<String> expensesTypes,List<Double> expensesAmount, List<String> invesTypes,List<Double> investAmounts,
			double totalExpenses, double totalIncome, String chartTitle) {
		
		
		this.setIncomes(incomes);
		this.setInvest(seeds);
		this.setExpenses(expenses);
		this.setIncomeAmounts(incomeAmounts);
		this.setIncomeCategories(incomeTypes);
		this.setExpensesCategories(expensesTypes);
		this.setExpensesAmount(expensesAmount);
		this.setInvestCategories(invesTypes);
		this.setInvestAmounts(investAmounts);
		this.setExpenseAmount(totalExpenses);
		this.setIncomeAmount(totalIncome);
		this.setChartTitle(chartTitle);
		
		
	}
	  
	public DashboardReportDTO(List<String> incomeTypes, List<Double> incomeAmounts,
			List<String> expensesTypes,List<Double> expensesAmount, double totalExpenses, double totalIncome) {
		
		this.setIncomeAmounts(incomeAmounts);
		this.setIncomeCategories(incomeTypes);
		this.setExpensesCategories(expensesTypes);
		this.setExpensesAmount(expensesAmount);
		this.setExpenseAmount(totalExpenses);
		this.setIncomeAmount(totalIncome);
		
	}
	 
	
	
}
