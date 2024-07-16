package com.projects.personal.forum_hub.repository;

import com.projects.personal.forum_hub.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long>{
    boolean existsByEmail(String email);
    boolean existsByUsername(String username);
    @Query(" SELECT s FROM User s WHERE s.email = :email")
    Optional<User> selectUserByEmail(String email);
}
