package com.example.demo.model.users;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface UserRepository extends JpaRepository<AppUser,Long> {

    public Optional<AppUser> findUserByUsername(String username);
}
