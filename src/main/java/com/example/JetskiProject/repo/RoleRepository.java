package com.example.JetskiProject.repo;

import com.example.JetskiProject.model.Role;
import com.example.JetskiProject.model.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface RoleRepository extends JpaRepository<Role,Integer> {

    @Query
    Role findFirstByRoleNameEquals(@Param(value = "roleName") Roles roleName);
}
