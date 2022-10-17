package com.example.springjwt.service.data_access.impl;

import com.example.springjwt.dto.RoleDTO;
import com.example.springjwt.entity.UserRole;
import com.example.springjwt.repository.RoleRepo;
import com.example.springjwt.service.data_access.RoleInfoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class RoleInfoServiceImpl implements RoleInfoService {

    private final RoleRepo roleRepo;

    @Override
    public UserRole saveUserRole(RoleDTO userRoleDTO) throws Exception {

        if (userRoleDTO.getUserRole().isEmpty()) throw new Exception("User role is empty");

        UserRole userRole = UserRole.builder()
                .userRoleName(userRoleDTO.getUserRole())
                .build();

        return roleRepo.save(userRole);

    }

    @Override
    public List<UserRole> getUserRole() throws Exception {


        List<UserRole> userRoles = roleRepo.findAll();

        if (userRoles.isEmpty()) throw new Exception("Roles are empty");

        return userRoles;

    }

    @Override
    public UserRole getUserRoleByRoleName(String roleName) throws Exception {
        if (roleName == null) throw new Exception("Role name is empty");

        return roleRepo.findByUserRoleName(roleName);

    }


}
