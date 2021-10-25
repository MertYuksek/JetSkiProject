package com.example.JetskiProject.services;

import com.example.JetskiProject.model.Role;
import com.example.JetskiProject.model.Roles;

public interface RoleService {

    Role findRoleByName(Roles roleName);
}
