package com.exampleTransfertArgent.Tranfert.Controller;

import com.exampleTransfertArgent.Tranfert.Models.Compte;
import com.exampleTransfertArgent.Tranfert.Services.CompteService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comptes")
public class CompteController {

    private final CompteService compteService;

    public CompteController(CompteService compteService) {
        this.compteService = compteService;
    }

    @PostMapping("/create")
    public Compte creerCompte(@RequestBody Compte compte) {
        return compteService.creerCompte(compte);
    }

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

