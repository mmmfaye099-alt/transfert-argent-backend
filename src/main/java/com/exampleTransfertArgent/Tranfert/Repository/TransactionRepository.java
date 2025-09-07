package com.exampleTransfertArgent.Tranfert.Repository;

import com.exampleTransfertArgent.Tranfert.Models.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> findByCompteSourceIdOrCompteDestinationId(Long sourceId, Long destinationId);
}
