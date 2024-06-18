package com.idat.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.idat.demo.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    public boolean existsByEmail(String email);
}
