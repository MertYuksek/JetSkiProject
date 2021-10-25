package com.example.JetskiProject.repo;

import com.example.JetskiProject.model.Role;
import com.example.JetskiProject.model.Roles;
import com.example.JetskiProject.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    @Query
    Optional<User> findFirstByEmailAddressEquals(@Param(value = "emailAddress") String emailAddress);
}
