package com.exampleTransfertArgent.Tranfert.Controller;

import com.exampleTransfertArgent.Tranfert.Models.Compte;
import com.exampleTransfertArgent.Tranfert.Services.CompteService;
import com.exampleTransfertArgent.Tranfert.dto.CompteRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comptes")
public class CompteController {

    private final CompteService compteService;

    public CompteController(CompteService compteService) {
        this.compteService = compteService;
    }

    // CREATE : attend CompteRequest { numero, solde, userId }
    @PostMapping(value = "/create", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Compte> creerCompte(@RequestBody CompteRequest request) {
        Compte created = compteService.creerCompte(request);
        return ResponseEntity.ok(created);
    }

    // --- autres endpoints existants (dépot / retrait / get / getByUser / getAll) ...
    @PostMapping("/{id}/depot")
    public Compte depot(@PathVariable Long id, @RequestParam Double montant) {
        return compteService.depot(id, montant);
    }

    @PostMapping("/{id}/retrait")
    public Compte retrait(@PathVariable Long id, @RequestParam Double montant) {
        return compteService.retrait(id, montant);
    }

    @GetMapping("/{id}")
    public Compte voirCompte(@PathVariable Long id) {
        return compteService.voirCompte(id);
    }

    @GetMapping("/user/{userId}")
    public List<Compte> comptesUtilisateur(@PathVariable Long userId) {
        return compteService.comptesUtilisateur(userId);
    }

    @GetMapping
    public List<Compte> getAllComptes() {
        return compteService.getAllComptes();
    }
}
