package com.n26.challenge.controller;


import com.n26.challenge.request.Transaction;
import com.n26.challenge.response.Statistic;
import com.n26.challenge.service.StatisitcServiceImpl;
import com.n26.challenge.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@RestController
public class RequestController {

    @Autowired
    TransactionService transactionService;

    @Autowired
    StatisitcServiceImpl statisitcService;

    @PostMapping("/transactions")
    public void addTransaction(@RequestBody Transaction transaction, HttpServletResponse httpServletResponse){
        boolean isSixtySecondOldTxn = transactionService.addTransaction(transaction);
        httpServletResponse.setStatus(HttpServletResponse.SC_OK);
        if(isSixtySecondOldTxn)
            httpServletResponse.setStatus(HttpServletResponse.SC_NO_CONTENT);

    }

    @GetMapping("/statistics")
    public Statistic getStatistics(){
        return statisitcService.getStatistics();
    }
}
