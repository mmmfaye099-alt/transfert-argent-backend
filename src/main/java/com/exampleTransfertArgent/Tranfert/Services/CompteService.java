package com.exampleTransfertArgent.Tranfert.Services;

import com.exampleTransfertArgent.Tranfert.Models.Compte;
import com.exampleTransfertArgent.Tranfert.Models.User;
import com.exampleTransfertArgent.Tranfert.Repository.CompteRepository;
import com.exampleTransfertArgent.Tranfert.Repository.UserRepository;
import com.exampleTransfertArgent.Tranfert.dto.CompteRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompteService {

    private final CompteRepository compteRepository;
    private final UserRepository userRepository;

    public CompteService(CompteRepository compteRepository, UserRepository userRepository) {
        this.compteRepository = compteRepository;
        this.userRepository = userRepository;
    }

    // Nouvelle méthode create qui prend CompteRequest (avec userId)
    public Compte creerCompte(CompteRequest request) {
        if (request == null) {
            throw new IllegalArgumentException("Request est null");
        }
        if (request.getUserId() == null) {
            throw new IllegalArgumentException("userId est requis");
        }

        // Récupérer l'utilisateur
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new RuntimeException("Utilisateur introuvable id=" + request.getUserId()));

        // Construire le Compte
        Compte compte = new Compte();
        compte.setNumero(request.getNumero() != null ? request.getNumero() : generateNumero());
        compte.setSolde(request.getSolde() != null ? request.getSolde() : 0.0);
        compte.setUser(user);

        // Sauvegarder
        return compteRepository.save(compte);
    }

    // (existant) Dépôt
    public Compte depot(Long id, Double montant) {
        Compte compte = compteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Compte introuvable"));
        compte.setSolde(compte.getSolde() + montant);
        return compteRepository.save(compte);
    }

    // (existant) Retrait
    public Compte retrait(Long id, Double montant) {
        Compte compte = compteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Compte introuvable"));
        if (compte.getSolde() < montant) {
            throw new RuntimeException("Solde insuffisant ❌");
        }
        compte.setSolde(compte.getSolde() - montant);
        return compteRepository.save(compte);
    }

    public Compte voirCompte(Long id) {
        return compteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Compte introuvable"));
    }

    public List<Compte> comptesUtilisateur(Long userId) {
        return compteRepository.findByUserId(userId);
    }

    public List<Compte> getAllComptes() {
        return compteRepository.findAll();
    }

    // Utilitaire simple : générer numéro si absent
    private String generateNumero() {
        long next = compteRepository.count() + 1;
        return "CPT" + String.format("%03d", next);
    }
}
