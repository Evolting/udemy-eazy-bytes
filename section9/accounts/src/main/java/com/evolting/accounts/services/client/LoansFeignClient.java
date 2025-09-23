package com.evolting.accounts.services.client;

import com.evolting.accounts.dtos.CardsDto;
import com.evolting.accounts.dtos.LoansDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "loans")
public interface LoansFeignClient {
    @GetMapping("/api/fetch")
    public ResponseEntity<LoansDto> fetchLoanDetails(@RequestParam String mobileNumber);
}
