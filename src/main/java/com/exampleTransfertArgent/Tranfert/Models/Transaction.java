package com.exampleTransfertArgent.Tranfert.Models;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double montant;

    private LocalDateTime date = LocalDateTime.now();

    @ManyToOne
    @JoinColumn(name = "source_id")
    private Compte compteSource;

    @ManyToOne
    @JoinColumn(name = "destination_id")
    private Compte compteDestination;
}
