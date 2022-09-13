package com.obider.expensetrackerapi.user.repository;

import com.obider.expensetrackerapi.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Integer> {
    @Query("SELECT u FROM User u WHERE u.email = ?1 AND u.password = ?2")
    Optional<User> findByEmailAndPassword(String email, String password);

    @Query("SELECT u FROM User u WHERE u.email = ?1")
    Optional<User> findByEmail(String email);

    @Query("SELECT COUNT(u) FROM User u WHERE u.email = ?1")
    Integer getCountByEmail(String email);
}
