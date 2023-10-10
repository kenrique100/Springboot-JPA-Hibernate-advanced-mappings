package com.kbf.Api.service.impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import com.kbf.Api.service.TransactionService;
import com.kbf.Api.model.Expense;
import com.kbf.Api.model.Investment;
import com.kbf.Api.model.Sales;
import com.kbf.Api.model.dto.DashboardReportDTO;
import com.kbf.Api.repository.ExpenseRepository;
import com.kbf.Api.repository.IncomeRepository;
import com.kbf.Api.repository.InvestmentRepository;

@Service
public class TransactionServiceImpl implements TransactionService {

	@Autowired
	private IncomeRepository incomeRepo;

	@Autowired
	private ExpenseRepository expenseRepo;

	@Autowired
	private InvestmentRepository investRepo;

	@Override
	public DashboardReportDTO getDashBoardData(int range) {

		List<Sales> incomes = incomeRepo.findByMonthInterval(range);

		List<String> incomeTypes = new ArrayList<>();
		List<Double> incomeAmounts = new ArrayList<>();
		incomes.forEach(income -> {

			incomeTypes.add(income.getType());
			incomeAmounts.add(income.getAmountSold());

		});
		Map<String, Double> transform = getChartLabelsAndData(incomeTypes, incomeAmounts);

		List<String> mergedIncomeTypes = transform.keySet().stream().toList();
		List<Double> mergedIncomeAmounts = transform.values().stream().toList();

		List<Expense> expenses = expenseRepo.findByMonthInterval(range);

		List<Double> expenseAmounts = new ArrayList<>();
		List<String> expenseTypes = new ArrayList<>();
		// Line Chart Data collections

		List<LocalDate> expenseDates = new ArrayList<>();

		expenses.forEach(expense -> {
			expenseAmounts.add(expense.getExpenseAmount());
			expenseTypes.add(expense.getType());
			expenseDates.add(expense.getExpenseDate());

		});
		// doghnut data
		Map<String, Double> expChartData = getChartLabelsAndData(expenseTypes, expenseAmounts);
		List<String> mergedExpenseTypes = expChartData.keySet().stream().toList();
		List<Double> mergedExpenseAmounts = expChartData.values().stream().toList();

		List<Investment> seeds = investRepo.findAllOrderByDate();
		List<String> seedTypes = new ArrayList<>();
		List<Double> seedAmounts = new ArrayList<>();
		seeds.forEach(seed -> {
			seedTypes.add(seed.getType());
			seedAmounts.add(seed.getAmountDisbursed());
		});
		Map<String, Double> seedChartData = getChartLabelsAndData(seedTypes, seedAmounts);
		List<String> mergedInvestTypes = seedChartData.keySet().stream().toList();
		List<Double> mergedInvestAmounts = seedChartData.values().stream().toList();

		double totalExpenses = mergedExpenseAmounts.stream().mapToDouble(Double::doubleValue).sum();
		double totalIncomes = mergedIncomeAmounts.stream().mapToDouble(Double::doubleValue).sum();

		DashboardReportDTO transDTO = new DashboardReportDTO(incomes, expenses, seeds, mergedIncomeTypes,
				mergedIncomeAmounts, mergedExpenseTypes, mergedExpenseAmounts, mergedInvestTypes, mergedInvestAmounts,
				totalExpenses, totalIncomes, getChartTitleData(range));
		return transDTO;
	}

	/**
	 * get list of transaction types and amount and transform result into list with
	 * all duplicate merged
	 * 
	 * @param labels  : Expense or Income Types
	 * @param dataSet : Expense or Income Amounts
	 * @return
	 */
	private Map<String, Double> getChartLabelsAndData(List<String> labels, List<Double> dataSet) {
		Multimap<String, Double> result = ArrayListMultimap.create();
		for (int i = 0; i < labels.size(); i++) {
			// sort here

			// result.containsKey(labels.get(i));
			result.put(labels.get(i), dataSet.get(i));
		}
		Map<String, Collection<Double>> typeAmount = result.asMap();
		Map<String, Double> transform = new HashMap<>();
		typeAmount.forEach((k, v) -> {
			double newSize = 0;

			// System.out.println(("key is " + k + ": value is" + v));
			newSize = v.stream().mapToDouble(d -> d).sum();
			transform.put(k, newSize);

		});
		return transform;
	}

	private String getChartTitleData(int value) {
		String toReturn = null;
		switch (value) {
		case 1:
			toReturn = "This Month";
			break;
		case 2:
			toReturn = "Last 2 Months";
			break;
		case 3:
			toReturn = "Last 3 Months";
			break;
		case 6:
			toReturn = "Last 6 Months";
			break;
		case 12:
			toReturn = "Last 12 Months";
			break;
		case 24:
			toReturn = "Last 24 Months";
			break;
		default:
			toReturn = "Selected range not available";
		}
		return toReturn;

	}

	@Override
	public Expense createExpense(Expense expense) {

		return expenseRepo.save(expense);
	}

	@Override
	public Expense updateExpenseById(int expenseId, Expense expense) {
//		Optional<Expense> expenseToUpdate = expenseRepo.findById(expenseId);
//		if (expenseToUpdate.isPresent()) {
//			
//		}
		return null;
	}

	@Override
	public String deleteExpenseById(int expenseId) {
		System.out.println("Expense id is  : " + expenseId);
		if (expenseRepo.findById(expenseId).isPresent()) {
			expenseRepo.deleteById(expenseId);
			return "Record removed successfully";
		}
		return "No such Record in database";
	}

	@Override
	public Sales createSales(Sales sales) {
		// calculate due balance
		return incomeRepo.save(sales);
	}

	@Override
	public Sales updateSalesById(int incomeId, Sales sales) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String deleteSalesById(int incomeId) {
		if (incomeRepo.findById(incomeId).isPresent()) {
			incomeRepo.deleteById(incomeId);
			return "Record removed successfully";
		}
		return "No such Record in database";
	}

	@Override
	public Investment createInvestment(Investment invest) {

		return investRepo.save(invest);
	}

	@Override
	public Investment updateInvestmentById(int investmentId, Investment invest) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String deleteInvestmentById(int investmentId) {
		if (investRepo.findById(investmentId).isPresent()) {
			investRepo.deleteById(investmentId);
			return "Record removed successfully";
		}
		return "No such Record in database";
	}

	@Override
	public List<Investment> getAllInvestments() {

		return investRepo.findAll();
	}

}
