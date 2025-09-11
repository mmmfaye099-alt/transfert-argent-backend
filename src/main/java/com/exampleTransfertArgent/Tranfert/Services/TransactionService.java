package com.exampleTransfertArgent.Tranfert.Services;

import com.exampleTransfertArgent.Tranfert.Models.Compte;
import com.exampleTransfertArgent.Tranfert.Models.Transaction;
import com.exampleTransfertArgent.Tranfert.Repository.CompteRepository;
import com.exampleTransfertArgent.Tranfert.Repository.TransactionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final CompteRepository compteRepository;

    public TransactionService(TransactionRepository transactionRepository, CompteRepository compteRepository) {
        this.transactionRepository = transactionRepository;
        this.compteRepository = compteRepository;
    }

    // ✅ Méthode utilitaire pour retrouver un compte
    private Compte getCompteOrThrow(Long compteId, String type) {
        return compteRepository.findById(compteId)
                .orElseThrow(() -> new RuntimeException("Compte " + type + " introuvable (id=" + compteId + ")"));
    }

    // ✅ Effectuer un transfert
    public Transaction transfert(Long sourceId, Long destinationId, Double montant) {
        Compte source = getCompteOrThrow(sourceId, "source");
        Compte destination = getCompteOrThrow(destinationId, "destination");

        if (source.getSolde() < montant) {
            throw new RuntimeException("Solde insuffisant ❌");
        }

        // Mise à jour des soldes
        source.setSolde(source.getSolde() - montant);
        destination.setSolde(destination.getSolde() + montant);

        compteRepository.save(source);
        compteRepository.save(destination);

        // ✅ Utilisation du constructeur simplifié
        Transaction transaction = new Transaction(source, destination, montant);

        return transactionRepository.save(transaction);
    }

    // ✅ Liste de toutes les transactions
    public List<Transaction> getTransactions() {
        return transactionRepository.findAll();
    }

    // ✅ Transactions d’un compte (source ou destination)
    public List<Transaction> getTransactionsByCompte(Long id) {
        return transactionRepository.findByCompteSourceIdOrCompteDestinationId(id, id);
    }
}
