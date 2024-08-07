package com.projects.personal.forum_hub.repository;

import com.projects.personal.forum_hub.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long>{
    boolean existsByEmail(String email);
    boolean existsByUsername(String username);
    boolean existsByEmailAndPassword(String email, String password);
    @Query(" SELECT s FROM User s WHERE s.email = :email")
    Optional<User> selectUserByEmail(String email);

    UserDetails findByEmail(String username);
}
