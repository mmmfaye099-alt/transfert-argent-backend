package com.exampleTransfertArgent.Tranfert.Controller;

import com.exampleTransfertArgent.Tranfert.Models.Transaction;
import com.exampleTransfertArgent.Tranfert.Services.TransactionService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping("/transfert")
    public Transaction transfert(
            @RequestParam Long sourceId,
            @RequestParam Long destinationId,
            @RequestParam Double montant) {
        return transactionService.transfert(sourceId, destinationId, montant);
    }

    @GetMapping
    public List<Transaction> getTransactions() {
        return transactionService.getTransactions();
    }

    @GetMapping("/compte/{id}")
    public List<Transaction> getTransactionsByCompte(@PathVariable Long id) {
        return transactionService.getTransactionsByCompte(id);
    }

}

