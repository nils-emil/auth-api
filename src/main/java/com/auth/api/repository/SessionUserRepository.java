package com.auth.api.repository;

import com.auth.api.model.SessionUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface SessionUserRepository extends JpaRepository<SessionUser, String> {

    @Query(value = "SELECT u FROM SessionUser u where u.idCode=:searchParam or u.email=:searchParam")
    Optional<SessionUser> findByEmailOrIdCode(String searchParam);
}
