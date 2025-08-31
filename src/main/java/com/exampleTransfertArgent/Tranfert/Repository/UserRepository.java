package com.exampleTransfertArgent.Tranfert.Repository;


import com.exampleTransfertArgent.Tranfert.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
}
