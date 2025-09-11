package com.exampleTransfertArgent.Tranfert.Models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double montant;

    private LocalDateTime date;


    @ManyToOne
    @JoinColumn(name = "compte_source_id")
    @JsonBackReference
    private Compte compteSource;

    @ManyToOne
    @JoinColumn(name = "compte_destination_id")
    @JsonBackReference
    private Compte compteDestination;


    // ✅ Constructeur par défaut (obligatoire pour JPA)
    public Transaction() {}

    // ✅ Constructeur simplifié
    public Transaction(Compte source, Compte destination, Double montant) {
        this.compteSource = source;
        this.compteDestination = destination;
        this.montant = montant;
        this.date = LocalDateTime.now(); // auto-remplissage de la date
    }

    // ✅ Getters et Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getMontant() {
        return montant;
    }

    public void setMontant(Double montant) {
        this.montant = montant;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public Compte getCompteSource() {
        return compteSource;
    }

    public void setCompteSource(Compte compteSource) {
        this.compteSource = compteSource;
    }

    public Compte getCompteDestination() {
        return compteDestination;
    }

    public void setCompteDestination(Compte compteDestination) {
        this.compteDestination = compteDestination;
    }
}
