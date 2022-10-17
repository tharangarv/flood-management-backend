package com.example.springjwt.service.business_logic.impl;

import com.example.springjwt.dto.RoleDTO;
import com.example.springjwt.dto.UserRoleListDTO;
import com.example.springjwt.entity.UserRole;
import com.example.springjwt.service.business_logic.RoleService;
import com.example.springjwt.service.data_access.RoleInfoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final RoleInfoService roleInfoService;

    @Override
    public RoleDTO saveRole(RoleDTO roleDTO) {

        try {
            UserRole userRole = roleInfoService.saveUserRole(roleDTO);

            RoleDTO savedUser = RoleDTO.builder()
                    .userRole(userRole.getUserRoleName())
                    .build();

            log.info("user role saved: {}", savedUser);
            return savedUser;

        } catch (Exception e) {
            log.error("Error : Unable to save the user role : {}", e.getMessage());
            return null;
        }

    }

    @Override
    public UserRoleListDTO getRole() {

        try {
            List<UserRole> userRoles = roleInfoService.getUserRole();

            List<RoleDTO> convertedRoles = userRoles.stream().map(role -> RoleDTO.builder()
                    .userRole(role.getUserRoleName())
                    .build()).collect(Collectors.toList());

            UserRoleListDTO rolesList = UserRoleListDTO.builder()
                    .userRoles(convertedRoles)
                    .build();

            log.info("Role : {} ", rolesList);
            return rolesList;

        } catch (Exception e) {
            log.error("Error : Unable to get the role : {}", e.getMessage());
            return null;
        }

    }
}
