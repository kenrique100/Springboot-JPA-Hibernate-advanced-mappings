package com.kbf.Api.controller;

import static com.kbf.Api.config.SwaggerConfig.BEARER_KEY_SECURITY_SCHEME;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.kbf.Api.model.Expense;
import com.kbf.Api.model.Investment;
import com.kbf.Api.model.Sales;
import com.kbf.Api.model.dto.DashboardReportDTO;
import com.kbf.Api.service.TransactionService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class TransactionsController {

	@Autowired
	private TransactionService transService;

	@Operation(security = { @SecurityRequirement(name = BEARER_KEY_SECURITY_SCHEME) })
	@GetMapping("/kbf/api/1.0/transactions/{range}")
	public DashboardReportDTO getTransactions(@PathVariable("range") int range) {

		return transService.getDashBoardData(range);
	}

	@Operation(security = { @SecurityRequirement(name = BEARER_KEY_SECURITY_SCHEME) })
	@PostMapping("/kbf/api/1.0/transactions/expense")
	public Expense createExpense(@RequestBody Expense expense) {

		return transService.createExpense(expense);
	}

	@Operation(security = { @SecurityRequirement(name = BEARER_KEY_SECURITY_SCHEME) })
	@PostMapping("/kbf/api/1.0/transactions/sales")
	public Sales createSales(@RequestBody Sales sale) {

		return transService.createSales(sale);
	}

	@Operation(security = { @SecurityRequirement(name = BEARER_KEY_SECURITY_SCHEME) })
	@GetMapping("/kbf/api/1.0/transactions/invest")
	public List<Investment> getAll() {

		return transService.getAllInvestments();
	}

	@Operation(security = { @SecurityRequirement(name = BEARER_KEY_SECURITY_SCHEME) })
	@PostMapping("/kbf/api/1.0/transactions/invest")
	public Investment createInvestment(@RequestBody Investment invest) {

		return transService.createInvestment(invest);
	}

	@Operation(security = { @SecurityRequirement(name = BEARER_KEY_SECURITY_SCHEME) })
	@DeleteMapping("/kbf/api/1.0/transactions/invest/{id}")
	public String deleteInvestment(@PathVariable("id") int id) {

		return transService.deleteInvestmentById(id);
	}

	@Operation(security = { @SecurityRequirement(name = BEARER_KEY_SECURITY_SCHEME) })
	@DeleteMapping("/kbf/api/1.0/transactions/income/{id}")
	public String deleteIncome(@PathVariable("id") int id) {

		return transService.deleteSalesById(id);
	}

	@Operation(security = { @SecurityRequirement(name = BEARER_KEY_SECURITY_SCHEME) })
	@DeleteMapping("/kbf/api/1.0/transactions/expense/{id}")
	@CrossOrigin
	public String deleteExpense(@PathVariable("id") int id) {

		return transService.deleteExpenseById(id);
	}

}
