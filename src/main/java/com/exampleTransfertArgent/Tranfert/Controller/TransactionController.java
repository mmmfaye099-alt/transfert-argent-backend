package com.exampleTransfertArgent.Tranfert.Controller;

import com.exampleTransfertArgent.Tranfert.Models.Transaction;
import com.exampleTransfertArgent.Tranfert.Services.TransactionService;
import com.exampleTransfertArgent.Tranfert.dto.TransactionRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transactions/transfert")
public class TransactionController {

    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    // ✅ Effectuer un transfert
    @PostMapping("/transfert")
    public Transaction transfert(@RequestBody TransactionRequest request) {
        return transactionService.transfert(
                request.getSourceId(),
                request.getDestinationId(),
                request.getMontant()
        );
    }

    // ✅ Liste de toutes les transactions
    @GetMapping
    public List<Transaction> getTransactions() {
        return transactionService.getTransactions();
    }

    // ✅ Liste des transactions d’un compte (source ou destination)
    @GetMapping("/compte/{id}")
    public List<Transaction> getTransactionsByCompte(@PathVariable Long id) {
        return transactionService.getTransactionsByCompte(id);
    }
}
