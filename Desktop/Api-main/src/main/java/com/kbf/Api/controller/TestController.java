package com.kbf.Api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kbf.Api.model.dto.DashboardReportDTO;
import com.kbf.Api.service.TransactionService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/test")
public class TestController {
	
	@Autowired
	private TransactionService transService;
	
  @GetMapping("/all")
  public String allAccess() {
    return "Public Content.";
  }

  @GetMapping("/user")
  @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
  public  DashboardReportDTO userAccess( ) {
	//  System.out.println(" Maybe this is it : " + token); 
	  return transService.getDashBoardData(1);
  }
  
  @GetMapping("/kbf/api/1.0/transactions/dashboard/{range}")
  @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
  public DashboardReportDTO getTransactions(@RequestHeader (name="Authorization") String token, @PathVariable("range") int range) {
      System.out.println(" Maybe this is it : " + token);
		return transService.getDashBoardData(range);
	}

  @GetMapping("/mod")
  @PreAuthorize("hasRole('MODERATOR')")
  public String moderatorAccess() {
    return "Moderator Board.";
  }

  @GetMapping("/admin")
  @PreAuthorize("hasRole('ADMIN')")
  public String adminAccess() {
    return "Admin Board.";
  }
}
