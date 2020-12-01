package com.zeon.Security.SpringSecurityAuthserver.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zeon.Security.SpringSecurityAuthserver.Model.Users;

import java.util.Optional;

public interface UsersRepository extends JpaRepository<Users, Integer> {
    Optional<Users> findByName(String username);
}
