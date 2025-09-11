package com.exampleTransfertArgent.Tranfert.Models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Compte {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String numero; // ex: "CPT001"
    private Double solde = 0.0;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user; // un compte appartient à un utilisateur

    @OneToMany(mappedBy = "compteSource", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Transaction> transactionsSource;

    @OneToMany(mappedBy = "compteDestination", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Transaction> transactionsDestination;



}
