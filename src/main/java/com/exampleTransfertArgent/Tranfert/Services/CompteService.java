package com.exampleTransfertArgent.Tranfert.Services;

import com.exampleTransfertArgent.Tranfert.Models.Compte;
import com.exampleTransfertArgent.Tranfert.Repository.CompteRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompteService {

    private final CompteRepository compteRepository;

    public CompteService(CompteRepository compteRepository) {
        this.compteRepository = compteRepository;
    }

    // Créer un compte
    public Compte creerCompte(Compte compte) {
        return compteRepository.save(compte);
    }

    // Dépôt d’argent
    public Compte depot(Long id, Double montant) {
        Compte compte = compteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Compte introuvable"));
        compte.setSolde(compte.getSolde() + montant);
        return compteRepository.save(compte);
    }

    // Retrait d’argent
    public Compte retrait(Long id, Double montant) {
        Compte compte = compteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Compte introuvable"));
        if (compte.getSolde() < montant) {
            throw new RuntimeException("Solde insuffisant ❌");
        }
        compte.setSolde(compte.getSolde() - montant);
        return compteRepository.save(compte);
    }

    // Voir solde
    public Compte voirCompte(Long id) {
        return compteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Compte introuvable"));
    }

    // Liste des comptes d’un utilisateur
    public List<Compte> comptesUtilisateur(Long userId) {
        return compteRepository.findByUserId(userId);
    }

    public List<Compte> getAllComptes() {
        return compteRepository.findAll();
    }
}
