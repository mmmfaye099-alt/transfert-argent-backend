package com.exampleTransfertArgent.Tranfert.Controller;

import com.exampleTransfertArgent.Tranfert.Models.User;
import com.exampleTransfertArgent.Tranfert.Services.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class ProfileController {

    private final UserService userService;

    public ProfileController(UserService userService) {
        this.userService = userService;
    }

    // ✅ Endpoint pour récupérer le profil de l'utilisateur connecté
    @PostMapping("/login")
    public String login(@RequestBody User user) {
        User u = userService.login(user.getEmail(), user.getPassword()); // ✅ pas findByEmail
        if (u != null) {
            return "Connexion réussie ✅ Bienvenue " + u.getEmail();
        } else {
            return "Échec de connexion ❌";
        }
    }
}
