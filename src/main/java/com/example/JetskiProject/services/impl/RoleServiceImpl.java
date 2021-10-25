package com.example.JetskiProject.services.impl;

import com.example.JetskiProject.model.Role;
import com.example.JetskiProject.model.Roles;
import com.example.JetskiProject.repo.RoleRepository;
import com.example.JetskiProject.services.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    @Override
    public Role findRoleByName(Roles roleName) {
        return roleRepository.findFirstByRoleNameEquals(roleName);
    }
}
