package com.exampleTransfertArgent.Tranfert.Services;

import com.exampleTransfertArgent.Tranfert.Models.User;
import com.exampleTransfertArgent.Tranfert.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // Inscription
    public User register(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword())); // mot de passe crypté
        user.setRole("USER"); // par défaut
        return userRepo.save(user);
    }

    // Connexion
    public User login(String email, String password) {
        User u = userRepo.findByEmail(email); // ✅ seulement l’email
        if (u != null && passwordEncoder.matches(password, u.getPassword())) {
            return u; // mot de passe correct
        }
        return null; // sinon échec
    }

    public List<User> getAllUsers() {
        return userRepo.findAll();
    }

}

